package estore.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportDAO extends JpaRepository<Order, Long>{
	@Query("SELECT o.name AS group, o.likeCount AS count "
			+ " FROM Product o "
			+ " WHERE o.likeCount > 0"
			+ " ORDER BY o.likeCount DESC")
	List<Report> getTopLikes(Pageable pageable);
	
	@Query("SELECT o.name AS group, size(o.shares) AS count"
			+ " FROM Product o "
			+ " WHERE o.shares IS NOT EMPTY")
	List<Report> getTopShares(Pageable pageable);

	@Query("SELECT o.category.name AS group,"
			+ " SUM(o.quantity) AS count,"
			+ " SUM(o.quantity * o.unitPrice) AS sum,"
			+ " MIN(o.unitPrice) AS min, "
			+ " MAX(o.unitPrice) AS max, "
			+ " AVG(o.unitPrice) AS avg"
			+ " FROM Product o "
			+ " GROUP BY o.category.name")
	List<Report> getInventoryByCategory();

	@Query("SELECT o.product.name AS group,"
			+ " SUM(o.quantity) AS count,"
			+ " SUM(o.quantity * o.unitPrice * (1 - o.discount)) AS sum,"
			+ " MIN(o.unitPrice) AS min, "
			+ " MAX(o.unitPrice) AS max, "
			+ " AVG(o.unitPrice) AS avg"
			+ " FROM OrderDetail o "
			+ " GROUP BY o.product.name")
	List<Report> getRevenueByProduct();

	@Query("SELECT o.product.category.name AS group,"
			+ " SUM(o.quantity) AS count,"
			+ " SUM(o.quantity * o.unitPrice * (1 - o.discount)) AS sum,"
			+ " MIN(o.unitPrice) AS min, "
			+ " MAX(o.unitPrice) AS max, "
			+ " AVG(o.unitPrice) AS avg"
			+ " FROM OrderDetail o "
			+ " GROUP BY o.product.category.name")
	List<Report> getRevenueByCategory();

	@Query("SELECT o.order.account.username AS group,"
			+ " SUM(o.quantity) AS count,"
			+ " SUM(o.quantity * o.unitPrice * (1 - o.discount)) AS sum,"
			+ " MIN(o.unitPrice) AS min, "
			+ " MAX(o.unitPrice) AS max, "
			+ " AVG(o.unitPrice) AS avg"
			+ " FROM OrderDetail o "
			+ " GROUP BY o.order.account.username"
			+ " ORDER BY SUM(o.quantity * o.unitPrice * (1 - o.discount)) DESC")
	List<Report> getRevenueByCustomer();

	@Query("SELECT YEAR(o.order.orderDate) AS group,"
			+ " SUM(o.quantity) AS count,"
			+ " SUM(o.quantity * o.unitPrice * (1 - o.discount)) AS sum,"
			+ " MIN(o.unitPrice) AS min, "
			+ " MAX(o.unitPrice) AS max, "
			+ " AVG(o.unitPrice) AS avg"
			+ " FROM OrderDetail o "
			+ " GROUP BY YEAR(o.order.orderDate)"
			+ " ORDER BY YEAR(o.order.orderDate)")
	List<Report> getRevenueByYear();

	@Query("SELECT CEILING(MONTH(o.order.orderDate)/3.0) AS group,"
			+ " SUM(o.quantity) AS count,"
			+ " SUM(o.quantity * o.unitPrice * (1 - o.discount)) AS sum,"
			+ " MIN(o.unitPrice) AS min, "
			+ " MAX(o.unitPrice) AS max, "
			+ " AVG(o.unitPrice) AS avg"
			+ " FROM OrderDetail o "
			+ " GROUP BY CEILING(MONTH(o.order.orderDate)/3.0)"
			+ " ORDER BY CEILING(MONTH(o.order.orderDate)/3.0)")
	List<Report> getRevenueByQuarter();

	@Query("SELECT MONTH(o.order.orderDate) AS group,"
			+ " SUM(o.quantity) AS count,"
			+ " SUM(o.quantity * o.unitPrice * (1 - o.discount)) AS sum,"
			+ " MIN(o.unitPrice) AS min, "
			+ " MAX(o.unitPrice) AS max, "
			+ " AVG(o.unitPrice) AS avg"
			+ " FROM OrderDetail o "
			+ " GROUP BY MONTH(o.order.orderDate)"
			+ " ORDER BY MONTH(o.order.orderDate)")
	List<Report> getRevenueByMonth();
}
