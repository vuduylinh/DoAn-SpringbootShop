package estore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDAO extends JpaRepository<Order, Long>{
	@Query("SELECT o FROM Order o WHERE o.status.id=?1")
	Page<Order> findPageByStatusId(Integer statusId, Pageable pageable);

	
	@Query("SELECT o FROM Order o WHERE o.account.username=?1") // truy vấn lấy ra đơn hàng theo id user
	List<Order> findByUsername(String username);
}
