<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap Core CSS -->
<link href="${basePath}/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="${basePath}/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="${basePath}/css/modern-business.css" rel="stylesheet">
<link href="${basePath}/css/local-style.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script src="${basePath}/js/jquery.js"></script>
<title>后台管理-${COMPANY_OBJ.name}</title>
<jsp:useBean id="now" class="java.util.Date" />