package estore.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import estore.repository.Report;
import estore.repository.ReportDAO;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	ReportDAO dao;
	
	@Override
	public List<Report> getInventoryByCategory() {
		return dao.getInventoryByCategory();
	}
	
	@Override
	public List<Report> getTopLikes(int size) {
		Pageable pageable = PageRequest.of(0, size);
		return dao.getTopLikes(pageable);
	}

	@Override
	public List<Report> getTopShares(int size) {
		Pageable pageable = PageRequest.of(0, size);
		return dao.getTopShares(pageable);
	}

	@Override
	public List<Report> getRevenueByProduct() {
		return dao.getRevenueByProduct();
	}

	@Override
	public List<Report> getRevenueByCategory() {
		return dao.getRevenueByCategory();
	}

	@Override
	public List<Report> getRevenueByCustomer() {
		return dao.getRevenueByCustomer();
	}

	@Override
	public List<Report> getRevenueByYear() {
		return dao.getRevenueByYear();
	}

	@Override
	public List<Report> getRevenueByQuarter() {
		return dao.getRevenueByQuarter();
	}

	@Override
	public List<Report> getRevenueByMonth() {
		return dao.getRevenueByMonth();
	}
}
