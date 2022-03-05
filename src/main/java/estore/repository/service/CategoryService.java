package estore.repository.service;

import java.util.List;

import estore.repository.Category;

public interface CategoryService {

	List<Category> findAll();

	Category getById(Integer id);

	void create(Category item);

	void update(Category item);

	void deleteById(Integer id);
}
