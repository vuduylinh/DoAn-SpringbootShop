package estore.service.session;

public interface SessionService {
	/**
	 * Đọc attribute từ session
	 * @param name tên attribute
	 * @param defval giá trị mặc định
	 * @return giá trị attribute hoặc defval nếu attribute ko tồn tại
	 */
	<T> T get(String name, T defval);
	/**
	 * Tạo hoặc thay thế attribute trong session
	 * @param name tên attribute
	 * @param value giá trị attribute
	 */
	void set(String name, Object value);
	/**
	 * Xóa attribute khỏi session
	 * @param name tên attribute cần xóa
	 */
	void remove(String name);
}
