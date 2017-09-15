var portal = {
	isDefined : function(variable){
			return (typeof(window[variable]) == 'undefined')? false : true;
	},
	ajaxSubmit : function(form, option) {
		form.ajaxSubmit(option);
	},
	ajaxJson : function(url, option, callback) {
		$.ajax(url, {
			type : 'post',
			dataType : 'json',
			data : option + "&postType=AJAX",
			success : function(data) {
				if(!portal.checkLogin(data)){
		 			return false;
		 		}
				if ($.isFunction(callback)) {
					callback(data);
				}
			},
			error : function(response, textStatus, errorThrown) {
				try {
					var data = $.parseJSON(response.responseText);
					// 检查登录
			 		if(!portal.checkLogin(data)){
			 			return false;
			 		}else{
			 			bootbox.alert(data.msg);
			 		}					
				} catch (e) {
					bootbox.alert("Error occured.");
				}
			},
			complete : function() {

			}
		});
	},
	
	deleteFormConfirm: function(url, option, callback){
		$('#confirm-delete').on('show.bs.modal', function(e) {
	        $(this).find('.btn-ok').click(function(){
	        	$('#confirm-delete').modal('hide');
		   		portal.deleteForm(url, option, callback);
	        });
	    });
		$('#confirm-delete').modal('show');
	},
	
	submitForm : function(form, callback, dataType) {
		
		var option = {
			type : 'post',
			dataType : dataType || 'json',
			success : function(data) {
				if(!portal.checkLogin(data)){
		 			return false;
		 		}
				
				if ($.isFunction(callback)) {
					callback(data);
				}
			},
			error : function(response, textStatus, errorThrown) {
				try {
					var data = $.parseJSON(response.responseText);
					if(!portal.checkLogin(data)){
			 			return false;
			 		}else{
			 			bootbox.alert(data.msg);
			 		}
				} catch (e) {
					bootbox.alert("Error occured.");
				}
			},
			complete : function() {

			}
		}
		portal.ajaxSubmit(form, option);
	},

	saveForm : function(form, callback) {
		if (form.form('validate')) {
			// ajax提交form
			portal.submitForm(form, function(data) {
				if(!portal.checkLogin(data)){
		 			return false;
		 		}
				
				if (data.success) {
					if (callback) {
						callback(data);
					} else {
						bootbox.alert(data.msg);
					}
				} else {
					bootbox.alert(data.msg);
				}
			});
		}
	},
	/**
	 * 
	 * @param {}
	 *            url
	 * @param {}
	 *            option {id:''}
	 */
	getById : function(url, option, callback) {
		portal.ajaxJson(url, option, function(data) {
			if (data.success) {
				if (callback) {
					callback(data);
				}
			} else {
				bootbox.alert(data.msg);
			}
		});
	},

	deleteForm : function(url, option, callback) {
		bootbox.confirm("确认要删除吗？", function(result){ 
			if (result){
				portal.ajaxJson(url, option, function(data) {
					if(!portal.checkLogin(data)){
			 			return false;
			 		}
					
					if (data.success) {
						bootbox.alert("删除成功！");
						if (callback) {
							callback(data);
						}
					} else {
						bootbox.alert(data.msg)
					}
				});
			}
		})
	},
	
	startProgress: function(){
		var $modal = $('.js-loading-bar'),
		$bar = $modal.find('.progress-bar');
		  
		$modal.modal('show');
		$bar.addClass('animate');
	},
	
	closeProgress: function(){
		var $modal = $('.js-loading-bar'),
		$bar = $modal.find('.progress-bar');
		
		$bar.removeClass('animate');
	    $modal.modal('hide');
	},
	
	checkSelect : function(rows){// 检查grid是否有勾选的行, 有返回 true,没有返回true
		var records =  rows;
		if(records && records.length > 0){
			return true;
		}
		bootbox.alert("您还没有选择数据，请选择!");
		return false;
	},

	checkSelectOne : function(rows){// 检查grid是否只勾选了一行,是返回 true,否返回true
		var records = rows;
		if(!portal.checkSelect(records)){
			return false;
		}
		if(records.length == 1){
			return true;
		}
		bootbox.alert("对不起，只能勾选一行数据！");
		return false;
	},
	
	hideDialogDelay : function (diaglogId, times){
		var delayTm = times || 1500;
		setTimeout("$('" + diaglogId + "').modal('hide')", delayTm);
	},
	

	initRichText : function (id, basePath, imagRelativePath){
		if (typeof imagRelativePath === "undefined"){
			imagRelativePath = basePath;
		}
		
		$('#' + id).summernote({
			  height:200,
			  toolbar: [
			          // [groupName, [list of button]]
			          ['style', ['bold', 'italic', 'underline', 'clear']],
			          ['font', ['strikethrough', 'superscript', 'subscript']],
			          ['fontsize', ['fontsize']],
			          ['color', ['color']],
			          ['para', ['ul', 'ol', 'paragraph']],
			          ['height', ['height']],
			          ['insert', ['picture','link','video','table','hr']],
			          ['fullscreen', ['fullscreen']],
			          ['codeview',['codeview']]
			   ],
			  disableDragAndDrop: true,
			  shortcuts: true,
			  lang: 'zh-CN',
			  callbacks: {
				  onImageUpload: function(files, editor, $editable) {
			          $editor = $(this);
					  data = new FormData();
			          data.append("file", files[0]);
			           $.ajax({
			        	   url: basePath + '/file/uploadForSummernote.shtml',
			           data: data,
			           cache: false,
			           contentType: false,
			           processData: false,
			           type: 'POST',
			           success: function(data){
			        	   var res = $.parseJSON(data);
				           $editor.summernote("insertImage", imagRelativePath + res.files[0].ctxFilePath, 'filename');
				       },
				       error: function(jqXHR, textStatus, errorThrown) {
				           console.log(textStatus+" "+errorThrown);
			          }
			           })
				    }
				  }
			});
		
	},

	openCropperModal : function (fileFolder, basePath, oldImgCtxPath){
		var srcv = basePath + '/file/cropperEdit.shtml?targetFld=' + fileFolder;
		if (typeof oldImgCtxPath === "undefined"){
		}else{
			srcv = srcv + '&oldImgCtxPath=' + oldImgCtxPath;
		}

		var s = '<div class="modal fade" id="cropperModalContainer" aria-hidden="true"                         ';
		s = s + '	aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">                      ';
		s = s + '	<div class="modal-dialog">                                                             ';
		s = s + '		<div class="modal-content">                                                        ';
		s = s + '			<div class="modal-header">                                                     ';
		s = s + '				<button type="button" class="close" data-dismiss="modal">&times;</button>  ';
		s = s + '				<h4 class="modal-title" id="avatar-modal-label">图片编辑</h4>                  ';
		s = s + '			</div>                                                                         ';
		s = s + '			<div class="modal-body">                                                 		';
		s = s + '<iframe width="100%" height="330" frameborder="0" scrolling="yes" allowtransparency="true" src="' + srcv + '"</iframe>'
		s = s + '			</div>'
	    s = s + '        <div class="modal-footer">                                                        ';
	    s = s + '          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>  ';
	    s = s + '        </div>                                                                            ';
		s = s + '		</div>                                                                             ';
		s = s + '	</div>                                                                                 ';
		s = s + '</div>                                                                                    ';
		$('#cropperModalContainer').remove();
		$('body').append(s);
		$("#cropperModalContainer").modal("show");
	},
	
	closeCropperModal: function (){
		$("#cropperModalContainer").modal("hide");
	},
	
	cropperUploadCallback:function(){
		// Will be replaced in page where they are using page cropper function
	},
	
	checkLogin:function(data){// 检查是否登录超时
		if(data.logoutFlag){
			portal.closeProgress();
			// bootbox.alert(data.msg);
			// 弹出登录窗口
			$('#logonDialog').modal('show');
			return false;
		}
		return true;
	},	
	init : function(formId, callback, customRules, customRuleMsg, dataType) {
		var formId = formId || "editForm";
		var form = $("#" + formId);
		form.validate({
			rules : customRules || {},
			messages : customRuleMsg || {},
			submitHandler : function(form) {
				portal.startProgress();
				$.ajax({
					url : form.action,
					type : form.method || "post",
					data : $(form).serialize() + "&postType=AJAX",
					dataType : dataType || "json",
					success : function(result) {
						if(!portal.checkLogin(result)){
				 			return false;
				 		}
						portal.closeProgress();
						// bootbox.alert("Success");
						if ($.isFunction(callback)) {
							callback(result);
						}
					}
					,
					error : function(response, textStatus, errorThrown) {
						portal.closeProgress();
						try {
							var data = $.parseJSON(response.responseText);
							if(!portal.checkLogin(data)){
					 			return false;
					 		}else{
					 			bootbox.alert(data.msg);
					 		}
						} catch (e) {
							bootbox.alert("错误，请刷新重试");
						}
					},
					complete : function() {
						// bootbox.alert("Complete");
					}
				});
			}
		});
	}
}


$.validator.setDefaults({
	highlight : function(element) {
		$(element).closest('.form-group').addClass('has-error');
	},
	unhighlight : function(element) {
		$(element).closest('.form-group').removeClass('has-error');
	},
	errorElement : 'span',
	errorClass : 'help-block',
	errorPlacement : function(error, element) {
		if (element.parent('.input-group').length) {
			error.insertAfter(element.parent());
		} else {
			error.insertAfter(element);
		}
	}
});
