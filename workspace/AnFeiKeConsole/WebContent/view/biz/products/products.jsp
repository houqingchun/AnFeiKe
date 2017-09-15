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
			<label class="ui-label">机型型号:</label><input name="name" class="easyui-box ui-text" style="width:100px;">
			<label class="ui-label">类别:</label><input name="types" class="easyui-box ui-text" style="width:100px;">
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
		     	   <div class="ftitle">机型</div>
					<div class="fitem">
						<label>机型型号</label>
						<input name="name" type="text" maxlength="200" class="easyui-validatebox" data-options="required:true" missingMessage="请填写机型型号">
					</div>
					<div class="fitem">
						<label>类别</label>
						<input name="types" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写类别">
					</div>
					<div class="fitem">
						<label>品牌</label>
						<input name="brand" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写品牌">
					</div>
					<div class="fitem">
						<label>座位数</label>
						<input name="seatsNo" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写座位数">
					</div>
					<div class="fitem">
						<label>续航里程</label>
						<input name="ranges" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写续航里程">
					</div>
					<div class="fitem">
						<label>里程单位</label>
						<input name="rangesUnit" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写里程单位">
					</div>
					<div class="fitem">
						<label>速度</label>
						<input name="speeds" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写速度">
					</div>
					<div class="fitem">
						<label>速度单位</label>
						<input name="speedsUnit" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写速度单位">
					</div>
					<div class="fitem">
						<label>最大载重</label>
						<input name="loads" type="text" maxlength="" class="easyui-numberbox" data-options="required:true" missingMessage="请填写最大载重">
					</div>
					<div class="fitem">
						<label>最大载重单位</label>
						<input name="loadsUnit" type="text" maxlength="100" class="easyui-validatebox" data-options="required:true" missingMessage="请填写最大载重单位">
					</div>
					<div class="fitem">
						<label>照片</label>
						<input name="photoPath" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写照片">
					</div>
					<div class="fitem">
						<label>简述</label>
						<input name="shortDesc" type="text" maxlength="4000" class="easyui-validatebox" data-options="" missingMessage="请填写简述">
					</div>
					<div class="fitem">
						<label>详细描述</label>
						<input name="description" type="text" maxlength="4000" class="easyui-validatebox" data-options="" missingMessage="请填写详细描述">
					</div>
					<div class="fitem">
						<label>制造商</label>
						<input name="manufacture" type="text" maxlength="255" class="easyui-validatebox" data-options="" missingMessage="请填写制造商">
					</div>
					<div class="fitem">
						<label>生产日期</label>
						<input name="produceDate" type="text" maxlength="" class="easyui-datebox" data-options="" missingMessage="请填写生产日期">
					</div>
					<div class="fitem">
						<label>参考价</label>
						<input name="price" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写参考价">
					</div>
					<div class="fitem">
						<label>参考价单位</label>
						<input name="priceUnit" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写参考价单位">
					</div>
					<div class="fitem">
						<label>预付价</label>
						<input name="prePay" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写预付价">
					</div>
					<div class="fitem">
						<label>预付价单位</label>
						<input name="prePayUnit" type="text" maxlength="" class="easyui-numberbox" data-options="required:true,precision:2,groupSeparator:','" missingMessage="请填写预付价单位">
					</div>
  			</div>
     	</form>
  	 </div>
  	 <script type="text/javascript" src="<%=basePath%>/view/biz/products/page-products.js"></script>
  </body>
</html>
