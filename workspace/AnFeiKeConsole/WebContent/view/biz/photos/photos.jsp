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
			<label class="ui-label">照片:</label><input name="photoPath" class="easyui-box ui-text" style="width:100px;">
			<label class="ui-label">介绍:</label><input name="description" class="easyui-box ui-text" style="width:100px;">
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
		     	   <div class="ftitle">照片</div>
					<div class="fitem">
						<label>照片</label>
						<input name="photoPath" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写照片">
					</div>
					<div class="fitem">
						<label>介绍</label>
						<input name="description" type="text" maxlength="4000" class="easyui-validatebox" data-options="" missingMessage="请填写介绍">
					</div>
					<div class="fitem">
						<label>主表ID</label>
						<input name="mainId" type="text" maxlength="36" class="easyui-validatebox" data-options="required:true" missingMessage="请填写主表ID">
					</div>
					<div class="fitem">
						<label>主表类别</label>
						<input name="mainType" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写主表类别">
					</div>
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/biz/photos/page-photos.js"></script>
  </body>
</html>
