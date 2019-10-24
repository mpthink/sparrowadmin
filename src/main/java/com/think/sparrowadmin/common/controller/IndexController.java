package com.think.sparrowadmin.common.controller;

import com.think.sparrowadmin.remexplus.service.IRpTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 首页控制器
* @ClassName: IndexController
* @author map6
*
 */
@Controller
@RequestMapping("/")
public class IndexController {  


    @Autowired
    private IRpTaskService rpTaskService;

    @RequestMapping({"","/","index"})  
    public  String index(Model model){
        List<Map<String, Object>> taskWithRecordTimes = rpTaskService.getTaskWithRecordTimes();
        model.addAttribute("listTasks", taskWithRecordTimes);
        return "index";
    }
}
