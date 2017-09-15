<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
  <body class="easyui-layout">
 	 <!-- Search panel start -->
 	 <div class="ui-search-panel" region="north" style="height: 80px;" title="过滤条件" data-options="striped: true,collapsible:false,iconCls:'icon-search',border:false" >  
 	 <form id="searchForm">
        <p class="ui-fields">
			<label class="ui-label">幻灯片类别:</label><input name="slideType" class="easyui-box ui-text" style="width:100px;">
			<label class="ui-label">标题:</label><input name="themeTitle" class="easyui-box ui-text" style="width:100px;">
	    </p>
	    <a href="#" id="btn-search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
      </form>  
     </div> 
     <!--  Search panel end -->

     <!-- Data List -->
     <div region="center" border="false" >
     <table id="data-list"></table>
	 </div>
	 
     <!-- Edit Win&Form -->
     <div id="edit-win" class="easyui-dialog" title="Basic window" data-options="closed:true,iconCls:'icon-save',modal:true" style="width:400px;height:380px;">  
     	<form id="editForm" class="ui-form" method="post">  
     		 <input class="hidden" name="id">
     		 <div class="ui-edit">
		     	   <div class="ftitle">幻灯片信息</div>
					<div class="fitem">
						<label>幻灯片类别</label>
						<input name="slideType" type="text" maxlength="36" class="easyui-validatebox" data-options="required:true" missingMessage="请填写幻灯片类别">
					</div>
					<div class="fitem">
						<label>标题</label>
						<input name="themeTitle" type="text" maxlength="300" class="easyui-validatebox" data-options="required:true" missingMessage="请填写标题">
					</div>
					<div class="fitem">
						<label>图片</label>
						<input name="photoPath" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写图片">
					</div>
					<div class="fitem">
						<label>主题内容</label>
						<input name="themeDesc" type="text" maxlength="65535" class="easyui-validatebox" data-options="" missingMessage="请填写主题内容">
					</div>
					<div class="fitem">
						<label>注释</label>
						<input name="themeComments" type="text" maxlength="65535" class="easyui-validatebox" data-options="" missingMessage="请填写注释">
					</div>
					<div class="fitem">
						<label>显示顺序</label>
						<input name="displayOrder" type="text" maxlength="" class="easyui-numberbox" data-options="" missingMessage="请填写显示顺序">
					</div>
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/biz/slide/page-slide.js"></script>
  </body>
</html>
