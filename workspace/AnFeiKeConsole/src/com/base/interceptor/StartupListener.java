package com.base.interceptor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.base.util.SpringContextUtil;
import com.base.web.MemCachedUtil;

/**
 * 
 * @author 侯青春<br>
 * 用于初始化相关系统变量使用
 *
 */
public class StartupListener extends ContextLoaderListener implements
		ServletContextListener {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info(" StartupListener 初始化开始！");
//		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

		
		//实始化数据字典
		//ISystemConfigService systemConfigService = (ISystemConfigService) ctx.getBean("systemConfigService");
		//List<SystemConfig> systemConfigs = systemConfigService.retrieveSystemConfigs();
		
		//BaseAction.loadSystemConfigInfomation(event.getServletContext(), systemConfigs);

		logger.info("  StartupListener 初始化结束！");
	}
}
