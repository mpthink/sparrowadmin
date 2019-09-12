package com.think.sparrowadmin.common.interceptor;


import com.think.sparrowadmin.common.util.ShiroUtil;
import com.think.sparrowadmin.common.util.SpringUtil;
import com.think.sparrowadmin.system.entity.SysSetting;
import com.think.sparrowadmin.system.entity.SysUser;
import com.think.sparrowadmin.system.entity.vo.TreeMenu;
import com.think.sparrowadmin.system.service.ISysMenuService;
import com.think.sparrowadmin.system.service.ISysSettingService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 全局拦截器
 * @author  map6
 *
 */
//@Component
public class GlobalInterceptor extends HandlerInterceptorAdapter {

	private final static Logger LOG = LoggerFactory.getLogger(GlobalInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (handler instanceof HandlerMethod) {
			
			/**
			 * 加载全局非登录访问常量
			 */
			List<SysSetting> list =  SpringUtil.getBean(ISysSettingService.class).findAll();
			for(SysSetting setting : list){
				request.setAttribute(setting.getSysKey(),setting.getSysValue());
			}
			
			/**
			 * 保存登录信息
			 */
			SysUser me = ShiroUtil.getSessionUser();
			if(me == null){
				return true;
			}
			me.setPassword("");
			request.setAttribute("me", me);
			/**
			 * 资源和当前选中菜单
			 */
			String res = request.getParameter("p");
			if (StringUtils.isNotBlank(res)) {
				request.getSession().setAttribute("res", res);
			}
			String cur = request.getParameter("t");
			if (StringUtils.isNotBlank(cur)) {
				request.getSession().setAttribute("cur", cur);
			}
			/**
			 * 获取当前用户的菜单
			 */
			List<TreeMenu> treeMenus = SpringUtil.getBean(ISysMenuService.class).selectTreeMenuByUserId(me.getId());
			request.setAttribute("treeMenus", treeMenus);

			LOG.debug("Get tree menus!");
			
		}

		/**
		 * 通过拦截
		 */
		return true;
	}

}
