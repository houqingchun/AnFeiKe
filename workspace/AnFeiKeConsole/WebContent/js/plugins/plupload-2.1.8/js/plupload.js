var PluploadObj = function(configPlupload) {
	configPlupload = configPlupload || {};
	var fileContainerId = configPlupload.fileContainerId || "uploader";
	var uploadUri = configPlupload.uploadUri || urls['msUrl'] + "/file/plupload.shtml";
	var maxFileSize = configPlupload.maxFileSize || "20mb";
	var maxFileCount = configPlupload.maxFileCount || 1;
	var multipleQueues = configPlupload.multipleQueues || true;
	var FileUploadedFun =  configPlupload.FileUploadedFun || {};
	
	var _this = {
		init : function() {
			$("#" + fileContainerId).plupload({
				// General settings
				runtimes: 'html5,flash,silverlight,html4',
				
				// Fake server response here 
				// url : '../upload.php',
				url: uploadUri,

				// Maximum file size
				max_file_size: maxFileSize,

				// User can upload no more then 20 files in one go (sets multiple_queues to false)
				max_file_count: maxFileCount,
				
				multiple_queues: multipleQueues,
				
				chunk_size: '1mb',

				// Resize images on clientside if we can
				//resize : {width: 1200,	height: 1200,quality: 90, crop: false	},

				// Specify what files to browse for
				filters: [
					{ title: "Image files", extensions: "jpg,bmp,gif,png,jpeg" },
					{ title: "Zip files", extensions:  "zip,avi" }
				],

				// Rename files by clicking on their titles
				rename: false,
				
				// Sort files
				sortable: false,

				// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
				dragdrop: true,

				// Views to activate
				views: {
					list: true,
					thumbs: true, // Show thumbs
					active: 'thumbs'
				},
			    
				 // Flash settings
			    flash_swf_url : urls['msUrl'] + "/js/plupload-2.1.8/js/Moxie.swf",
			 
			    // Silverlight settings
			    silverlight_xap_url : urls['msUrl'] + "/js/plupload-2.1.8/js/Moxie.xap",
			    
			    init :{
			    	 FileUploaded : function(up, file, info) {//文件上传完毕触发
			    		 if(jQuery.isFunction(FileUploadedFun)){
			    			 var response = $.parseJSON(info.response);
			    			 FileUploadedFun(response);
						}
			         }
			    }
			});

		}
	}
	return _this;
};



var PluploadObjSimple = function(configPlupload) {
	configPlupload = configPlupload || {};
	var fileContainerId = configPlupload.fileContainerId || "uploader";
	var uploadUri = configPlupload.uploadUri || urls['msUrl'] + "/file/upload.shtml";
	var maxFileSize = configPlupload.maxFileSize || "20mb";
	var maxFileCount = configPlupload.maxFileCount || 1;
	var multipleQueues = configPlupload.multipleQueues || true;
	var pickFilesBtn = configPlupload.pickFilesBtn || 'pickFilesBtn'	
	var uploadFilesBtn = configPlupload.uploadFilesBtn || 'uploadFilesBtn'
	var consolveInfo = configPlupload.consolveInfo || 'consolveInfo'
	var FileUploadedFun =  configPlupload.FileUploadedFun || {};
	
	var _this = {
		init : function() {
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : pickFilesBtn, // you can pass in id...
				container: document.getElementById(fileContainerId), // ... or DOM Element itself
				max_file_size : maxFileSize,

				// Fake server response here 
				url: uploadUri,
				
				 // Flash settings
			    flash_swf_url : urls['msUrl'] + "/js/plupload-2.1.8/js/Moxie.swf",
			 
			    // Silverlight settings
			    silverlight_xap_url : urls['msUrl'] + "/js/plupload-2.1.8/js/Moxie.xap",
				filters : [		
					{ title: "Image files", extensions: "jpg,bmp,gif,png,jpeg" },
					{ title: "Zip files", extensions:  "zip,avi" }
				],

				init: {
					PostInit: function() {
						$('#'+fileContainerId).html();
						$('#'+uploadFilesBtn).bind('click', function() {
							uploader.start();
							return false;
						});
					},

					FilesAdded: function(up, files) {
						plupload.each(files, function(file) {
							$('#'+fileContainerId).html('<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>');
						});
					},

					UploadProgress: function(up, file) {
						document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
					},

					Error: function(up, err) {
						$('#' + consolveInfo).html("\nError #" + err.code + ": " + err.message);
					}, 
					
					 FileUploaded : function(up, file, info) {//文件上传完毕触发
			    		 if(jQuery.isFunction(FileUploadedFun)){
			    			 var response = $.parseJSON(info.response);
			    			 FileUploadedFun(response);
						}
			         }
				}
			});

			uploader.init();

		}
	}
	return _this;
};


