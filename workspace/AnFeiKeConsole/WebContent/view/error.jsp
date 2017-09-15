<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="head.jsp"%>
</head>
<body>

	<div id="wrapper">
		<%@include file="menu.jsp"%>
		<div id="page-wrapper">
			<!--bottom-header-->
			<div class="container-fluid" style="min-height: 300px;">
				<div class="alert alert-danger" role="alert">
					<label>温馨提示：</label>
					<p>${msg}</p>
					<c:if test="${fn:contains(msg,'001')}">
						<script>
							window.location="${basePath}/people/logon-page.shtml";
						</script>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>

</html>
