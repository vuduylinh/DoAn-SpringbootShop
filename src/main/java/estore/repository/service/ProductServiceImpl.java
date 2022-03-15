package estore.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import estore.repository.Product;
import estore.repository.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO dao;

	@Override
	public Product getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void create(Product item) {
		dao.save(item);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public void update(Product item) {
		dao.save(item);
	}

	@Override
	public Page<Product> findByCategoryId(Integer cid, Pageable pageable) {
		return dao.findByCategoryId(cid, pageable);
	}

	@Override
	public Page<Product> findByKeywords(String keywords, Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findByKeywords("%"+keywords+"%",pageable);
	}

	@Override
	public Page<Product> findByDiscount(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findByDisconut(pageable);
	}

	@Override
	public Page<Product> findByFavorite(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findByFavorite(pageable);
	}

	@Override
	public Page<Product> findByLatest(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findByLatest(pageable);
	}
	
	@Override
	public Page<Product> findBySpecial(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findBySpecial(pageable);
	}

	@Override
	public Page<Product> findByShare(Pageable pageable) {
		// TODO Auto-generated method stub
		return dao.findByShare(pageable);
	}
}
