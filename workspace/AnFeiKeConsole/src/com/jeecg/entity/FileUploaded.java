package com.jeecg.entity;

public class FileUploaded {
	private String id;//临时GUID
	private String oldName; //原有文件名
	private String oldNameWithoutExt; //原有文件名，不包括扩展名
	private String newName; //新文件名
	private double size; //大小KB
	private String ctxFilePath;//文件上下文完整路径，包括文件名
	private String extension; //扩展名
	
	private double cropX;
	private double cropY;
	private double cropWidth;
	private double cropHeight;
	private double cropRotate;
	private double cropScaleX;
	private double cropScaleY;
	private String targetFld;//目标存放目录
	
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getCtxFilePath() {
		return ctxFilePath;
	}
	public void setCtxFilePath(String ctxFilePath) {
		this.ctxFilePath = ctxFilePath;
	}
	public String getOldNameWithoutExt() {
		return oldNameWithoutExt;
	}
	public void setOldNameWithoutExt(String oldNameWithoutExt) {
		this.oldNameWithoutExt = oldNameWithoutExt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getCropX() {
		return cropX;
	}
	public void setCropX(double cropX) {
		this.cropX = cropX;
	}
	public double getCropY() {
		return cropY;
	}
	public void setCropY(double cropY) {
		this.cropY = cropY;
	}
	public double getCropWidth() {
		return cropWidth;
	}
	public void setCropWidth(double cropWidth) {
		this.cropWidth = cropWidth;
	}
	public double getCropHeight() {
		return cropHeight;
	}
	public void setCropHeight(double cropHeight) {
		this.cropHeight = cropHeight;
	}
	public double getCropRotate() {
		return cropRotate;
	}
	public void setCropRotate(double cropRotate) {
		this.cropRotate = cropRotate;
	}
	public double getCropScaleX() {
		return cropScaleX;
	}
	public void setCropScaleX(double cropScaleX) {
		this.cropScaleX = cropScaleX;
	}
	public double getCropScaleY() {
		return cropScaleY;
	}
	public void setCropScaleY(double cropScaleY) {
		this.cropScaleY = cropScaleY;
	}
	public String getTargetFld() {
		return targetFld;
	}
	public void setTargetFld(String targetFld) {
		this.targetFld = targetFld;
	}
}
