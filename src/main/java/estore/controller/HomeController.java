package estore.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import estore.admin.bean.AccountFilter;
import estore.repository.Account;
import estore.repository.Order;
import estore.repository.Product;
import estore.repository.service.ProductService;
import estore.service.session.SessionService;


@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	SessionService sessionService;
	
	/*
	 * @RequestMapping("/home/index") public String Poster(Model model) { // lấy ra
	 * sp mới nhất Page<Product> latest =
	 * productService.findByLatest(PageRequest.of(0, 4));// phan trang //
	 * List<Product> listnew = productService.findAll();
	 * model.addAttribute("listNew", latest);
	 * 
	 * // lấy ra sp giảm giá Pageable pageable = PageRequest.of(0, 4,
	 * Direction.DESC, "discount"); List<Product> prom =
	 * productService.findByDiscount(pageable).getContent();
	 * model.addAttribute("prom", prom); return "home/index"; }
	 */
	
	
	@RequestMapping("/home/index/paginate/{pageNumber}")
	public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
//		model.addAttribute("?", false);
		return "forward:/home/index";
	}
	
	@RequestMapping("/home/index")
	public String index(Model model) {
		model.addAttribute("item", new Product());
		return this.forward(model);
	}
	private String forward(Model model) {
//		Integer cid = sessionService.get("cid", 1000);
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageable = PageRequest.of(pageNumber, 12);
		Page<Product> page = productService.findByList(pageable);
		model.addAttribute("page", page);
		
		// lấy ra sp mới nhất
		Page<Product> latest = productService.findByLatest(PageRequest.of(0, 4));// phan trang
//		List<Product> listnew = productService.findAll();
		model.addAttribute("listNew", latest);
		
		// lấy ra sp giảm giá
		pageable = PageRequest.of(0, 4, Direction.DESC, "discount");
		List<Product> prom = productService.findByDiscount(pageable).getContent();
		model.addAttribute("prom", prom);
		return "/home/index";
	}
	
	
	@RequestMapping("/home/about")
	public String about() {
		return "home/about";
	}
	// phan trang
		
	
}
