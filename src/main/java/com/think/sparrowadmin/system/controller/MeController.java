package com.think.sparrowadmin.system.controller;

import com.think.sparrowadmin.common.controller.SuperController;
import com.think.sparrowadmin.common.util.ShiroUtil;
import com.think.sparrowadmin.system.entity.SysUser;
import com.think.sparrowadmin.system.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * 用户中心控制器
 * @author map6
 */
@Controller
@RequestMapping("/system/me")
public class MeController extends SuperController {
	
	@Autowired private ISysUserService sysUserService;
	
	/**
	 * 个人信息
	 * @param model
	 * @return
	 */
    @RequestMapping("/info")  
    public  String info(Model model){
    	
    	SysUser sysUser = sysUserService.getById(ShiroUtil.getSessionUid());
    	model.addAttribute("sysUser", sysUser);
		return "system/me/info";
    } 
    
    
    /**
	 * 修改密码页面
	 * @param model
	 * @return
	 */
    @RequestMapping("/pwd")  
    public  String pwd(Model model){
		return "system/me/pwd";
    } 
    
    /**
     * 修改密码
     */
    @RequestMapping("/doChangePwd")
    public String doChangePwd(String password,String newpassword,String newpassword2,Model model,RedirectAttributes redirectAttributes){
    	
    	if(StringUtils.isBlank(password) || StringUtils.isBlank(newpassword) || StringUtils.isBlank(newpassword2)){
    		redirectAttributes.addFlashAttribute("msg","客户端提交数据不能为空.");
    		return redirectTo("/system/me/pwd");
    	}
    	
    	Subject subject = SecurityUtils.getSubject();
		SysUser sysUser = (SysUser) subject.getPrincipal();
    	
    	SysUser user = sysUserService.getById(sysUser.getId());
    	String passwordMD5 = ShiroUtil.md51024Pwd(password, user.getUserName());
    	if(!user.getPassword().equals(passwordMD5)){
    		redirectAttributes.addFlashAttribute("msg","旧密码输入错误.");
    		return redirectTo("/system/me/pwd");
    	}
    	
    	if(!newpassword2.equals(newpassword)){
    		redirectAttributes.addFlashAttribute("msg","两次输入的密码不一致.");
    		return redirectTo("/system/me/pwd");
    	}
    	
    	user.setPassword(passwordMD5);
    	sysUserService.updateById(user);
    	
    	redirectAttributes.addFlashAttribute("info","密码修改成功.");
    	return redirectTo("/system/me/pwd");
    }
    
    /**
     * 更新用户
     * @param sysUser
     * @param model
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(SysUser sysUser,Model model,RedirectAttributes redirectAttributes){
    	
    	SysUser user = sysUserService.getById(sysUser.getId());
    	if(StringUtils.isNotBlank(user.getAvatar())){
    		user.setAvatar(sysUser.getAvatar());
    	}
    	sysUserService.updateById(user);
    	model.addAttribute("sysUser", user);
    	redirectAttributes.addFlashAttribute("info","更新成功.");
    	return redirectTo("/system/me/info");
    }
}
