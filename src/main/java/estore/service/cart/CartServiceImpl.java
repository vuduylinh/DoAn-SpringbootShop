package estore.service.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import estore.repository.Product;
import estore.repository.ProductDAO;

@SessionScope 
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	ProductDAO dao;

	Map<Integer, CartItem> map = new HashMap<>();
	
	@Override
	public Collection<CartItem> getItems() {
		return map.values();
	}

	@Override
	public void add(Integer id) { // đưa id vào
		CartItem item = map.get(id); // vào trong map lấy id của mh đó
		if(item == null) { // nếu item = null thì lấy data từ db
			Product product = dao.getById(id);
			item = new CartItem(product, 1);
			map.put(id, item); // sau đó bỏ vào map vs sl = 1
		} else {
			item.increase(); // tăng số lượng lên 1
		}
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	@Override
	public void update(Integer id, int qty) {
		CartItem item = map.get(id);
		item.setQty(qty);
	}

	@Override
	public void clear() {
		map.clear();
	}
	
	public int getCount() {
		return this.getItems().stream() //
			.mapToInt(item -> item.getQty())
			.sum();
	}
	
	public double getAmount() {
		return this.getItems().stream() // duyệt qua từng items
			.mapToDouble(item -> item.getAmount()) // cộng tộng số lượng
			.sum();
	}
	
}