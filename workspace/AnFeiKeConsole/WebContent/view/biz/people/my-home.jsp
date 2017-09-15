<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../../top.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../../head.jsp"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/bootstrap-table.min.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/bootstrap-table.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/locale/bootstrap-table-zh-CN.min.js"></script>
</head>

<body>
	<%@include file="../../menu.jsp"%>


	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					&nbsp;&nbsp;数据信息管理 <small></small>
				</h1>
			</div>
		</div>
		<div class="col-xs-3">
			<div class="panel panel-danger">
				<div class="panel-heading">
					<h3 class="panel-title">安全设置</h3>
				</div>
				<div class="panel-body">
					<a href="#" data-toggle="modal" data-target="#pwdUpdateDialog">修改密码</a>
					<br></br>
					<a href="${basePath}/people/logout.shtml" data-toggle="modal">退出</a>
				</div>
			</div>
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">主体信息维护</h3>
				</div>
				<div class="panel-body">
					<a href="${basePath}/company/edit.shtml" target="_blank">公司信息修改</a>
					<br></br>
					<a href="${basePath}/club/edit.shtml" target="_blank">俱乐部信息修改</a>
				</div>
			</div>
			<c:if test="${SESSION_USER.isAdmin == 'Y' }">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">管理员操作</h3>
					</div>
					<div class="panel-body">
						<a href="#" id="clearCacheLink">清除后端缓存</a>
						<br></br>
						<a href="#" id="publishToClientLink">发布至前端</a>
					</div>
				</div>
			</c:if>
		</div>
		<div class="col-xs-9">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">机型维护</h3>
				</div>
				<div class="panel-body">
					<a href="${basePath}/products/edit.shtml"
						class="btn btn-warning btn-sm" target="_blank"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加机型
					</a>
				</div>
				<div class="table-responsive">
					<table id="productsTable" data-toggle="table"
						data-url="${basePath}/products/myList.shtml"
						data-side-pagination="server" data-pagination="true"
						data-page-list="[5, 10, 20, 50]" data-search="false"
						data-click-to-select="true" data-single-select="true"
						data-show-refresh="true" data-show-toggle="false">
						<thead>
							<tr>
								<th data-field="name" data-sortable="false">型号</th>
								<th data-field="types" data-sortable="true">类别</th>
								<th data-field="brand" data-sortable="true">品牌</th>
								<th data-field="updateTime" data-sortable="true">最后更新</th>
								<th data-field="id" data-formatter="operateFormatterProducts">操作</th>
							</tr>
						</thead>
					</table>
				</div>

				<div class="panel-heading">
					<h3 class="panel-title">新闻维护</h3>
				</div>
				<div class="panel-body">
					<a href="${basePath}/news/edit.shtml"
						class="btn btn-warning btn-sm" target="_blank"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加新闻
					</a>
				</div>
				<div class="table-responsive">
					<table id="newsTable" data-toggle="table"
						data-url="${basePath}/news/myList.shtml"
						data-side-pagination="server" data-pagination="true"
						data-page-list="[5, 10, 20, 50]" data-search="false"
						data-click-to-select="true" data-single-select="true"
						data-show-refresh="true" data-show-toggle="false">
						<thead>
							<tr>
								<th data-field="subject" data-sortable="false">标题</th>
								<th data-field="types" data-sortable="true">类别</th>
								<th data-field="updateTime" data-sortable="true">最后更新</th>
								<th data-field="id" data-formatter="operateFormatterNews">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				
				<div class="panel-heading">
					<h3 class="panel-title">数据字典-信息维护</h3>
				</div>
				<div class="panel-body">
					<a href="${basePath}/dataDictionary/edit.shtml"
						class="btn btn-warning btn-sm" target="_blank"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
					</a>
				</div>
				<div class="table-responsive">
					<table id="dictTable" data-toggle="table"
						data-url="${basePath}/dataDictionary/myList.shtml"
						data-side-pagination="server" data-pagination="false"
						data-page-list="[5, 10, 20, 50]" data-search="false"
						data-click-to-select="true" data-single-select="true"
						data-show-refresh="true" data-show-toggle="false">
						<thead>
							<tr>
								<th data-field="type" data-sortable="false">数据字典类别</th>
								<th data-field="code" data-sortable="false">编码</th>
								<th data-field="name" data-sortable="true">描述</th>
								<th data-field="nameNumber" data-sortable="true">排列顺序</th>
								<th data-field="updateTime" data-sortable="true">最后更新</th>
								<th data-field="id" data-formatter="operateFormatterDicts">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				
				<div class="panel-heading">
					<h3 class="panel-title">幻灯片-信息维护</h3>
				</div>
				<div class="panel-body">
					<a href="${basePath}/slide/edit.shtml"
						class="btn btn-warning btn-sm" target="_blank"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加
					</a>
				</div>
				<div class="table-responsive">
					<table id="slideTable" data-toggle="table"
						data-url="${basePath}/slide/myList.shtml"
						data-side-pagination="server" data-pagination="true"
						data-page-list="[5, 10, 20, 50]" data-search="false"
						data-click-to-select="true" data-single-select="true"
						data-show-refresh="true" data-show-toggle="false">
						<thead>
							<tr>
								<th data-field="slideType" data-sortable="false">类别</th>
								<th data-field="themeTitle" data-sortable="false">大标题</th>
								<th data-field="themeDesc" data-sortable="true">简介</th>
								<th data-field="displayOrder" data-sortable="true">显示顺序</th>
								<th data-field="updateTime" data-sortable="true">最后更新</th>
								<th data-field="id" data-formatter="operateFormatterSlide">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				
				<div class="panel-heading">
					<h3 class="panel-title">招聘信息维护</h3>
				</div>
				<div class="panel-body">
					<a href="${basePath}/recruit/edit.shtml"
						class="btn btn-warning btn-sm" target="_blank"> <span
						class="glyphicon glyphicon-plus" aria-hidden="true"></span>招聘信息
					</a>
				</div>
				<div class="table-responsive">
					<table id="recruitTable" data-toggle="table"
						data-url="${basePath}/recruit/myList.shtml"
						data-side-pagination="server" data-pagination="true"
						data-page-list="[5, 10, 20, 50]" data-search="false"
						data-click-to-select="true" data-single-select="true"
						data-show-refresh="true" data-show-toggle="false">
						<thead>
							<tr>
								<th data-field="jobBase" data-sortable="true">所在地</th>
								<th data-field="jobTitle" data-sortable="false">职位</th>
								<th data-field="headCount" data-sortable="true">招聘人数</th>
								<th data-field="createTime" data-sortable="true">发布时间</th>
								<th data-field="id" data-formatter="operateFormatterRecruit">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!--end-footer-->
	<%@include file="../../footer.jsp"%>
	<!--end-footer-->


	<!-- 修改密码 -->
	<div class="modal fade" id="pwdUpdateDialog" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<h3>修改密码</h3>
					</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" id="formPwdUpdate"
						action="${basePath}/people/modifyPwd.shtml" method="post">
						<div class="form-group">
							<!-- Text input-->
							<label class="col-xs-3 control-label" for="input01"><span
								class="requiredmark">*</span>旧密码</label>
							<div class="col-xs-6">
								<input placeholder="旧密码" class="form-control" type="password"
									name="oldPwd" required="required" maxlength="20">
							</div>
						</div>
						<div class="form-group">
							<!-- Text input-->
							<label class="col-xs-3 control-label" for="input01"><span
								class="requiredmark">*</span>新密码</label>
							<div class="col-xs-6">
								<input placeholder="请输入新密码" class="form-control" type="password"
									name="pwd" required="required" maxlength="20">
							</div>
						</div>

						<div class="form-group">

							<!-- Text input-->
							<label class="col-xs-3 control-label" for="input01"><span
								class="requiredmark">*</span>重新输入</label>
							<div class="col-xs-6">
								<input placeholder="请重新输入" class="form-control" type="password"
									name="pwdRepeat" required="required" maxlength="20">
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-offset-3 col-xs-6">
								<button type="submit" class="btn btn-primary">
									<span class="glyphicon glyphicon-save" aria-hidden="true"></span>保存
								</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer inline">
					<div class="alert alert-success" role="alert"
						id="notificationForPwdUpdate" style="display: none;">...</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>


	<script>
		//修改密码FORM
		portal.init("formPwdUpdate", function(result) {
			if (result.success) {
				//提交新密码
				$('#notificationForPwdUpdate').html(result.msg).removeClass()
						.addClass('alert alert-success').show();

				//延迟关闭
				portal.hideDialogDelay("#pwdUpdateDialog");
			} else {
				//匹配失败
				$('#notificationForPwdUpdate').html(result.msg).removeClass()
						.addClass('alert alert-danger').show();
			}

		});

		function displayImageFormat(value, row) {
			if (value != undefined && value.length > 0) {
				return '<img src="${basePath}' + value.replace('_O.', '_I.') + '" alt="..." class="img-rounded" >';
			} else {
				return '<img src="${basePath}/images/img_default_i.gif" alt="..." class="img-rounded" >';
			}

		}
		function operateFormatterProducts(value, row, index) {
			return [
					'<a class="edit ml10" href="${basePath}/products/edit.shtml?id='
							+ value + '" title="修改" target="_blank">',
					'<i class="glyphicon glyphicon-edit"></i>',
					'</a>',
					'&nbsp;&nbsp;',
					'<a class="edit ml10" href="${basePath}/products/view.shtml?id='
							+ value + '" title="查看" target="_blank">',
					'<i class="glyphicon glyphicon-eye-open"></i>',
					'</a>',
					'&nbsp;&nbsp;',
					'<a class="edit ml10" href="#" onclick="removedRowData(\'productsTable\',\'products\',\'' + value + '\')" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>'

			].join('');
		}

		function operateFormatterNews(value, row, index) {
			return [
					'<a class="edit ml10" href="${basePath}/news/edit.shtml?id='
							+ value + '" title="修改" target="_blank">',
					'<i class="glyphicon glyphicon-edit"></i>',
					'</a>',
					'&nbsp;&nbsp;',
					'<a class="edit ml10" href="${basePath}/news/view.shtml?id='
							+ value + '" title="查看" target="_blank">',
					'<i class="glyphicon glyphicon-eye-open"></i>',
					'</a>',
					'&nbsp;&nbsp;',
					'<a class="edit ml10" href="#" onclick="removedRowData(\'newsTable\',\'news\',\'' + value + '\')" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}
		
		function operateFormatterDicts(value, row, index) {
			return [
					'<a class="edit ml10" href="${basePath}/dataDictionary/edit.shtml?id='
							+ value + '" title="修改" target="_blank">',
					'<i class="glyphicon glyphicon-edit"></i>',
					'</a>',
					'&nbsp;&nbsp;',
					'<a class="edit ml10" href="#" onclick="removedRowData(\'dictTable\',\'dataDictionary\',\'' + value + '\')" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}
		
		function operateFormatterSlide(value, row, index) {
			return [
					'<a class="edit ml10" href="${basePath}/slide/edit.shtml?id='
							+ value + '" title="修改" target="_blank">',
					'<i class="glyphicon glyphicon-edit"></i>',
					'</a>',
					'&nbsp;&nbsp;',
					'<a class="edit ml10" href="#" onclick="removedRowData(\'slideTable\',\'slide\',\'' + value + '\')" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}
		


		function operateFormatterRecruit(value, row, index) {
			return [
					'<a class="edit ml10" href="${basePath}/recruit/edit.shtml?id='
							+ value + '" title="修改" target="_blank">',
					'<i class="glyphicon glyphicon-edit"></i>',
					'</a>',
					'&nbsp;&nbsp;',
					'<a class="edit ml10" href="${basePath}/recruit/view.shtml?id='
							+ value + '" title="查看" target="_blank">',
					'<i class="glyphicon glyphicon-eye-open"></i>',
					'</a>',
					'&nbsp;&nbsp;',
					'<a class="edit ml10" href="#" onclick="removedRowData(\'recruitTable\',\'recruit\',\'' + value + '\')" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}
		
		
		function removedRowData(tableObj, subObjName, delId){
			var arr = [];			
	    	arr.push('id='+delId);
			var data = arr.join("&");
	   		portal.deleteForm('${basePath}/' + subObjName + '/delete.shtml',data,function(result){
	   			$('#confirm-delete').modal('hide');
	   			$('#' + tableObj).bootstrapTable('refresh');
			});
		}

		$(document).ready(function() {
			$('#clearCacheLink').click(function() {
				portal.deleteForm("clearHTMLCache.shtml?postType=AJAX");
			});
			
			$('#publishToClientLink').click(function() {
				portal.deleteForm("publishToClient.shtml?postType=AJAX");
			})
		});
	</script>
</body>
</html>
