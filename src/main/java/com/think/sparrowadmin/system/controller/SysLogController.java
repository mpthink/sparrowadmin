package com.think.sparrowadmin.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.think.sparrowadmin.common.controller.SuperController;
import com.think.sparrowadmin.system.entity.SysLog;
import com.think.sparrowadmin.system.service.ISysLogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-02
 */
@Controller
@RequestMapping("/system/log")
public class SysLogController extends SuperController {
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 分页查询日志
     */
    @RequiresPermissions("listLog")
    @RequestMapping("/list/{pageNumber}")
    public  String list(@PathVariable Integer pageNumber, @RequestParam(defaultValue="15") Integer pageSize, String search, String daterange, Model model){

        Page<SysLog> page = getPage(pageNumber,pageSize);
        page.addOrder(OrderItem.desc("gmt_create"));
        model.addAttribute("pageSize", pageSize);
        // 查询分页
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(search)){
            queryWrapper.apply("(user_name like CONCAT('%',{0},'%')", search).or()
                    .apply("title like CONCAT('%',{0},'%'))", search);
            model.addAttribute("search", search);
        }
        //日期查询
        if(StringUtils.isNotBlank(daterange)){
            model.addAttribute("daterange", daterange);
            String[] dateranges = StringUtils.split(daterange, "-");
            queryWrapper.apply(" gmt_create >= {0}", dateranges[0].trim().replaceAll("/","-") + " 00:00:00");
            queryWrapper.apply(" gmt_create <= {0}", dateranges[1].trim().replaceAll("/","-") + " 23:59:59");
        }
        IPage<SysLog> pageData = sysLogService.page(page, queryWrapper);
        model.addAttribute("pageData", pageData);
        return "system/log/list";
    }

    /**
     * 获取参数
     */
    @RequestMapping("/params/{id}")
    @ResponseBody
    public String params(@PathVariable String id,Model model){
        SysLog sysLog = sysLogService.getById(id);
        return sysLog.getParams();
    }
}

