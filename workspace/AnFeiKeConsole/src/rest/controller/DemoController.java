package rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import rest.entity.Demo;

import com.jeecg.entity.SysUser;
import com.jeecg.service.SysUserService;

@Controller
public class DemoController {

	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SysUserService<SysUser> sysUserService;
	@Autowired(required = false)
	
	@RequestMapping(method=RequestMethod.GET, value="/demos/{id}")
	public ModelAndView getSingleDemo(@PathVariable String id) throws Exception {
		// 此处添加逻辑，生成相应的返回结果以bean形式响应给调用者
		Demo r = new Demo();
		r.setId(id);
		r.setName("name-test");
		r.setCode("code-test");	
		return new ModelAndView("","result", r);
	}
	@RequestMapping(method=RequestMethod.POST, value="/demos")
	public ModelAndView getDemoList(@RequestBody String body) throws Exception {
		//此处添加逻辑，生成相应的返回结果以bean形式响应给调用者
		List<Demo> c = new ArrayList<Demo>();
		for (int i = 0 ; i < 20; i++){
			Demo r = new Demo();
			r.setId(i+"");
			r.setName("name-test" + i);
			r.setCode("code-test" + i);
			c.add(r);
		}
		
		return new ModelAndView("","result", c);
	}



}
