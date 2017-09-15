package com.jeecg.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import biz.page.members.MembersPage;

import com.base.annotation.Auth;
import com.base.util.Constant;
import com.base.util.HtmlUtil;
import com.base.util.SessionUtils;
import com.base.util.json.JSONUtil;
import com.base.web.BaseAction;
import com.base.web.MemCachedUtil;
import com.jeecg.entity.FileUploaded;

@Controller
@RequestMapping("/file")
public class FileUploadAction extends BaseAction {
	private static final String FILES = "files";
	private final static Logger log = Logger.getLogger(FileUploadAction.class);

	private SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	

	SimpleDateFormat yearMonth = new SimpleDateFormat("yyyyMM");


	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/demo1") 
	public ModelAndView  demo1(MembersPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/file/demo1",context); 
	}
	

	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/demo4") 
	public ModelAndView  demo4(MembersPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/file/demo4",context); 
	}
	
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/demo2") 
	public ModelAndView  demo2(MembersPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/file/demo2",context); 
	}
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/cropperEdit") 
	public ModelAndView  cropperEdit(MembersPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/file/cropper-edit",context); 
	}
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/fileUpload") 
	public ModelAndView  fileUpload(MembersPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/file/file-upload",context); 
	}
	
	/**
	 * 
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception 
	 */
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/demo3") 
	public ModelAndView  demo3(MembersPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/file/demo3",context); 
	}
	
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/demo5") 
	public ModelAndView  demo5(MembersPage page,HttpServletRequest request) throws Exception{
		Map<String,Object>  context = getRootMap();
		return forword("biz/file/demo5",context); 
	}
	
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/uploadTmp")
	public void uploadTmp(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String ymd = yearMonth.format(new Date());
		
		String fullPath = SessionUtils.getServletContextPath() + "/" + Constant.UPLOAD_FOLDER_TMP + "/" + ymd + "/";
		String contextPath = "/" + Constant.UPLOAD_FOLDER_TMP + "/" + ymd + "/";

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		// 创建文件夹
		File file = new File(fullPath);
		if (!file.exists()) {
			file.mkdirs();
		}
				
		String fileName = null;
		ArrayList<FileUploaded> fileList = new ArrayList<FileUploaded>();
		boolean flag = true;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String fileExt = FilenameUtils.getExtension(fileName).toUpperCase();

			log.debug("File Name: " + fileName);

			String newFileName = simpleDateTimeFormat.format(new Date()) + "_" + new Random().nextInt(1000) + "_O."
					+ fileExt;
			File uploadFile = new File(fullPath, newFileName);
			
			log.debug("File saved under: " + fullPath + newFileName);

			// 返回给前端显示
			FileUploaded fileUp = new FileUploaded();
			fileUp.setId(MemCachedUtil.getInstance().generateTblKey(this.getEntityName()));
			fileUp.setOldName(fileName);
			fileUp.setCtxFilePath(contextPath + newFileName);
			fileUp.setNewName(newFileName);
			fileUp.setExtension(FilenameUtils.getExtension(newFileName));
			fileUp.setOldNameWithoutExt(FilenameUtils.getBaseName(newFileName));

			fileList.add(fileUp);
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);
			} catch (IOException e) {
				e.printStackTrace();
				flag = false;
			}
		}

		result.put(FILES, fileList);

		if (flag) {
			result.put(SUCCESS, true);
			result.put(MSG, "上传成功");// "上传成功"
		} else {
			result.put(SUCCESS, false);
			result.put(MSG, "上传失败");// "上传失败"
		}

		HtmlUtil.writerJson(response, result);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/cropperUpload")
	public void cropperUpload(FileUploaded fileUploaded, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		//检查是否设定了目标目录
		String targetFld = fileUploaded.getTargetFld();

		if (StringUtils.isBlank(targetFld)){
			targetFld = "";
		}
		String fullPath = SessionUtils.getServletContextPath() + "/" + Constant.UPLOAD_FOLDER + "/" + targetFld + "/";
		String contextPath = "/" + Constant.UPLOAD_FOLDER + "/" + targetFld + "/";

		fileUploaded.setId(String.valueOf((int)(Math.random() * 100000))); //Setup a temporary id

		// 创建文件夹
		File file = new File(fullPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		//获取临时文件
		String fileTemp = fileUploaded.getCtxFilePath();
		String fileExtension = FilenameUtils.getExtension(fileTemp);
		String newFileName = simpleDateTimeFormat.format(new Date()) + "_" + new Random().nextInt(1000) + "_O."
				+ fileExtension;;
		
		File preUploadeFile = new File(SessionUtils.getServletContextPath() + File.separator + fileUploaded.getCtxFilePath());
		
		// 对图片文件生成标准大小图形
		BufferedImage image = ImageIO.read(preUploadeFile);
		log.debug("Pre Image:" + fileUploaded.getCtxFilePath());
		log.debug("Target Folder:" + contextPath);
		log.debug("id:" + fileUploaded.getId());
		log.debug("X:" + (int)fileUploaded.getCropX());
		log.debug("Y:" + (int)fileUploaded.getCropY());
		log.debug("Width:" + (int)fileUploaded.getCropWidth());
		log.debug("Height:" + (int)fileUploaded.getCropHeight());
		log.debug("New Image:" + newFileName);
		BufferedImage thumbnail = Scalr.crop(image, (int)fileUploaded.getCropX(), (int)fileUploaded.getCropY(), (int)fileUploaded.getCropWidth(), (int)fileUploaded.getCropHeight(), Scalr.OP_ANTIALIAS);
		ImageIO.write(thumbnail, fileExtension, new File(fullPath, newFileName));

		//由临时文件根据CROP参数生成的新文件
		File newUploaFile = new File(fullPath + File.separator + newFileName);
		
		//根据新生成的文件生成相关缩略图
		this.generateThumbNailFromBase(newUploaFile, fullPath, newFileName);

		fileUploaded.setCtxFilePath(contextPath + newFileName);
		
		result.put(SUCCESS, true);
		result.put("data", fileUploaded);
		result.put(MSG, "上传成功");// "上传成功"

		HtmlUtil.writerJson(response, result);
	}
	
	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String targetFld = request.getParameter("targetFld");
		if (targetFld == null){
			targetFld = "DEFAULT";
		}
				
		String fullPath = SessionUtils.getServletContextPath() + "/" + Constant.UPLOAD_FOLDER + "/" + targetFld + "/";
		String contextPath = "/" + Constant.UPLOAD_FOLDER + "/" + targetFld + "/";

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		log.debug("File Count:" + fileMap.size());
		// 创建文件夹
		File file = new File(fullPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		ArrayList<FileUploaded> fileList = new ArrayList<FileUploaded>();
		boolean flag = true;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String fileExt = FilenameUtils.getExtension(fileName).toUpperCase();

			log.debug("File Name: " + fileName);

			String newFileName = simpleDateTimeFormat.format(new Date()) + "_" + new Random().nextInt(1000) + "_O."
					+ fileExt;
			File uploadFile = new File(fullPath, newFileName);
			
			log.debug("File saved under: " + fullPath + newFileName);

			// 返回给前端显示
			FileUploaded fileUp = new FileUploaded();
			fileUp.setId(MemCachedUtil.getInstance().generateTblKey(this.getEntityName()));
			fileUp.setOldName(fileName);
			fileUp.setCtxFilePath(contextPath + newFileName);
			fileUp.setNewName(newFileName);
			fileUp.setExtension(FilenameUtils.getExtension(newFileName));
			fileUp.setOldNameWithoutExt(FilenameUtils.getBaseName(newFileName));

			fileList.add(fileUp);
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);

				// 对图片文件生成标准大小图形
				this.generateThumbNailFromBase(uploadFile, fullPath, newFileName);
			} catch (IOException e) {
				e.printStackTrace();
				flag = false;
			}
		}

		result.put(FILES, fileList);

		if (flag) {
			result.put(SUCCESS, true);
			result.put(MSG, "上传成功");// "上传成功"
		} else {
			result.put(SUCCESS, false);
			result.put(MSG, "上传失败");// "上传失败"
		}

		HtmlUtil.writerJson(response, result);
	}
	

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/uploadForSummernote")
	public void uploadForSummernote(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String targetFld = request.getParameter("targetFld");
		if (targetFld == null){
			targetFld = "DEFAULT";
		}
				
		String fullPath = SessionUtils.getServletContextPath() + "/" + Constant.UPLOAD_FOLDER + "/" + targetFld + "/";
		String contextPath = "/" + Constant.UPLOAD_FOLDER + "/" + targetFld + "/";

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		log.debug("File Count:" + fileMap.size());
		// 创建文件夹
		File file = new File(fullPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = null;
		ArrayList<FileUploaded> fileList = new ArrayList<FileUploaded>();
		boolean flag = true;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			String fileExt = FilenameUtils.getExtension(fileName).toUpperCase();

			log.debug("File Name: " + fileName);

			String newFileName = simpleDateTimeFormat.format(new Date()) + "_" + new Random().nextInt(1000) + "_O."
					+ fileExt;
			File uploadFile = new File(fullPath, newFileName);
			
			log.debug("File saved under: " + fullPath + newFileName);

			// 返回给前端显示
			FileUploaded fileUp = new FileUploaded();
			fileUp.setId(MemCachedUtil.getInstance().generateTblKey(this.getEntityName()));
			fileUp.setOldName(fileName);
			fileUp.setCtxFilePath(contextPath + newFileName);
			fileUp.setNewName(newFileName);
			fileUp.setExtension(FilenameUtils.getExtension(newFileName));
			fileUp.setOldNameWithoutExt(FilenameUtils.getBaseName(newFileName));

			fileList.add(fileUp);
			try {
				FileCopyUtils.copy(mf.getBytes(), uploadFile);

				// 对图片文件生成标准大小图形
				this.generateThumbNailFromBase(uploadFile, fullPath, newFileName);
			} catch (IOException e) {
				e.printStackTrace();
				flag = false;
			}
		}

		result.put(FILES, fileList);

		if (flag) {
			result.put(SUCCESS, true);
			result.put(MSG, "上传成功");// "上传成功"
		} else {
			result.put(SUCCESS, false);
			result.put(MSG, "上传失败");// "上传失败"
		}

		HtmlUtil.writerHtml(response, result);
	}

	@Auth(verifyLogin = false, verifyURL = false)
	@RequestMapping("/plupload")
	public void plupload(FileUploaded fileUploaded, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		//检查是否设定了目标目录
		String targetFld = fileUploaded.getTargetFld();
		if (StringUtils.isBlank(targetFld)){
			targetFld = "";
		}
		//上传文件的类型，包括AUDIO,VIDEO,IMAGE,FILE
		String targetFileType = request.getParameter("targetFileType");
		String fullPath = SessionUtils.getServletContextPath() + "/" + Constant.UPLOAD_FOLDER + "/" + targetFld + "/";
		String contextPath = "/" + Constant.UPLOAD_FOLDER + "/" + targetFld + "/";

		// 创建文件夹
		File file = new File(fullPath);
		if (!file.exists()) {
			file.mkdirs();
		}

		response.setCharacterEncoding("UTF-8");
		Integer chunk = null;// 分割块数
		Integer chunks = null;// 总分割数
		String tempFileName = null;// 临时文件名
		String newFileName = null;// 最后合并后的新文件名
		BufferedOutputStream outputStream = null;
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				ArrayList<FileUploaded> fileList = new ArrayList<FileUploaded>();

				// upload.setSizeMax(5 * 1024 * 1024);// 设置附件最大大小，超过这个大小上传会不成功
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

				Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

				String fileName = null;
				for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
					// 上传文件名
					MultipartFile mf = entity.getValue();

					CommonsMultipartFile citem = (CommonsMultipartFile) entity.getValue();
					FileItem item = citem.getFileItem();

					tempFileName = multipartRequest.getParameter("name");
					if (StringUtils.isNotBlank(multipartRequest.getParameter("chunk"))){
						chunk = Integer.parseInt(multipartRequest.getParameter("chunk"));
					}
					if (StringUtils.isNotBlank(multipartRequest.getParameter("chunks"))){
						chunks = Integer.parseInt(multipartRequest.getParameter("chunks"));
					}
					
					log.debug("tempFileName: " + tempFileName);
					log.debug("chunk: " + chunk);
					log.debug("chunks: " + chunks);
					if (tempFileName != null) {
						String chunkName = tempFileName;
						if (chunk != null) {
							chunkName = chunk + "_" + tempFileName;
						}
						File savedFile = new File(fullPath, chunkName);
						item.write(savedFile);
					}
				}
				
				String fileExt = FilenameUtils.getExtension(tempFileName).toUpperCase();

				newFileName = simpleDateTimeFormat.format(new Date()) + "_" + new Random().nextInt(1000)
						+ "_O.".concat(fileExt);
				if (chunk != null && chunk + 1 == chunks || chunk == null && chunks == null) {
					File newFile = new File(fullPath, newFileName);
					outputStream = new BufferedOutputStream(new FileOutputStream(newFile));
					
					if (chunk != null && chunk + 1 == chunks){
						// 遍历文件合并
						for (int i = 0; i < chunks; i++) {
							File tempFile = new File(fullPath, i + "_" + tempFileName);
							byte[] bytes = FileUtils.readFileToByteArray(tempFile);
							outputStream.write(bytes);
							outputStream.flush();
							tempFile.delete();
						}
					}else{
						File tempFile = new File(fullPath, tempFileName);
						byte[] bytes = FileUtils.readFileToByteArray(tempFile);
						outputStream.write(bytes);
						outputStream.flush();
						tempFile.delete();
					}
					outputStream.flush();

					log.debug("File saved under: " + fullPath + newFileName);
					
					// 对图片文件生成标准大小图形
					this.generateThumbNailFromBase(newFile, fullPath, newFileName);

					// 返回给前端显示
					FileUploaded fileUp = new FileUploaded();
					fileUp.setId(MemCachedUtil.getInstance().generateTblKey(this.getEntityName()));
					fileUp.setOldName(fileName);
					fileUp.setCtxFilePath(contextPath + newFileName);
					fileUp.setNewName(newFileName);
					fileUp.setExtension(FilenameUtils.getExtension(newFileName));
					fileUp.setOldNameWithoutExt(FilenameUtils.getBaseName(newFileName));

					fileList.add(fileUp);
					result.put(SUCCESS, true);
					result.put(FILES, fileList);
				}else if (chunk == null && chunks == null){ //未分块
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				result.put(SUCCESS, false);
			} finally {
				try {
					if (outputStream != null)
						outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			HtmlUtil.writerJson(response, result);
		}
	}

	/**
	 * 生成缩略图
	 */
	private void generateThumbNailFromBase(File originalFile, String filePath, String fileName) {
		String fileExt = FilenameUtils.getExtension(fileName);
		// 对图片文件生成标准大小图形
		if ("JPEG".equals(fileExt) || "JPG".equals(fileExt) || "GIF".equals(fileExt)
				|| "PNG".equals(fileExt) || "BMP".equals(fileExt)) {

			try {
				BufferedImage image = ImageIO.read(originalFile);

				// 缩略图-小号
				String fileNameSmall = fileName.replace("_O.", "_S.");
				BufferedImage thumbnail = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
						Constant.IMAGE_SCALE_W_SMALL, Constant.IMAGE_SCALE_H_SMALL, Scalr.OP_ANTIALIAS);
				ImageIO.write(thumbnail, fileExt, new File(filePath, fileNameSmall));

				// 缩略图-中号
				String fileNameMedium = fileName.replace("_O.", "_M.");
				thumbnail = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
						Constant.IMAGE_SCALE_W_MEDIUM, Constant.IMAGE_SCALE_H_MEDIUM, Scalr.OP_ANTIALIAS);
				ImageIO.write(thumbnail, fileExt, new File(filePath, fileNameMedium));

				// 缩略图-大号
				String fileNameLarge = fileName.replace("_O.", "_L.");
				thumbnail = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH,
						Constant.IMAGE_SCALE_W_LARGE, Constant.IMAGE_SCALE_H_LARGE, Scalr.OP_ANTIALIAS);
				ImageIO.write(thumbnail, fileExt, new File(filePath, fileNameLarge));

				// 缩略图-图标
				String fileNameIcon = fileName.replace("_O.", "_I.");
				thumbnail = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, Constant.IMAGE_SCALE_W_ICON, Constant.IMAGE_SCALE_H_ICON, Scalr.OP_ANTIALIAS);
				ImageIO.write(thumbnail, fileExt, new File(filePath, fileNameIcon));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
