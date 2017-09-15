package biz.controller.people;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.base.annotation.Auth;
import com.base.util.Constant;
import com.base.util.HtmlUtil;
import com.base.util.MethodUtil;
import com.base.util.SessionUtils;
import com.base.web.BaseAction;
import com.base.web.BaseValidator;

import biz.entity.company.Company;
import biz.entity.dict.DataDictionary;
import biz.entity.news.News;
import biz.entity.people.People;
import biz.entity.products.Products;
import biz.page.company.CompanyPage;
import biz.page.dict.DataDictionaryPage;
import biz.page.news.NewsPage;
import biz.page.people.PeoplePage;
import biz.page.products.ProductsPage;
import biz.service.company.CompanyService;
import biz.service.dict.DataDictionaryService;
import biz.service.news.NewsService;
import biz.service.people.PeopleService;
import biz.service.products.ProductsService;

/**
 * 
 * <br>
 * <b>功能：</b>PeopleController<br>
 * <b>作者：</b>Boris Hou<br>
 * <b>日期：</b> July, 2015 <br>
 */
@Controller
@RequestMapping("/people")
public class PeopleController extends BaseAction {

	public static final String COOKIE_PORTAL_ACCOUNT = "COOKIE_PORTAL_ACCOUNT";

	public static final String COOKIE_PORTAL_PWDMD5 = "COOKIE_PORTAL_PWDMD5";

	private final static Logger log = Logger.getLogger(PeopleController.class);

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private PeopleService<People> peopleService;

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private NewsService<News> newsService;

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ProductsService<Products> productsService;

	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private DataDictionaryService<DataDictionary> dataDictionaryService; 

	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CompanyService<Company> companyService; 
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/list")
	public ModelAndView list(PeoplePage page, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("biz/people/people", context);
	}

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/logon-page")
	public ModelAndView logonPage(PeoplePage page, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		return forword("biz/people/logon-page", context);
	}

