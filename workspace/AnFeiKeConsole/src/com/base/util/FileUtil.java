package com.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

public class FileUtil {

	/**
	 * 将指定资源文件压缩为zip
	 * 
	 * @param sourceFilePaths
	 * @param targetZipFilePath
	 * @return
	 */
	public static boolean zipFiles(String[] sourceFilePaths, String targetZipFilePath) {
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];

		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetZipFilePath));

			// Compress the files
			for (int i = 0; i < sourceFilePaths.length; i++) {
				FileInputStream in = new FileInputStream(sourceFilePaths[i]);

				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(sourceFilePaths[i]));

				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				// Complete the entry
				out.closeEntry();
				in.close();
			}

			// Complete the ZIP file
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean zipFiles(File[] sourceFiles, String targetZipFilePath) {
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];

		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetZipFilePath));
			// Compress the files
			for (int i = 0; i < sourceFiles.length; i++) {
				File f = sourceFiles[i];
				FileInputStream in = new FileInputStream(f);

				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(f.getName()));

				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				// Complete the entry
				out.closeEntry();
				in.close();
			}

			// Complete the ZIP file
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		// String targetZipFileName = "C:\\test测试.zip";
		// Collection allFiles = FileUtils.listFiles(new
		// File("C:\\Users\\qhou\\Desktop\\temp"),
		// new String[] { "xls", "txt" }, false);
		// FileUtil.zipFiles(FileUtils.convertFileCollectionToFileArray(allFiles),
		// targetZipFileName);

		FileUtils.deleteDirectory(new File("d:/eff/"));
		FileUtils.copyDirectory(new File("d:/abc"), new File("d:/eff/abc"));

	}
}
