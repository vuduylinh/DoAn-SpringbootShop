package estore.service.cart;

import java.util.Collection;

public interface CartService {
	/**
	 * Đọc các mặt hàng trong giỏ
	 */
	Collection<CartItem> getItems();
	/**
	 * Thêm mặt hàng vào giỏ hoặc tăng số lượng lên 1 nếu đã tồn tại
	 * @param id mã mặt hàng
	 * @return mặt hàng đã được thêm vào hoặc cập nhật số lượng
	 */
	void add(Integer id);
	/**
	 * Xóa mặt hàng khỏi giỏ
	 * @param id mã mặt hàng
	 * @return mặt hàng đã bị xóa
	 */
	void remove(Integer id);
	/**
	 * Thay đổi số lượng mặt hàng trong giỏ
	 * @param id mã mặt hàng
	 * @param qty số lượng mới
	 * @return mặt hàng đã được cập nhật số lượng
	 */
	void update(Integer id, int qty);
	/**
	 * Xóa sạch các mặt hàng trong giỏ
	 */
	void clear();
	/**
	 * Lấy tổng số lượng các mặt hàng trong giỏ
	 */
	 int getCount();
	/**
	 * Tính tổng số tiền các mặt hàng trong giỏ
	 */
	double getAmount();
}
