package estore.repository.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import estore.repository.Order;
import estore.repository.OrderDAO;
import estore.repository.OrderDetail;
import estore.repository.OrderDetailDAO;
import estore.service.cart.CartService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;

	@Autowired
	OrderDetailDAO ddao;
	
	@Override
	public Order getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void create(Order item) {
		dao.save(item);
	}

	@Override
	public void update(Order item) {
		dao.save(item);
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public Page<Order> findPageByStatusId(Integer statusId, Pageable pageable) {
		return dao.findPageByStatusId(statusId, pageable);
	}

	
	@Transactional
	@Override
	public void create(Order order, CartService cartService) {
		// TODO Auto-generated method stub
		dao.save(order); //insert 1 bang ghi va bang order
		
		// lay ra cac cart item doi sang String
		List<OrderDetail> list = cartService.getItems().stream().map(item -> {
		return	new OrderDetail(order, item.getProduct(), item.getQty());
		}).collect(Collectors.toList());
		ddao.saveAll(list); // insert nhieu bang ghi vao trong orderDetails
	}

	@Override
	public List<Order> findByUsername(String username) {
			
		return dao.findByUsername(username);
	}
}
