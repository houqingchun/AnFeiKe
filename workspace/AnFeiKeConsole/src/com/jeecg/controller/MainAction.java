package com.jeecg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import biz.entity.club.Club;
import biz.entity.company.Company;
import biz.entity.news.News;
import biz.entity.people.People;
import biz.entity.photos.Photos;
import biz.entity.products.Products;
import biz.entity.recruit.Recruit;
import biz.entity.slide.Slide;
import biz.page.news.NewsPage;
import biz.page.products.ProductsPage;
import biz.page.recruit.RecruitPage;
import biz.page.slide.SlidePage;
import biz.service.club.ClubService;
import biz.service.company.CompanyService;
import biz.service.news.NewsService;
import biz.service.people.PeopleService;
import biz.service.photos.PhotosService;
import biz.service.products.ProductsService;
import biz.service.recruit.RecruitService;
import biz.service.slide.SlideService;

import com.base.annotation.Auth;
import com.base.util.Constant;
import com.base.web.BaseAction;

@Controller
public class MainAction extends BaseAction {

	private final static Logger log = Logger.getLogger(MainAction.class);

	@Autowired(required = false)
	private PeopleService<People> PeopleService;

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ProductsService<Products> productsService;

	// Servrice start
	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private NewsService<News> newsService;

	@Autowired(required = false)
	// 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private PhotosService<Photos> photosService;

	// Servrice start
	@Autowired(required = false) // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private CompanyService<Company> companyService;

	// Servrice start
	@Autowired(required = false) // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private SlideService<Slide> slideService;

	// Servrice start
	@Autowired(required = false) // 自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private ClubService<Club> clubService;
	

	// Servrice start
	@Autowired(required=false) //自动注入，不需要生成set方法了，required=false表示没有实现类，也不会报错。
	private RecruitService<Recruit> recruitService; 

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		Map<String, Object> context = getRootMap();

		// 默认按照完整度和姓名倒序

		ProductsPage page = new ProductsPage();
		page.setPagination(false);
		if (StringUtils.isBlank(page.getSort())) {
			page.setSort("name");
			page.setOrder("desc");
		}

		// this.initialForm(page, false);
		List<Products> dataList = productsService.queryByList(page);

		// this.initialForm(page, true);
		// 设置页面数据
		context.put("productRows", dataList);

		// 默认按照完整度和姓名倒序
		NewsPage nPage = new NewsPage();
		nPage.setPagination(false);
		if (StringUtils.isBlank(nPage.getSort())) {
			nPage.setSort("createTime");
			nPage.setOrder("desc");
		}

		// this.initialForm(page, false);
		List<News> nDataList = newsService.queryByList(nPage);
		
		List<News> compNews = new ArrayList<News>();
		List<News> areaNews = new ArrayList<News>();
		List<News> policyNews = new ArrayList<News>();

		for (News n : nDataList){
			if (Constant.DICT_TYPE_NEWSTYPE_AREA.equals(n.getTypes())){
				areaNews.add(n);
			}else if (Constant.DICT_TYPE_NEWSTYPE_COMPANY.equals(n.getTypes())){
				compNews.add(n);
			}else if (Constant.DICT_TYPE_NEWSTYPE_POLICY.equals(n.getTypes())){
				policyNews.add(n);
			}
		}
		
		context.put("newsCompany", compNews);
		context.put("newsArea", areaNews);
		context.put("newsPolicy", policyNews);

		//Club Information
		context.put("club", this.clubService.queryById("A1000"));
		
		SlidePage slidePage = new SlidePage();
		slidePage.setPagination(false);
		slidePage.setSlideType(Constant.DICT_TYPE_SLIDETYPE_HOME);
		if (StringUtils.isBlank(slidePage.getSort())) {
			nPage.setSort("displayOrder");
		}
		context.put("homeSlides", this.slideService.queryByList(slidePage));
		slidePage.setSlideType(Constant.DICT_TYPE_SLIDETYPE_COMP);
		context.put("compSlides", this.slideService.queryByList(slidePage));
		
		RecruitPage recruitPage = new RecruitPage();
		recruitPage.setPagination(false);
		if (StringUtils.isBlank(recruitPage.getSort())) {
			nPage.setSort("createTime");
		}
		context.put("recruitRows", this.recruitService.queryByList(recruitPage));
		
		return forword("index", context);
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/about")
	public ModelAndView about(HttpServletRequest request) {
		Map<String, Object> context = getRootMap();
		return forword("about", context);
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/contact")
	public ModelAndView contact(HttpServletRequest request) {
		Map<String, Object> context = getRootMap();
		return forword("contact", context);
	}

	/**
	 * ilook 首页
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/services")
	public ModelAndView services(HttpServletRequest request) {
		Map<String, Object> context = getRootMap();
		return forword("services", context);
	}
}
