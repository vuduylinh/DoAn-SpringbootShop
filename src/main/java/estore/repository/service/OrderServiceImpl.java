package estore.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import estore.repository.Order;
import estore.repository.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderDAO dao;

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
}
