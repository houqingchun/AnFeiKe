function submitFormWithBtn() {
	$('#description').val($('#descriptionEditor').summernote('code'));
	$('#editForm').submit();
}

function setCurrentImgAsMaster(basePath, imgPath){
	$('#photoPathDisplay').attr('src', basePath + imgPath.replace('_O.', '_M.'));
	$('#photoPath').val(imgPath);
}
