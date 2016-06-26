package com.baotoan.dev.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Loader {
	@SuppressWarnings("unchecked")
	public static Map<String, String> upload(String path,
			HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "false");

		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File fileDir = new File(path);
		fileFactory.setRepository(fileDir);
		ServletFileUpload uploader = new ServletFileUpload(fileFactory);

		if (!ServletFileUpload.isMultipartContent(request)) {
			return result;
		}
		try {
			List<FileItem> fileItems = uploader.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			while (iter.hasNext()) {
				FileItem fileItem = iter.next();
				if (!fileItem.isFormField()) {
					File file = new File(path + File.separator + fileItem.getName());
					fileItem.write(file);
					result.put("status", "true");
					result.put(fileItem.getFieldName(), file.getAbsolutePath());
					System.out.println("path: " + file.getAbsolutePath());
				} else {
					result.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
					System.out.println(fileItem.getFieldName() + " - " + fileItem.getString("UTF-8"));
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}
}
