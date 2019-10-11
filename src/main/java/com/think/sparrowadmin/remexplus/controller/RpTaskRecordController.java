package com.think.sparrowadmin.remexplus.controller;


import com.think.sparrowadmin.common.bean.Rest;
import com.think.sparrowadmin.common.controller.SuperController;
import com.think.sparrowadmin.remexplus.service.IRpTaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * record task running 前端控制器
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Controller
@RequestMapping("/remexplus/taskRecord")
public class RpTaskRecordController extends SuperController {

    @Autowired
    private IRpTaskRecordService rpTaskRecordService;


    @RequestMapping("/list")
    public  String list(Model model){
        return "remexplus/taskrecord/list";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public String getAllArrayInfo() {
        return toJson(rpTaskRecordService.selectTaskRecords());
    }

    @PostMapping("/delete")
    @ResponseBody
    public Rest batchDelete(HttpServletRequest request, HttpServletResponse response){
        String idsString = request.getParameter("ids");
        String[] ids = idsString.split(",");
        for(String id:ids){
            rpTaskRecordService.removeById(id);
        }
        return Rest.ok();
    }
}