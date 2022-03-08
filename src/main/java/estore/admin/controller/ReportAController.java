	package estore.admin.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import estore.repository.Report;
import estore.repository.service.ReportService;

@CrossOrigin("*")
@Controller
public class ReportAController {
	@Autowired
	ReportService reportService;
	
	@RequestMapping("/admin/report/inventory")
	public String inventory() {
		return "admin/report/inventory";
	}
	
	@RequestMapping("/admin/report/revenue")
	public String revenue() {
		return "admin/report/revenue";
	}
	
	@ResponseBody
	@RequestMapping({"/api/product/like", "/api/product/like/{size}"})
	public List<Report> getTopLikes(@PathVariable("size") Optional<Integer> sizeOpts) {
		int size = sizeOpts.orElse(5); // truy xuất ra mặt hàng có lươt like nhiều nhất theo top 5
		return reportService.getTopLikes(size);
	}
	
	@ResponseBody
	@RequestMapping({"/api/product/share", "/api/product/share/{size}"})
	public List<Report> getTopShares(@PathVariable("size") Optional<Integer> sizeOpts) {
		int size = sizeOpts.orElse(5);
		return reportService.getTopShares(size);
	}
	
	@ResponseBody
	@RequestMapping("/api/inventory")
	public List<Report> getInventory() {
		return reportService.getInventoryByCategory();
	}
	
	@ResponseBody
	@RequestMapping("/api/revenue/product")
	public List<Report> getRevenueByProduct() {
		return reportService.getRevenueByProduct();
	}
	
	@ResponseBody
	@RequestMapping("/api/revenue/category")
	public List<Report> getRevenueByCategory() {
		return reportService.getRevenueByCategory();
	}
	
	@ResponseBody
	@RequestMapping("/api/revenue/customer")
	public List<Report> getRevenueByCustomer() {
		return reportService.getRevenueByCustomer();
	}
	
	@ResponseBody
	@RequestMapping("/api/revenue/year")
	public List<Report> getRevenueByYear() {
		return reportService.getRevenueByYear();
	}
	
	@ResponseBody
	@RequestMapping("/api/revenue/quarter")
	public List<Report> getRevenueByQuarter() {
		return reportService.getRevenueByQuarter();
	}
	
	@ResponseBody
	@RequestMapping("/api/revenue/month")
	public List<Report> getRevenueByMonth() {
		return reportService.getRevenueByMonth();
	}
}