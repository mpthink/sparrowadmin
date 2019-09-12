/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.common.controller;

import com.google.code.kaptcha.servlet.KaptchaExtend;
import com.think.sparrowadmin.common.annotation.LogAnnotation;
import com.think.sparrowadmin.system.service.ISysLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Login controller
 * @author map6
 */
@Controller
@RequestMapping("/login")
public class LoginController extends SuperController{
    /**
     * Log service
     */
    @Autowired
    private ISysLogService sysLogService;

    /**
     * login page
     */
    @RequestMapping
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/stopThread")
    @ResponseBody
    public void stopThread(){
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        int noThreads = currentGroup.activeCount();
        Thread[] lstThreads = new Thread[noThreads];
        currentGroup.enumerate(lstThreads);
        Arrays.stream(lstThreads).filter(thread -> thread.getName().equals("threadOne"))
                .forEach(thread -> {
                    thread.interrupt();
                    thread.stop();
                });
    }


    /**
     * 执行登录
     */
    @LogAnnotation("执行用户登录")
    @RequestMapping(value = "/doLogin",method= RequestMethod.POST)
    public  String doLogin(String userName,String password, String captcha,String return_url,RedirectAttributesModelMap model){

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        if (!currentUser.isAuthenticated()) {
            // token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                model.addFlashAttribute("error", "未知用户");
                return redirectTo("/login");
            } catch (IncorrectCredentialsException ice) {
                model.addFlashAttribute("error", "密码错误");
                return redirectTo("/login");
            } catch (LockedAccountException lae) {
                model.addFlashAttribute("error", "账号已锁定");
                return redirectTo("/login");
            }
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                model.addFlashAttribute("error", "服务器繁忙");
                return redirectTo("/login");
            }
        }
        return redirectTo("/");
    }

    /**
     * Verification code
     */
    @RequestMapping("/captcha")
    @ResponseBody
    public  void captcha() throws ServletException, IOException {
        KaptchaExtend kaptchaExtend =  new KaptchaExtend();
        kaptchaExtend.captcha(request, response);
    }

}
