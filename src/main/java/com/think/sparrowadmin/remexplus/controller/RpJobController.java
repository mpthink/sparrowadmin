package com.think.sparrowadmin.remexplus.controller;


import com.think.sparrowadmin.common.controller.SuperController;
import com.think.sparrowadmin.remexplus.service.IRpJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * jobs of task 前端控制器
 * </p>
 *
 * @author Paul Ma
 * @since 2019-09-29
 */
@Controller
@RequestMapping("/remexplus/job")
public class RpJobController extends SuperController {

    @Autowired
    private IRpJobService rpJobService;

    @RequestMapping("/list/{id}")
    public  String list(@PathVariable String id, Model model){
        model.addAttribute("taskRecordId", id);
        return "remexplus/job/list";
    }

    @RequestMapping("/listJobs/{id}")
    @ResponseBody
    public  String getJobsByTaskRecordId(@PathVariable String id, Model model){
        return toJson(rpJobService.selectJobsByTaskRecordId(id));
    }

}

