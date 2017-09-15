package com.base.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import biz.entity.people.People;

import com.base.annotation.Auth;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;
import com.base.web.BaseAction;


/**
 * 权限拦截器
 * @author  www.jeecg.org
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	private final static Logger log= Logger.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod method = (HandlerMethod)handler;
		log.debug("Request URI:" + request.getRequestURI());
		log.debug("Request URL:" + request.getRequestURL());
		Auth  auth = method.getMethod().getAnnotation(Auth.class);
		////验证登陆超时问题  auth = null，默认验证 
		if(auth == null || auth.verifyLogin()){
			People user =SessionUtils.getUser(request);
		
			if(user == null){
				if ("AJAX".equals(request.getParameter("postType"))){
					log.debug("AJAX response[Pre Handler]");
					//以AJAX方式提交
					response.setStatus(response.SC_GATEWAY_TIMEOUT);
					Map<String, Object> result = new HashMap<String, Object>();
					result.put(BaseAction.SUCCESS, false);
					result.put(BaseAction.LOGOUT_FLAG, true);// 登录标记 true 退出
					result.put(BaseAction.MSG, "登录超时"); // 登录超时.
					HtmlUtil.writerJson(response, result);
				}else{
					log.debug("Redirect response[Pre Handler]");
					//直接设置至提醒界面，提示需要登录
		            response.sendRedirect(request.getContextPath() + "/error.html?msgCode=OFFLINE&nextStep=LOGON&toUrl=" + request.getRequestURL() + SessionUtils.getEncodeParameters(request, true));
		            return false;
				}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
