/******************************************************************************
 *                       COPYRIGHT 2002 - 2012 BY DELL INC.
 *                          ALL RIGHTS RESERVED
 *
 * THIS DOCUMENT OR ANY PART OF THIS DOCUMENT MAY NOT BE REPRODUCED WITHOUT
 * WRITTEN PERMISSION FROM DELL INC.
 *****************************************************************************/

package com.think.sparrowadmin.common.aspect;

import com.google.gson.Gson;
import com.think.sparrowadmin.common.annotation.LogAnnotation;
import com.think.sparrowadmin.common.util.ShiroUtil;
import com.think.sparrowadmin.system.entity.SysLog;
import com.think.sparrowadmin.system.entity.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @author map6
 */
@Aspect //将一个类定义为一个切面类
@Component
//@Order(1) 标记切面类的处理优先级，i值越小，优先级越高. PS： 可以注解类，也可以注解到方法上
public class LogAspect {

    public static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    // 需要切一个包下所有类，@Pointcut("execution(public * com.xxx.xxx.inr.api..*.*(..))")这样比较方便
    @Pointcut("execution(* com.think.sparrowadmin.system.controller.*.*(..))")
    public void controllerLogAspect() {
    }

    @AfterReturning(value = "controllerLogAspect()")
    public void logControllerAfterReturn(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String methodName = method.getName();
        SysUser sysUser = ShiroUtil.getSessionUser();

        SysLog sysLog = new SysLog();
        sysLog.setGmtCreate(LocalDateTime.now());
        sysLog.setTitle(methodName);
        sysLog.setUserName((sysUser != null) ? sysUser.getUserName() : "system");
        sysLog.setUrl(request.getRequestURI().toString());
        String params = new Gson().toJson(request.getParameterMap()).toString();
        sysLog.setParams(params.substring(0, params.length()>1000 ? 900:params.length()-1));
        sysLog.insert();
        LOG.debug("记录日志:" + sysLog.toString());
    }

    // 针对性的对某一些类进行注解
    @Pointcut("@annotation(com.think.sparrowadmin.common.annotation.LogAnnotation)")
    public void logAnnotation(){
    }

    @AfterReturning(value = "logAnnotation()")
    public void logAnnotationAfterReturn(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LogAnnotation log =  method.getAnnotation(LogAnnotation.class);
        SysUser sysUser = ShiroUtil.getSessionUser();
        if(log != null){
            SysLog sysLog  =new SysLog();
            sysLog.setGmtCreate(LocalDateTime.now());
            sysLog.setTitle(log.value());
            sysLog.setUserName((sysUser != null )? sysUser.getUserName() : "system");
            sysLog.setUrl(request.getRequestURI().toString());
            sysLog.setParams(new Gson().toJson(request.getParameterMap()));
            sysLog.insert();
            LOG.debug("记录日志:"+sysLog.toString());
        }
    }

}
