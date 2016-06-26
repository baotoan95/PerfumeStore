package com.baotoan.dev.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class GenerateCode {
//	public static String md5(String srcText) {
//		String enrText = "";
//		try {
//			MessageDigest msd = MessageDigest.getInstance("MD5"); // Thuật toán md5
//			byte[] srcTextBytes = srcText.getBytes("UTF-8"); // Chuyển chuỗi gốc thành mảng byte
//			byte[] enrTextBytes = msd.digest(srcTextBytes); // Tiến hành mã hóa
//			BigInteger bigInt = new BigInteger(1, enrTextBytes);
//			enrText = bigInt.toString(16); // Chuyển qua hệ 16 (Hexa)
//			
//		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return enrText;
//	}

	public static String md5(String str) {
		try {
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			// Update input string in message digest
			digest.update(str.getBytes(), 0, str.length());
			// Converts message digest value in base 16 (hex)
			return new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static String generate(int length) {
		String result = "";
		while (result.length() <= length) {
			Random rand = new Random();
			int numb = rand.nextInt(113);
			if (numb >= 48 && numb <= 57 || numb >= 64 && numb <= 90
					|| numb >= 97 && numb <= 112) {
				result += (char) numb;
			}
		}
		return result;
	}

	public static String generateHTML(int totalRecordResult,
			int numPageNeedShow, int numRecordPerPage, int currentPage) {
		int totalPageResult = totalRecordResult / numRecordPerPage;
		if (totalRecordResult % numRecordPerPage != 0) {
			totalPageResult++;
		}

		int totalSegment = totalPageResult / numPageNeedShow;
		if (totalPageResult % numPageNeedShow != 0) {
			totalSegment++;
		}

		if (currentPage > totalPageResult) {
			currentPage = totalPageResult;
		}

		int currentSegment = currentPage / numPageNeedShow;
		if (currentPage % numPageNeedShow != 0) {
			currentSegment++;
		}

		int min = ((currentSegment - 1) * numPageNeedShow) + 1;
		int max = min + numPageNeedShow <= totalPageResult ? min
				+ numPageNeedShow - 1 : totalPageResult;

		return html(min, max, currentPage, currentSegment, totalSegment);
	}

	private static String html(int min, int max, int currentPage,
			int currentSegment, int totalSegment) {
		String html = "";

		for (int i = min; i <= max; i++) {
			if (i == currentPage) {
				html += "<a href='page="+i+"'><li class='active'>" + i + "</li></a>";
			} else {
				html += "<a href='page="+i+"'><li>" + i + "</li></a>";
			}
		}

		if (currentSegment == 1) { // Đầu
			if (currentSegment < totalSegment) {
				html += "<a href='page=" + (max + 1) + "'><li>&raquo;</li></a>";
			}
		} else if (currentSegment == totalSegment) { // Cuối
			if (currentSegment > 1) {
				html = "<a href='page=" + (min - 1) + "'><li>&laquo;</li></a>" + html;
			}
		} else { // Giữa (tiến + lùi)
			html = "<a href='page="
					+ (min - 1) + "'><li>&laquo;</li></a>" + html
					+ "<a href='page="
					+ (max + 1) + "'><li>&raquo;</li></a>";
		}
		return html;
	}
}
