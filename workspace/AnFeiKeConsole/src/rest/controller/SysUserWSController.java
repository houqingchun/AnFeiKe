package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import rest.entity.WsCallResult;

import com.base.util.MethodUtil;
import com.jeecg.entity.SysUser;
import com.jeecg.service.SysUserService;

@Controller
public class SysUserWSController {

	@RequestMapping(method=RequestMethod.GET, value="/wsLogin/{loginName}/{loginPwd}")
	public ModelAndView getSingleDemo(@PathVariable String loginName, @PathVariable String loginPwd){
		//此处添加逻辑，生成相应的返回结果以bean形式响应给调用者
//		WsCallResult ws = new WsCallResult();
//		SysUser u = sysUserService.queryLogin(loginName, MethodUtil.MD5(loginPwd));
//		ws.setData(u);
//		if (u == null){
//			ws.setCode("001");
//			ws.setMessage(WsCallResult.LOGIN_ERROR);
//		}else{
//			ws.setCode("200");
//			ws.setMessage(WsCallResult.LOGIN_SUCCESS);
//		}
//		return new ModelAndView("","result", ws);
		return null;
	}	
}
