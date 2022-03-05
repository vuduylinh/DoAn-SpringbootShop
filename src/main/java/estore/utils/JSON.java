package estore.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
	static ObjectMapper mapper = new ObjectMapper();
	/**
	 * Chuyển đổi JSON String thành Object
	 * @param jsonText là chuỗi json cần chuyển đổi
	 * @return đối tượng đã chuyển đổi
	 * @exception RuntimeException lỗi chuyển đổi
	 */
	public static <T> T parse(String jsonText) {
		try {
			return mapper.readValue(jsonText, new TypeReference<T>() {});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * Chuyển đổi Object thành JSON String
	 * @param object là đối tượng cần chuyển đổi
	 * @return JSON String nhận được
	 * @exception RuntimeException lỗi chuyển đổi
	 */
	public static String stringify(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}