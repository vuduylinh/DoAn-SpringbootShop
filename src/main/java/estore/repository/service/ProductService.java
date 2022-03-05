package estore.repository.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import estore.repository.Product;

public interface ProductService {

	Product getById(Integer id);

	void create(Product item);

	void deleteById(Integer id);

	List<Product> findAll();

	void update(Product item);

	Page<Product> findByCategoryId(Integer cid, Pageable pageable);
}
