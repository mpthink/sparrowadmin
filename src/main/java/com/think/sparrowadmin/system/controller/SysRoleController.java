package com.think.sparrowadmin.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.common.bean.Rest;
import com.think.sparrowadmin.common.controller.SuperController;
import com.think.sparrowadmin.system.entity.SysRole;
import com.think.sparrowadmin.system.entity.SysRoleMenu;
import com.think.sparrowadmin.system.entity.SysUser;
import com.think.sparrowadmin.system.entity.SysUserRole;
import com.think.sparrowadmin.system.entity.vo.TreeMenuAllowAccess;
import com.think.sparrowadmin.system.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Controller
@RequestMapping("/system/role")
public class SysRoleController extends SuperController{

    /**
     * 角色服务
     */
    @Autowired
    private ISysRoleService sysRoleService;
    /**
     * 角色用户服务
     */
    @Autowired private ISysUserRoleService sysUserRoleService;
    /**
     * 用户服务
     */
    @Autowired private ISysUserService sysUserService;
    /**
     * 菜单服务
     */
    @Autowired private ISysMenuService sysMenuService;
    /**
     * 角色权限服务
     */
    @Autowired private ISysRoleMenuService sysRoleMenuService;

    /**
     * 分页查询角色
     */
    @RequiresPermissions("listRole")
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue="15") Integer pageSize, String search, Model model){

        Page<SysRole> page = getPage(pageNumber,pageSize);
        page.addOrder(OrderItem.desc("gmt_create"));
        model.addAttribute("pageSize",pageSize);
        // 查询分页
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(search)){
            queryWrapper.like("role_name",search);
            model.addAttribute("search",search);
        }
        IPage<SysRole> pageData = sysRoleService.page(page, queryWrapper);
        model.addAttribute("pageData", pageData);
        return "system/role/list";
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("addRole")
    @RequestMapping("/add")
    public  String add(Model model){
        return "system/role/add";
    }

    /**
     * 执行新增角色
     */
    @RequiresPermissions("addRole")
    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(SysRole role){
        role.setGmtCreate(LocalDateTime.now());
        sysRoleService.save(role);
        return Rest.ok();

    }

    /**
     * 删除角色
     */
    @RequiresPermissions("deleteRole")
    @RequestMapping("/delete")
    @ResponseBody
    public  Rest delete(String id){
        sysRoleService.removeById(id);
        return Rest.ok();
    }

    /**
     * 批量删除角色
     */
    @RequiresPermissions("deleteBatchRole")
    @RequestMapping("/deleteBatch")
    @ResponseBody
    public Rest deleteBatch(@RequestParam("id[]") List<String> ids){
        sysRoleService.removeByIds(ids);
        return Rest.ok();
    }

    /**
     * 编辑角色
     */
    @RequiresPermissions("editRole")
    @RequestMapping("/edit/{id}")
    public  String edit(@PathVariable String id,Model model){
        SysRole sysRole = sysRoleService.getById(id);
        model.addAttribute(sysRole);
        return "system/role/edit";
    }

    /**
     * 执行编辑角色
     */
    @RequiresPermissions("editRole")
    @RequestMapping("/doEdit")
    @ResponseBody
    public  Rest doEdit(SysRole sysRole,Model model){
        sysRole.setGmtModified(LocalDateTime.now());
        sysRoleService.updateById(sysRole);
        return Rest.ok();
    }

    /**
     * 权限
     */
    @RequiresPermissions("authRole")
    @RequestMapping("/auth/{id}")
    public  String auth(@PathVariable String id,Model model){

        SysRole sysRole = sysRoleService.getById(id);

        if(sysRole == null){
            throw new RuntimeException("该角色不存在");
        }

        List<SysRoleMenu> sysRoleMenus = sysRoleMenuService.list(new QueryWrapper<SysRoleMenu>().eq("role_id", id));
        List<String> menuIds = sysRoleMenus.stream().map(sysRoleMenu -> sysRoleMenu.getMenuId()).collect(Collectors.toList());

        List<TreeMenuAllowAccess> treeMenuAllowAccesses = sysMenuService.selectTreeMenuAllowAccessByMenuIdsAndPid(menuIds, "0");

        model.addAttribute("sysRole", sysRole);
        model.addAttribute("treeMenuAllowAccesses", treeMenuAllowAccesses);

        return "system/role/auth";
    }

    /**
     * 权限
     */
    @RequiresPermissions("authRole")
    @RequestMapping("/doAuth")
    @CacheEvict(value = "menuCache", allEntries = true)
    @ResponseBody
    public  Rest doAuth(String roleId,@RequestParam(value="mid[]",required=false) String[] mid){
        sysRoleMenuService.addAuth(roleId,mid);
        return Rest.ok("OK,授权成功,1分钟后生效  ~");
    }

    /**
     * 获取角色下的所有用户
     */
    @RequestMapping("/getUsers")
    public String getUsers(String roleId,Model model){

        List<SysUserRole> sysUserRoles = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("role_id", roleId));

        List<String> userIds = sysUserRoles.stream().map(sysUserRole -> sysUserRole.getUserId()).collect(Collectors.toList());

        List<SysUser> users  = new ArrayList<>();

        if(userIds.size() > 0){
            QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
            queryWrapper.in("id", userIds);
            users= sysUserService.list(queryWrapper);
        }

        model.addAttribute("users",users);
        return "system/role/users";
    }

    /**
     * 获取指定角色的用户数量
     */
    @RequestMapping("/getCount")
    @ResponseBody
    public String getCount(String roleId){

        int count =  sysUserRoleService.count(new QueryWrapper<SysUserRole>().eq("role_id", roleId));
        return String.valueOf(count);
    }

}

