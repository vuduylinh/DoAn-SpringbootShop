package estore.repository.service;

import java.util.List;

import estore.repository.Report;

public interface ReportService {
	List<Report> getInventoryByCategory();
	List<Report> getTopLikes(int size);
	List<Report> getTopShares(int size);
	List<Report> getRevenueByProduct();
	List<Report> getRevenueByCategory();
	List<Report> getRevenueByCustomer();
	List<Report> getRevenueByYear();
	List<Report> getRevenueByQuarter();
	List<Report> getRevenueByMonth();
}
