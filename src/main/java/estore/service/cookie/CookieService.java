package estore.service.cookie;

import java.util.List;

import javax.servlet.http.Cookie;

import estore.utils.ENDE;
import estore.utils.JSON;

public interface CookieService {
	/**
	 * Đọc cookie
	 * @param name tên cookie
	 * @return cookie hoặc null nếu không tồn tại
	 */
	Cookie get(String name);
	/**
	 * Đọc giá trị cookie
	 * @param name tên cookie
	 * @return giá trị cookie hoặc "" nếu không tồn tại
	 */
	String getValue(String name, String defval);
	/**
	 * Tạo và gửi cookie về client
	 * @param name tên cookie
	 * @param value giá trị cookie
	 * @param days số ngày tồn tại
	 * @return cookie đã tạo
	 */
	Cookie create(String name, String value, int days);
	/**
	 * Xóa cookie
	 * @param name tên cookie
	 */
	void delete(String name);
	
	default Cookie createEncode(String name, String value, int days) {
		return this.create(name, ENDE.encode(value), days);
	}
	
	default String getDecodedValue(String name, String defval) {
		return ENDE.decode(this.getValue(name, ENDE.encode(defval)));
	}
	
	default List<Integer> getVisitIds(){
		String visits = this.getDecodedValue("visits", "[]");
		return JSON.parse(visits);
	}
	
	default List<Integer> addVisitId(Integer id){
		List<Integer> ids = this.getVisitIds();
		if(!ids.contains(id)) {
			ids.add(id);
		}
		this.createEncode("visits", JSON.stringify(ids), 5);
		return ids;
	}
}
