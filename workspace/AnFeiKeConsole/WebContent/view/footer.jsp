<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- Bootstrap Core JavaScript -->
<script src="${basePath}/js/bootstrap.min.js"></script>
<!-- Morris Charts JavaScript -->
<script src="${basePath}/js/plugins/bootbox.min.js"></script>
<script src="${basePath}/js/plugins/validate/jquery.validate.min.js"></script>
<script src="${basePath}/js/base.js?id=<%=Math.random() %>"></script>
<script>
	this.$('.js-loading-bar').modal({
		backdrop : 'static',
		show : false
	});
</script>
 <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12 text-center">
                	<p>
                    ${COMPANY_OBJ.name } <i class="fa fa-heart"></i> <a target="_blank" href="#"></a>
						2016-<fmt:formatDate value="${now}" type="both" dateStyle="long"
							pattern="yyyy" />
						. 版权所有 ${COMPANY_OBJ.icp }
                    </p>
                </div>
            </div>
        </footer>
<!--
.progress-bar.animate {
   width: 100%;
}
-->
</style>

<div class="modal js-loading-bar">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="progress progress-popup">
					<div class="progress-bar"></div>
				</div>
			</div>
		</div>
	</div>
</div>