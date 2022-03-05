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
	public void add(Integer id) {
		CartItem item = map.get(id);
		if(item == null) {
			Product product = dao.getById(id);
			item = new CartItem(product, 1);
			map.put(id, item);
		} else {
			item.increase();
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
}