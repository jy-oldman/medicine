package com.jy.medicine.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
@Component
public class DBUtil {
	/**
	 * 将指定的字符串进行倒转
	 * 
	 * @param s
	 *            要转换的字符串
	 * @return
	 */
	public static String spiltRtoL(String s) {
		StringBuffer sb = new StringBuffer();
		int length = s.length();
		char[] c = new char[length];
		for (int i = 0; i < length; i++) {
			c[i] = s.charAt(i);
		}
		for (int i = length - 1; i >= 0; i--) {
			sb.append(c[i]);
		}
		return sb.toString();
	}

	/**
	 * 上传图片工具类
	 * 
	 * @param imFile
	 *            文件路径
	 * @return
	 */
	public static String upLoadFile(MultipartFile imFile) {
		String img = "";
		String oldName = imFile.getOriginalFilename();
		File file = null;
		try {
			File path = new File(ResourceUtils.getURL("").getPath());
			File upload = new File(path.getAbsolutePath(), "static/upload/");
			if (!upload.exists()) {
				upload.mkdirs();
			}
			String uploadPath = upload + "\\";
			file = new File(uploadPath + oldName);
			try {
				imFile.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String path1 = file.toString().replace("\\", "/");
			int index = path1.indexOf("static");
			img = path1.substring(index + 6);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		return img;
	}

	public static Paging getPaging(Paging paging, List<?> list, Map<String, Object> map) {
		// 取出总记录数
		int rowCount = (int) map.get("rowCount");
		// 取出总页数
		int pageCount = (int) map.get("pageCount");
		paging.setRowCount(rowCount);
		paging.setPageCount(pageCount);
		if (paging.getPageIndex() == 1) {// 如果当前页是第一页，上一页还是第一页
			paging.setPrePage(paging.getPageIndex());
		} else {
			paging.setPrePage(paging.getPageIndex() - 1);
		}
		if (paging.getPageIndex() == pageCount) {// 如果当前页是最后一页，下一页还是最后一页
			paging.setNextPage(pageCount);
		} else {
			paging.setNextPage(paging.getPageIndex() + 1);
		}
		List<Object> list2 = new ArrayList<>();
		for (Object o : list) {
			list2.add(o);
		}
		paging.setList(list2);
		return paging;
	}
}
