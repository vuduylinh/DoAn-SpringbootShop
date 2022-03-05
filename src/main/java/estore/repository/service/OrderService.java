package estore.repository.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import estore.repository.Order;

public interface OrderService {

	Order getById(Long id);

	void create(Order item);

	void update(Order item);

	void deleteById(Long id);

	Page<Order> findPageByStatusId(Integer statusId, Pageable pageable);
}
