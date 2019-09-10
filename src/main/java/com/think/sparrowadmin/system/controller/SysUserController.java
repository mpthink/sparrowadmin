package com.think.sparrowadmin.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.common.bean.Rest;
import com.think.sparrowadmin.common.controller.SuperController;
import com.think.sparrowadmin.system.entity.SysRole;
import com.think.sparrowadmin.system.entity.SysUser;
import com.think.sparrowadmin.system.entity.SysUserRole;
import com.think.sparrowadmin.system.service.ISysRoleService;
import com.think.sparrowadmin.system.service.ISysUserRoleService;
import com.think.sparrowadmin.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends SuperController{

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    /**
     * 分页查询用户
     */
    @RequiresPermissions("listUser")
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue="15") Integer pageSize, String search, Model model){
        if(StringUtils.isNotBlank(search)){
            model.addAttribute("search", search);
        }
        Page<SysUser> page = getPage(pageNumber,pageSize);
        model.addAttribute("pageSize", pageSize);
        IPage<SysUser> pageData = sysUserService.selectUserPage(page, search);
        model.addAttribute("pageData", pageData);

        return "system/user/list";
    }
    /**
     * 新增用户
     */
    @RequiresPermissions("addUser")
    @RequestMapping("/add")
    public  String add(Model model){
        model.addAttribute("roleList", sysRoleService.list());
        return "system/user/add";
    }

    /**
     * 执行新增
     */
    @RequiresPermissions("addUser")
    @RequestMapping("/doAdd")
    @ResponseBody
    public Rest doAdd(SysUser user, @RequestParam(value="roleId[]",required=false) String[] roleId){

        sysUserService.insertUser(user,roleId);
        return Rest.ok();
    }
    /**
     * 删除用户
     */
    @RequiresPermissions("deleteUser")
    @RequestMapping("/delete")
    @ResponseBody
    public  Rest delete(String id){
        sysUserService.delete(id);
        return Rest.ok();
    }

    /**
     * 编辑用户
     */
    @RequestMapping("/edit/{id}")
    @RequiresPermissions("editUser")
    public  String edit(@PathVariable String id,Model model){
        SysUser sysUser = sysUserService.getById(id);

        List<SysRole> sysRoles = sysRoleService.list();
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>();
        queryWrapper.eq("user_id ", id);
        List<SysUserRole> mySysUserRoles = sysUserRoleService.list();
        List<String> myRolds = mySysUserRoles.stream().map(sysUserRole -> sysUserRole.getRoleId()).collect(Collectors.toList());
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("sysRoles",sysRoles);
        model.addAttribute("myRolds",myRolds);
        return "system/user/edit";
    }
    /**
     * 执行编辑
     */
    @RequiresPermissions("editUser")
    @RequestMapping("/doEdit")
    @ResponseBody
    public  Rest doEdit(SysUser sysUser,@RequestParam(value="roleId[]",required=false) String[] roleId,Model model){
        sysUserService.updateUser(sysUser,roleId);
        return Rest.ok();
    }

    /**
     * 验证用户名是否已存在
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public Rest checkName(String userName){
        List<SysUser> list = sysUserService.list(new QueryWrapper<SysUser>().eq("user_name", userName));
        if(list.size() > 0){
            return Rest.failure("用户名已存在");
        }
        return Rest.ok();
    }

}

