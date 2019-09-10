package com.think.sparrowadmin.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.think.sparrowadmin.common.bean.MyPage;
import com.think.sparrowadmin.common.bean.Rest;
import com.think.sparrowadmin.common.controller.SuperController;
import com.think.sparrowadmin.system.entity.SysMenu;
import com.think.sparrowadmin.system.service.ISysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends SuperController {

    /**
     * 菜单服务
     */
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 分页查询菜单
     */
    @RequiresPermissions("listMenu")
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue="15") Integer pageSize, String search, Model model){

        IPage<SysMenu> page = getPage(pageNumber,pageSize);
        model.addAttribute("pageSize",pageSize);
        // 查询分页
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(search)){
            queryWrapper.like("menu_name",search);
            model.addAttribute("search",search);
        }
        queryWrapper.orderByAsc("code");
        IPage<SysMenu> pageData = sysMenuService.page(page, queryWrapper);

        for(SysMenu menu : pageData.getRecords()){
            if(menu.getPid() == null || menu.getDeep() !=3){
                menu.setMenuName(StringUtils.join("<i class='fa fa-folder-open'></i> ",menu.getMenuName()));
            }else{
                menu.setMenuName(StringUtils.join("<i class='fa fa-file'></i> ",menu.getMenuName()));
            }
            for(int i=1;i<menu.getDeep();i++){
                menu.setMenuName(StringUtils.join("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;",menu.getMenuName()));
            }

        }

        MyPage<SysMenu> myPage = new MyPage<>();
        myPage.setRecords(pageData.getRecords());
        myPage.setCurrent(pageData.getCurrent());
        myPage.setSize(pageData.getSize());
        myPage.setTotal(pageData.getTotal());
        myPage.setPages(pageData.getPages());
        model.addAttribute("pageData", myPage);
        return "system/menu/list";
    }

    /**
     * 增加菜单
     */
    @RequiresPermissions("addMenu")
    @RequestMapping("/add")
    public String add(Model model){

        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("code");
        queryWrapper.eq("pid","0");
        List<SysMenu> list = sysMenuService.list(queryWrapper);
        model.addAttribute("list",list);
        return "system/menu/add";

    }
    /**
     * 添加目录
     */
    @RequiresPermissions("addMenu")
    @RequestMapping("/doAddDir")
    @ResponseBody
    public Rest doAddDir(SysMenu sysMenu, Model model){

        sysMenu.setPid("0");
        sysMenu.setDeep(1);
        sysMenuService.save(sysMenu);
        return Rest.ok();
    }

    /**
     * 添加菜单
     */
    @RequiresPermissions("addMenu")
    @RequestMapping("/doAddMenu")
    @ResponseBody
    public Rest doAddMenu(SysMenu sysMenu,Model model){
        sysMenu.setDeep(2);
        sysMenuService.save(sysMenu);
        return Rest.ok();
    }
    /**
     * 编辑菜单
     */
    @RequiresPermissions("editMenu")
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id,Model model){
        SysMenu sysMenu =sysMenuService.getById(id);
        model.addAttribute("sysMenu", sysMenu);

        if(sysMenu.getDeep() == 1){
            return  "/system/menu/edit";
        }else if(sysMenu.getDeep() == 2){
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("code");
            queryWrapper.eq("pid","0");
            List<SysMenu> list = sysMenuService.list(queryWrapper);
            model.addAttribute("list",list);
            return "/system/menu/editMenu";
        }
        else{
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("code");
            queryWrapper.eq("pid","0");
            List<SysMenu> list = sysMenuService.list(queryWrapper);
            model.addAttribute("list",list);
            model.addAttribute("pSysMenu",sysMenuService.getById(sysMenu.getPid()));
            return  "/system/menu/editAction";
        }
    }

    /**
     * 执行编辑菜单
     */
    @RequiresPermissions("editMenu")
    @RequestMapping("/doEdit")
    @ResponseBody
    public Rest doEdit(SysMenu sysMenu,Model model){
        sysMenuService.updateById(sysMenu);
        return Rest.ok();
    }

    /**
     * 执行编辑菜单
     */
    @RequiresPermissions("deleteMenu")
    @RequestMapping("/delete")
    @ResponseBody
    public Rest delete(String id){
        sysMenuService.removeById(id);
        return Rest.ok();
    }

    /**
     * 根据父节点获取子菜单
     */
    @RequestMapping("/json")
    @ResponseBody
    public Rest json(String pid){
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.eq("pid", pid);
        List<SysMenu> list = sysMenuService.list(queryWrapper);

        List<Map<String, Object>> listMap = new ArrayList<>();
        for(SysMenu m : list){
            Map<String, Object> map = new HashMap<>();
            map.put("id", m.getId());
            map.put("text",StringUtils.join(m.getCode(),"-",m.getMenuName()));
            listMap.add(map);
        }
        return Rest.okData(listMap);
    }


    /**
     * 验证菜单资源名称是否存在
     */
    @RequestMapping("/checkMenuResource")
    @ResponseBody
    public Rest checkMenuResource(String resource){

        List<SysMenu> list = sysMenuService.list(new QueryWrapper<SysMenu>().eq("resource", resource));
        if(list.size() > 0){
            return Rest.failure("资源已存在,请换一个尝试.");
        }
        return Rest.ok();
    }

    @RequiresPermissions("addMenu")
    @RequestMapping("/doAddAction")
    public String doAddAction(SysMenu sysMenu,Model model){
        sysMenu.setDeep(3);
        sysMenuService.save(sysMenu);
        return redirectTo("/system/menu/list/1.html");
    }

}

