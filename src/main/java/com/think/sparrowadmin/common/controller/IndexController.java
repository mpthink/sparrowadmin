package com.think.sparrowadmin.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 首页控制器
* @ClassName: IndexController
* @author map6
*
 */
@Controller
@RequestMapping("/")
public class IndexController {  
	
    @RequestMapping({"","/","index"})  
    public  String index(Model model){
		return "index";
    }  
}
