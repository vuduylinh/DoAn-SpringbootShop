package estore.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estore.repository.Category;
import estore.repository.CategoryDAO;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO dao;

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}

	@Override
	public Category getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void create(Category item) {
		dao.save(item);
	}

	@Override
	public void update(Category item) {
		dao.save(item);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
}
