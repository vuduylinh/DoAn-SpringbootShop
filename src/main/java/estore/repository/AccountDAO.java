package estore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountDAO extends JpaRepository<Account, String>{
	@Query("SELECT o FROM Account o "
			+ " WHERE (o.username LIKE ?1 OR o.email LIKE ?1 OR o.fullname LIKE ?1 OR o.mobile LIKE ?1 OR o.address LIKE ?1)")
	Page<Account> findAccountByKeyword(String keyword, Pageable pageable);
	
	@Query("SELECT o FROM Account o "
			+ " WHERE (o.username LIKE ?1 OR o.email LIKE ?1 OR o.fullname LIKE ?1 OR o.mobile LIKE ?1 OR o.address LIKE ?1)"
			+ " AND o.activated=?2")
	Page<Account> findAccountByKeywordAndActivated(String keyword, Boolean activated, Pageable pageable);
	
	@Query("SELECT o FROM Account o WHERE o.authorities IS EMPTY"
			+ " AND (o.username LIKE ?1 OR o.email LIKE ?1 OR o.fullname LIKE ?1 OR o.mobile LIKE ?1 OR o.address LIKE ?1)")
	Page<Account> findCustomerByKeyword(String keyword, Pageable pageable);
	
	@Query("SELECT o FROM Account o WHERE o.authorities IS EMPTY"
			+ " AND (o.username LIKE ?1 OR o.email LIKE ?1 OR o.fullname LIKE ?1 OR o.mobile LIKE ?1 OR o.address LIKE ?1)"
			+ " AND o.activated=?2")
	Page<Account> findCustomerByKeywordAndActivated(String keyword, Boolean activated, Pageable pageable);
	
	@Query("SELECT o FROM Account o WHERE o.authorities IS NOT EMPTY"
			+ " AND (o.username LIKE ?1 OR o.email LIKE ?1 OR o.fullname LIKE ?1 OR o.mobile LIKE ?1 OR o.address LIKE ?1)")
	Page<Account> findMasterByKeyword(String keyword, Pageable pageable);
	
	@Query("SELECT o FROM Account o WHERE o.authorities IS NOT EMPTY"
			+ " AND (o.username LIKE ?1 OR o.email LIKE ?1 OR o.fullname LIKE ?1 OR o.mobile LIKE ?1 OR o.address LIKE ?1)"
			+ " AND o.activated=?2")
	Page<Account> findMasterByKeywordAndActivated(String keyword, Boolean activated, Pageable pageable);
}