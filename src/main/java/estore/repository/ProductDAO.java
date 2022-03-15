package estore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	@Query("SELECT o FROM Product o WHERE o.category.id=?1")
	Page<Product> findByCategoryId(Integer cid, Pageable pageable);

	
	@Query("SELECT o FROM Product o WHERE o.name LIKE ?1"
			+ " OR o.category.name LIKE ?1"
			+ " OR o.category.nameVn LIKE ?1") // LIKE ?1 -> LILE ?keywords
	// truy vấn tên sản phẩm từ bảng product và bảng category có tên đó.
	Page<Product> findByKeywords(String keywords, Pageable pageable);


	@Query("SELECT o FROM Product o WHERE  o.discount > 0") // tìm ra các SP khuyến mãi
	Page<Product> findByDisconut(Pageable pageable);

	
	@Query("SELECT o FROM Product o WHERE  o.likeCount > 0 ORDER BY o.likeCount DESC")
	Page<Product> findByFavorite(Pageable pageable);// tìm ra các SP yêu thích nhất.


	@Query("SELECT o FROM Product o ORDER BY o.productDate DESC") // tìm ra sản phẩm mới
	Page<Product> findByLatest(Pageable pageable);
	
	@Query("SELECT o FROM Product o WHERE o.special=true") // tìm ra sp đặc biệt
	Page<Product> findBySpecial(Pageable pageable);


	@Query("SELECT o FROM Product o WHERE o.shares IS NOT EMPTY ORDER BY size(o.shares) DESC") // tìm ra sp share nhiều
	Page<Product> findByShare(Pageable pageable);
	
}
