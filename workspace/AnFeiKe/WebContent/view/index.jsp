<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="top.jsp"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
<%@include file="head.jsp"%>
</head>
<body data-spy="scroll" data-target=".navbar-collapse">

	<!-- Preloader -->
	<div id="loading">
		<div id="loading-center">
			<div id="loading-center-absolute">
				<div class="object" id="object_one"></div>
				<div class="object" id="object_two"></div>
				<div class="object" id="object_three"></div>
				<div class="object" id="object_four"></div>
			</div>
		</div>
	</div>
	<!--End off Preloader -->


	<div class="culmn">
		<!--Home page style-->
		<nav class="navbar navbar-default bootsnav navbar-fixed">
			<div class="container">
				<!-- Start Header Navigation -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navbar-menu">
						<i class="fa fa-bars"></i>
					</button>
					<a class="navbar-brand" href="#"> <img
						src="${basePath}/assets/images/logo.gif" class="logo" alt="">
					</a>
				</div>
				<!-- End Header Navigation -->

				<!-- navbar menu -->
				<div class="collapse navbar-collapse" id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#home">主页</a></li>
						<li><a href="#features">服务介绍</a></li>
						<li><a href="#business">关于我们</a></li>
						<li><a href="#product">机型介绍</a></li>
						<li><a href="#club">俱乐部</a></li>
						<li><a href="#news">新闻中心</a></li>
						<li><a href="#contact">联系我们</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
		</nav>

		<!--Home Sections-->

		<section id="home" class="home bg-black fix">
			<div class="overlay"></div>
			<div class="container">
				<div class="row">
					<div class="main_home text-center">
						<div class="col-md-12">
							<div class="hello_slid">
								<c:forEach items="${homeSlides}" var="item" varStatus="idx">
									<div class="slid_item">
										<div class="home_text ">
											<h2 class="text-white">
												<strong>${item.themeTitle}</strong>
											</h2>
											<h1 class="text-white">${item.themeDesc}</h1>
											<h3 class="text-white">- ${item.themeComments} -</h3>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<!--End off row-->
			</div>
			<!--End off container -->
		</section>
		<!--End off Home Sections-->

		<!--Featured Section-->
		<section id="features" class="features">
			<div class="container">
				<div class="row">
					<div class="main_features fix roomy-70">
						<div class="col-xs-6 col-md-3">
							<div class="features_item">
								<div class="f_item_icon">
									<i class="fa fa-thumbs-o-up"></i>
								</div>
								<div class="f_item_text">
									<h3>
										<a
											href="${basePath}/company/service.htm?service=training"
											target="_blank">飞行培训</a>
									</h3>
									<p>专业培训</p>
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-md-3">
							<div class="features_item">
								<div class="f_item_icon">
									<i class="fa fa-support"></i>
								</div>
								<div class="f_item_text">
									<h3><a
											href="${basePath}/company/service.htm?service=trusteeship"
											target="_blank">飞机托管</a>
									</h3>
									<p>委托代管</p>
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-md-3">
							<div class="features_item">
								<div class="f_item_icon">
									<i class="fa fa-clock-o"></i>
								</div>
								<div class="f_item_text">
									<h3><a href="${basePath}/company/service.htm?service=rent"
											target="_blank">租赁服务</a>
									</h3>
									<p>服务流程</p>
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-md-3">
							<div class="features_item">
								<div class="f_item_icon">
									<i class="fa fa-plane"></i>
								</div>
								<div class="f_item_text">
									<h3><a href="${basePath}/company/service.htm?service=sale"
											target="_blank">销售服务</a>
									</h3>
									<p>服务流程</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- End off row -->
			</div>
			<!-- End off container -->
		</section>
		<!-- End off Featured Section-->


		<!--Business Section-->
		<section id="business" class="business bg-grey roomy-70">
			<div class="container">
				<div class="row">
					<div class="main_business">
						<div class="col-md-6">
							<div class="business_slid">
								<div class="slid_shap bg-grey"></div>
								<div class="business_items text-center">
									<c:forEach items="${compSlides}" var="item" varStatus="idx">
										<div class="business_item">
											<div class="business_img">
												<img src="${basePath}${item.photoPath}" alt="" />
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="business_item sm-m-top-50">
								<h2 class="text-uppercase">
									<strong>${COMPANY_OBJ.name}</strong>
								</h2>
								<p class="m-top-20">${COMPANY_OBJ.longDesc}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End off Business section -->


		<!--product section-->
		<section id="product" class="product">
			<div class="container">
				<div class="main_product roomy-70">
					<div class="head_title text-center fix">
						<h2 class="text-uppercase">销售代管机型介绍</h2>
						<h5>销售代管各类机型详细介绍</h5>
					</div>

					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner" role="listbox">
							<div class="item active">
								<div class="container">
									<div class="row">
										<c:forEach items="${productRows}" var="item" varStatus="idx">
											<c:if test="${idx.index > 0 && idx.index % 4 == 0 }">
									</div>
								</div>
							</div>
							<div class="item">
								<div class="container">
									<div class="row">
										</c:if>

										<div class="col-sm-3">
											<div class="port_item xs-m-top-30">
												<div class="port_img">
													<img
														src="${basePath}${fn:replace(item.photoPath,'_O','_S')}"
														alt="" />
													<div class="port_overlay text-center">
														<a
															href="${basePath}${fn:replace(item.photoPath,'_O','_S')}"
															class="popup-img">+</a>
													</div>
												</div>
												<div class="port_caption m-top-20">
													<h5>
														<a href="${basePath}/products/view.htm?id=${item.id}"
															title="${item.name}" target="_blank"> <c:choose>
																<c:when test="${fn:length(item.name) > 15}">
																			${fn:substring(item.name,0,15)}...	
																	</c:when>
																<c:otherwise>
																			${item.name}
																	</c:otherwise>
															</c:choose>
														</a>
													</h5>
													<h6 title="${item.shortDesc}">
														<c:choose>
															<c:when test="${fn:length(item.shortDesc) > 18}">
																			${fn:substring(item.shortDesc,0,18)}...	
																		</c:when>
															<c:otherwise>
																			${item.shortDesc}
																		</c:otherwise>
														</c:choose>
													</h6>
												</div>
											</div>
										</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						<!-- Controls -->
						<a class="left carousel-control" href="#carousel-example-generic"
							role="button" data-slide="prev"> <i class="fa fa-angle-left"
							aria-hidden="true"></i> <span class="sr-only">上一个</span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" role="button" data-slide="next">
							<i class="fa fa-angle-right" aria-hidden="true"></i> <span
							class="sr-only">下一个</span>
						</a>
					</div>
				</div>
				<!-- End off row -->
			</div>
			<!-- End off container -->
		</section>
		<!-- End off Product section -->

		<section id="club" class="bg-grey">
			<div class="container">
				<div class="main_news roomy-70">
					<!-- Service Tabs -->
					<div class="head_title text-center fix">
						<h2 class="text-uppercase">俱乐部</h2>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="panel panel-default text-center">
							<div class="panel-heading">
								<span class="fa-stack fa-5x"> <i
									class="fa fa-circle fa-stack-2x text-primary"></i> <i
									class="fa fa-history fa-stack-1x fa-inverse"></i>
								</span>
							</div>
							<div class="panel-body">
								<h4>背景</h4>
								<p>俱乐部发展历史及背景介绍</p>
								<a href="${basePath}/club/service.htm?service=background"
									class="btn btn-primary">查看</a>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="panel panel-default text-center">
							<div class="panel-heading">
								<span class="fa-stack fa-5x"> <i
									class="fa fa-circle fa-stack-2x text-primary"></i> <i
									class="fa fa-certificate fa-stack-1x fa-inverse"></i>
								</span>
							</div>
							<div class="panel-body">
								<h4>资质</h4>
								<p>俱乐部已经取得的资质认证</p>
								<a href="${basePath}/club/service.htm?service=qualification"
									class="btn btn-primary">查看</a>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="panel panel-default text-center">
							<div class="panel-heading">
								<span class="fa-stack fa-5x"> <i
									class="fa fa-circle fa-stack-2x text-primary"></i> <i
									class="fa fa-asterisk fa-stack-1x fa-inverse"></i>
								</span>
							</div>
							<div class="panel-body">
								<h4>保险</h4>
								<p>俱乐部能够提供的保险及保障服务</p>
								<a href="${basePath}/club/service.htm?service=assurance"
									class="btn btn-primary">查看</a>
							</div>
						</div>
					</div>
					<div class="col-md-3 col-sm-6">
						<div class="panel panel-default text-center">
							<div class="panel-heading">
								<span class="fa-stack fa-5x"> <i
									class="fa fa-circle fa-stack-2x text-primary"></i> <i
									class="fa fa-heart fa-stack-1x fa-inverse"></i>
								</span>
							</div>
							<div class="panel-body">
								<h4>社会责任</h4>
								<p>我们的社会责任与义务</p>
								<a href="${basePath}/club/service.htm?service=responsibility"
									class="btn btn-primary">查看</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section id="news" class="news">
			<div class="container">
				<div class="main_news roomy-70">
					<!-- Service Tabs -->
					<div class="head_title text-center fix">
						<h2 class="text-uppercase">新闻中心</h2>
					</div>

					<div class="col-lg-12 p-bottom-90">
						<ul id="myTab" class="nav nav-tabs nav-justified">
							<li class="active"><a href="#service-one" data-toggle="tab"><i
									class="fa fa-tree"></i> 公司新闻</a></li>
							<li class=""><a href="#service-two" data-toggle="tab"><i
									class="fa fa-car"></i> 行业新闻</a></li>
							<li class=""><a href="#service-three" data-toggle="tab"><i
									class="fa fa-support"></i> 政策法规</a></li>
						</ul>

						<div id="myTabContent" class="tab-content">
							<div class="tab-pane fade active in" id="service-one">
								<h3>&nbsp;</h3>
								<c:forEach items="${newsCompany}" var="item" varStatus="idx">
									<p>${item.subject}
										<i class="fa fa-clock-o"></i> 发布于
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
											value="${item.createTime }" />


										<a class="link link-default"
											href="${basePath}/news/view.htm?id=${item.id}">详情</a>
									</p>
								</c:forEach>
							</div>
							<div class="tab-pane fade" id="service-two">
								<h3>&nbsp;</h3>
								<c:forEach items="${newsArea}" var="item" varStatus="idx">
									<p>${item.subject}
										<i class="fa fa-clock-o"></i> 发布于
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
											value="${item.createTime }" />


										<a class="link link-default"
											href="${basePath}/news/view.htm?id=${item.id}">详情</a>
									</p>
								</c:forEach>
							</div>
							<div class="tab-pane fade" id="service-three">
								<h3>&nbsp;</h3>
								<c:forEach items="${newsPolicy}" var="item" varStatus="idx">
									<p>${item.subject}
										<i class="fa fa-clock-o"></i> 发布于
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
											value="${item.createTime }" />


										<a class="link link-default"
											href="${basePath}/news/view.htm?id=${item.id}">详情</a>
									</p>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- Our Friends 
		<section id="friends" class="p-bottom-20">
			<div class="container">
				<div class="main_friends roomy-30">
						<div class="col-md-2 col-sm-4 col-xs-6">
							<a href="http://www.caac.gov.cn" target="_blank" title="中国民用航空局">
							<img class="img-responsive customer-img"
								src="${basePath}/images/caac.jpg">
								</a>
						</div>
						<div class="col-md-2 col-sm-4 col-xs-6">
							<a href="http://www.cgaa.com.cn" target="_blank" title="中国航空运输协会">
							<img class="img-responsive customer-img"
								src="${basePath}/images/cata.jpg">
								</a>
						</div>
						<div class="col-md-2 col-sm-4 col-xs-6">
							<img class="img-responsive customer-img"
								src="http://placehold.it/500x300" alt="">
						</div>
						<div class="col-md-2 col-sm-4 col-xs-6">
							<img class="img-responsive customer-img"
								src="http://placehold.it/500x300" alt="">
						</div>
						<div class="col-md-2 col-sm-4 col-xs-6">
							<img class="img-responsive customer-img"
								src="http://placehold.it/500x300" alt="">
						</div>
						<div class="col-md-2 col-sm-4 col-xs-6">
							<img class="img-responsive customer-img"
								src="http://placehold.it/500x300" alt="">
						</div>
				</div>
			</div>
		</section>
		-->
		<!--Call to  action section-->
		<section id="action" class="action bg-primary roomy-40">
			<div class="container">
				<div class="row">
					<div class="maine_action">
						<div class="col-md-12">
							<div class="action_item text-center">
								<h2 class="text-white text-uppercase">${COMPANY_OBJ.slogan }</h2>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		<footer id="contact" class="footer action-lage bg-black p-top-80">
			<!--<div class="action-lage"></div>-->
			<div class="container">
				<div class="row">
					<div class="widget_area">
						<div class="col-md-4">
							<div class="widget_item widget_service sm-m-top-50">
								<h5 class="text-white">招贤纳士</h5>
								<ul class="m-top-20">
									<c:forEach items="${recruitRows}" var="item" varStatus="idx">
										<li class="m-top-20"><a href="${basePath}/recruit/view.htm?id=${item.id}" target="_blank"><i
											class="fa fa-angle-right"></i><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
												value="${item.createTime }" /> - ${item.jobBase} - ${item.jobTitle }</a></li>
										</p>
									</c:forEach>
								</ul>
							</div>
							<!-- End off widget item -->
						</div>
						<!-- End off col-md-3 -->
						<div class="col-md-4">
							<div class="widget_item widget_about">
								<h5 class="text-white">联系我们</h5>
								<div class="widget_ab_item m-top-30">
									<div class="item_icon">
										<i class="fa fa-location-arrow"></i>
									</div>
									<div class="widget_ab_item_text">
										<h6 class="text-white">位置</h6>
										<p>${fn:replace(COMPANY_OBJ.address,';','<br>')}</p>
									</div>
								</div>
								<div class="widget_ab_item m-top-10">
									<div class="item_icon">
										<i class="fa fa-phone"></i>
									</div>
									<div class="widget_ab_item_text">
										<h6 class="text-white">电话 :</h6>
										<p>${fn:replace(COMPANY_OBJ.tel,';','<br>')}</p>
									</div>
								</div>
								<div class="widget_ab_item m-top-10">
									<div class="item_icon">
										<i class="fa fa-envelope-o"></i>
									</div>
									<div class="widget_ab_item_text">
										<h6 class="text-white">邮件 :</h6>
										<p>${COMPANY_OBJ.email}</p>
									</div>
								</div>
								<div class="widget_ab_item m-top-10">
									<div class="item_icon">
										<i class="fa fa-mobile"></i>
									</div>
									<div class="widget_ab_item_text">
										<h6 class="text-white">咨询热线 :</h6>
										<p>${fn:replace(COMPANY_OBJ.hotlines,';','<br>')}</p>
									</div>
								</div>
							</div>
							<!-- End off widget item -->
						</div>
						<!-- End off col-md-3 -->

						<div class="col-md-4">
							<iframe width="324" height="305" frameborder="0" scrolling="no"
								marginheight="0" marginwidth="0"
								src="http://j.map.baidu.com/0fwHI"></iframe>
						</div>
					</div>
				</div>
			</div>
			<div
				class="main_footer fix bg-mega text-center p-top-40 p-bottom-30 m-top-80">
				<div class="col-md-12">
					<p class="wow fadeInRight" data-wow-duration="1s">
						${COMPANY_OBJ.name } <i class="fa fa-heart"></i> <a target="_blank" href="#"></a>
						2016-<fmt:formatDate value="${now}" type="both" dateStyle="long"
							pattern="yyyy" />
						. 版权所有 ${COMPANY_OBJ.icp }
					</p>
				</div>
			</div>
		</footer>
		
		<c:forEach items="${homeSlides}" var="item" varStatus="idx">
			<input type="hidden" id="homeSlide${idx.index}" value="${basePath}${item.photoPath}"/>
		</c:forEach>

		<!-- JS includes -->

		<script src="${basePath}/assets/js/vendor/jquery-1.11.2.min.js"></script>
		<script src="${basePath}/assets/js/vendor/bootstrap.min.js"></script>

		<script src="${basePath}/assets/js/owl.carousel.min.js"></script>
		<script src="${basePath}/assets/js/jquery.magnific-popup.js"></script>
		<script src="${basePath}/assets/js/jquery.easing.1.3.js"></script>
		<script src="${basePath}/assets/css/slick/slick.js"></script>
		<script src="${basePath}/assets/js/jquery.collapse.js"></script>
		<script src="${basePath}/assets/js/bootsnav.js"></script>



		<script src="${basePath}/assets/js/plugins.js"></script>
		<script src="${basePath}/assets/js/main.js"></script>
		
		<script type="text/javascript">
	    $(document).ready(function(){
	   	// On before slide change
	      $('.hello_slid').on('beforeChange', function(event, slick, currentSlide, nextSlide){
	        var bgUrl = $('#homeSlide' + nextSlide).val();	        
	        $('#home').css({'background':'url(' + bgUrl + ') no-repeat scroll  center center', 'background-size':'cover','border':'2px solid red;'});
	      });
	    });
	  </script>
	</div>

</body>
</html>
