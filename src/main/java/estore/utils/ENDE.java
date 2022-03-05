package estore.utils;

import java.util.Base64;

public class ENDE {	
	/**
	 * Mã hóa chuỗi dạng Base64
	 * @param text là chuỗi cần mã hóa
	 * @return chuỗi đã được mã hóa
	 */
	public static String encode(String text) {
		return Base64.getEncoder().encodeToString(text.getBytes());
	}
	/**
	 * Giải mã chuỗi dạng Base64
	 * @param encodeText là chuỗi cần giải mã
	 * @return chuỗi đã được giải mã
	 */
	public static String decode(String encodeText) {
		return new String(Base64.getDecoder().decode(encodeText.getBytes()));
	}
}