	private void initialForm(People people) {
		people.setIsAdmin("N");
		people.setIsAvailable("Y");
		people.setIsDeleted("N");
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/regist")
	public void regist(People entity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (entity.getId() == null
				|| StringUtils.isBlank(entity.getId().toString())) {
			// 若帐号未填写，则默认为邮箱
			if (StringUtils.isBlank(entity.getAccount())) {
				entity.setAccount(entity.getEmail());
			}
			// 完整服务器端验证
			BaseValidator validator = new PeopleValidator(entity, 1,
					this.peopleService);
			String error = validator.getErrorString();
			if (error != null) {
				sendFailureMessage(response, error);
			} else {
				// 设置默认值
				initialForm(entity);
				// 加密
				entity.setPwd(MethodUtil.MD5(entity.getPwd()));

				peopleService.add(entity);
				// 直接自动登录
				SessionUtils.setUser(request, entity);
				sendSuccessMessage(response, entity, "注册成功");
			}
		} else {
			sendFailureMessage(response, "您已经登陆");
		}
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/dataList")
	public void datalist(PeoplePage page, HttpServletResponse response)
			throws Exception {
		List<People> dataList = peopleService.queryByList(page);
		// 设置页面数据
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", page.getPager().getRowCount());
		jsonMap.put("rows", dataList);
		HtmlUtil.writerJson(response, jsonMap);
	}

	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/logon")
	public void logon(PeoplePage user, HttpServletRequest req,
			HttpServletResponse response) throws Exception {
		// 从Cookie里取值，以判断是否以COOKIE信息登录
		Cookie[] reqCookie = req.getCookies();

		user.setPwd(MethodUtil.MD5(user.getPwd()));

		People u = peopleService.queryLogin(user);

		if (u != null) {
			// 设置User到Session
			SessionUtils.setUser(req, u);

			// 刷新 COOKIE
			this.refreshCookie(user, req, response);

			sendSuccessMessage(response, u, "登陆成功");
			// -------------------------------------------------------
		} else {
			sendFailureMessage(response, "用户名或密码错误!");
		}
	}

	/**
	 * 根据用户选择是否记录COOKIE 更新COOKIE内容和有效期
	 * 
	 * @param user
	 * @param req
	 * @param response
	 */
	private void refreshCookie(PeoplePage user, HttpServletRequest req,
			HttpServletResponse response) {
		// 重新写入cookie
		Cookie accountCookie = new Cookie(COOKIE_PORTAL_ACCOUNT,
				user.getAccount());
		Cookie pwdCookie = new Cookie(COOKIE_PORTAL_PWDMD5, user.getPwd());
		int maxAgeForAccount = 60 * 60 * 24 * 7;// 365天
		int maxAgeForPwd = -1;// 默认不保存
		if ("ON".equalsIgnoreCase(user.getRemindMe())) {
			// 7天
			maxAgeForPwd = 60 * 60 * 24 * 7;
		}
		accountCookie.setMaxAge(maxAgeForAccount);
		pwdCookie.setMaxAge(maxAgeForPwd);

		response.addCookie(accountCookie);
		response.addCookie(pwdCookie);
	}

	/**
	 * 清除COOKIE
	 * 
	 * @param user
	 * @param req
	 * @param response
	 */
	private void clearCookie(HttpServletResponse response) {
		// 重新写入cookie
		Cookie accountCookie = new Cookie(COOKIE_PORTAL_ACCOUNT, "");
		Cookie pwdCookie = new Cookie(COOKIE_PORTAL_PWDMD5, "");

		accountCookie.setMaxAge(-1);
		pwdCookie.setMaxAge(-1);

		response.addCookie(accountCookie);
		response.addCookie(pwdCookie);
	}

	/**
	 * 找回密码时验证
	 * 
	 * @param user
	 * @param req
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/checkAccountForPwdReset")
	public void checkAccountForPwdReset(PeoplePage user,
			HttpServletRequest req, HttpServletResponse response)
			throws Exception {
		List<People> returnPeople = peopleService.queryByList(user);
		if (returnPeople.size() == 1) {
			// 匹配正确
			sendSuccessMessage(response, returnPeople.get(0), "匹配正确，请输入新密码");
		} else if (returnPeople.size() > 1) {
			// 找到多人，需要更详细信息确认
			sendFailureMessage(response, "匹配到多个帐号，请提供更详细信息");
		} else {
			// 未找到
			sendFailureMessage(response, "未找到相关信息");
		}
	}

	/**
	 * 找回密码-提交
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/resetPwd")
	public void resetPwd(People entity, HttpServletRequest req,
			HttpServletResponse response) throws Exception {
		// 完整服务器端验证
		BaseValidator validator = new PeopleValidator(entity, 6,
				this.peopleService);
		String error = validator.getErrorString();
		if (error != null) {
			sendFailureMessage(response, error);
		} else {
			People bean = new People();
			bean.setId(entity.getId());
			bean.setPwd(MethodUtil.MD5(entity.getPwd()));
			peopleService.update(bean);

			sendSuccessMessage(response, "密码重置成功");
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyURL = false)
	@RequestMapping("/modifyPwd")
	public void modifyPwd(People entity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		People user = SessionUtils.getUser(request);
		if (user == null) {
			sendFailureMessage(response, "对不起，您尚未登陆，或登陆已超时，请重新登录");
			return;
		}
		People bean = peopleService.queryById(user.getId());
		if (bean.getId() == null
				|| Constant.DELETED.YES.key == bean.getIsDeleted()) {
			sendFailureMessage(response, "对不起,用户不存在.");
			return;
		}

		// 不是超级管理员，匹配旧密码
		if (!MethodUtil.ecompareMD5(entity.getOldPwd(), bean.getPwd())) {
			sendFailureMessage(response, "旧密码输入不匹配.");
			return;
		}

		// 完整服务器端验证
		BaseValidator validator = new PeopleValidator(entity, 4,
				this.peopleService);
		String error = validator.getErrorString();
		if (error != null) {
			sendFailureMessage(response, error);
		} else {
			bean.setPwd(MethodUtil.MD5(entity.getPwd()));
			peopleService.update(bean);
			sendSuccessMessage(response, "保存成功");
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyURL = false)
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SessionUtils.removeUser(request);
		this.clearCookie(response);
		response.sendRedirect(request.getContextPath()
				+ "/index.htm");
	}

	/**
	 * 获取登录状态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/logonStatus")
	public void logonStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap<String, Object>();
		People entity = SessionUtils.getUser();
		boolean active = false;
		if (entity == null) {
			// 检查是否COOKIE有效，有效则直接登录
			Cookie[] reqCookie = request.getCookies();
			String account = null;
			String pwd = null;

			log.debug("自动登录检查");
			for (Cookie c : reqCookie) {
				log.debug("Cookie Info: " + c.getName() + "=" + c.getValue());
				if (c.getName().equals(COOKIE_PORTAL_PWDMD5)) {// 密码从Cookie而来，不用再次加密
					pwd = c.getValue();
				}
				if (c.getName().equals(COOKIE_PORTAL_ACCOUNT)) {// 密码从Cookie而来，不用再次加密
					account = c.getValue();
				}
			}
			// 若COOKIE有效，则自动更新session内容
			if (StringUtils.isNotBlank(account) && StringUtils.isNotBlank(pwd)) {
				log.debug("自动登录检查有效。。。");
				PeoplePage p = new PeoplePage();
				p.setAccount(account);
				p.setPwd(pwd);
				entity = peopleService.queryLogin(p);
				if (entity != null) {
					log.debug("重新设置SESSION");
					// 设置User到Session
					SessionUtils.setUser(request, entity);
					// 此处不再刷新cookie，7天后将失败，需要重新验证
					active = true;
				} else {
					log.debug("Cookie无效，被清除");
					this.clearCookie(response);
				}
			}
		} else {
			log.debug("在线状态");
			active = true;
		}

		if (active) {
			context.put(SUCCESS, true);
			context.put("data", entity);
			HtmlUtil.writerJson(response, context);
		} else {
			sendFailureMessage(response, "未登陆");
			return;
		}
	}

	/**
	 * 添加或修改数据
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public void update(People entity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		People user = SessionUtils.getUser(request);
		if (user == null) {
			sendFailureMessage(response, "对不起，您尚未登陆，或登陆已超时，请重新登录");
			return;
		}

		Map<String, Object> context = new HashMap<String, Object>();
		initialForm(entity);

		// 完整服务器端验证
		BaseValidator validator = new PeopleValidator(entity, 2,
				this.peopleService);
		String error = validator.getErrorString();
		if (error != null) {
			sendFailureMessage(response, error);
		} else {
			this.peopleService.update(entity);

			sendFailureMessage(response, "更新成功");
		}
	}

	@RequestMapping("/getId")
	public void getId(String id, HttpServletResponse response) throws Exception {
		Map<String, Object> context = new HashMap();
		People entity = peopleService.queryById(id);
		if (entity == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		context.put(SUCCESS, true);
		context.put("data", entity);
		HtmlUtil.writerJson(response, context);
	}
	

	/**
	 * 我的主页
	 * 
	 * @param page
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/myHome")
	public ModelAndView myHome(PeoplePage page, HttpServletRequest request)
			throws Exception {
		Map<String, Object> context = getRootMap();
		People user = SessionUtils.getUser();

		if (user != null) {
			String userId = user.getId();

//			ProductsPage pp = new ProductsPage();
//			pp.setPagination(false);
//			context.put("productsList", this.productsService.queryByList(pp));
//
//			NewsPage np = new NewsPage();
//			np.setPagination(false);
//			context.put("newsList", this.newsService.queryByList(np));
//			
//			DataDictionaryPage dt = new DataDictionaryPage();
//			dt.setPagination(false);
//			dt.setType(Constant.DICT_TYPE_PRODUCTTYPE);//只维护机型类别
//			context.put("dictList", this.dataDictionaryService.queryByList(dt));
//			
//
//			CompanyPage cp = new CompanyPage();
//			cp.setPagination(false);
//			List<Company> cpList = this.companyService.queryByList(cp);
//			if (cpList.size() == 1){
//				context.put("company", cpList.get(0));
//			}

			return forword("biz/people/my-home", context);
		} else {
			return this.error("OFFLINE", "LOGON",
					SessionUtils.getServletContextPath()
							+ "/people/myHome.html", request);
		}
	}

	@RequestMapping("/delete")
	public void delete(String[] id, HttpServletResponse response)
			throws Exception {
		peopleService.delete(id);
		sendSuccessMessage(response, "删除成功");
	}

}
