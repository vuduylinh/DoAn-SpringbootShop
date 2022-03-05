package estore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	@Query("SELECT o FROM Product o WHERE o.category.id=?1")
	Page<Product> findByCategoryId(Integer cid, Pageable pageable);
}
