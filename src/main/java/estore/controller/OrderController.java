package estore.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import estore.repository.Account;
import estore.repository.Order;
import estore.repository.Product;
import estore.repository.Status;
import estore.repository.service.OrderService;
import estore.repository.service.ProductService;
import estore.security.UserDetailsImpl;
import estore.service.cart.CartService;
import estore.service.mail.MailerService;

@Controller
public class OrderController {
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;

	@Autowired
	MailerService mailerService;
	
	// thanh toan hang mua
	@RequestMapping("/order/checkout")
	public String checkout(Model model, Authentication auth) {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
		
		Order order = new Order();
		order.setAccount(account);
		order.setAddress(account.getAddress());
		order.setMobile(account.getMobile());
		order.setOrderDate(new Date());
		order.setAmount(cartService.getAmount());
		order.setRecipient(account.getFullname());
		
		model.addAttribute("item", order);
		model.addAttribute("cart", cartService);
		return "order/checkout";
	}
	
	@RequestMapping("/order/purchase")
	public String purchase(Model model,Authentication auth, @ModelAttribute("item") Order order) {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
		try {
			order.setAccount(account);
			orderService.create(order, cartService);
			mailerService.sendOrder(order);
			return "redirect:/order/detail/" + order.getId();
		} catch (Exception e) {
			model.addAttribute("message", "Đặt hàng lỗi");
			model.addAttribute("cart", cartService);
			return "order/checkout";
		}
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
			Order order=orderService.getById(id);
			model.addAttribute("item",order);
			return "order/detail";
	}
	// huy don hàng
	@RequestMapping("/order/cancel/{id}")
	public String cancel(Model model, @PathVariable("id") Long id) {
			Order order=orderService.getById(id);
			order.setStatus(new Status(-1,null,null,null,null));
			orderService.update(order);
			
			return "redirect:/order/detail/" + id;
	}
	// controller danh sach orders
	@RequestMapping("/order/list")
	public String list(Model model, Authentication auth) {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
			List<Order> list = orderService.findByUsername(account.getUsername());
			model.addAttribute("list", list);
			return "order/list";
	}
	
	@Autowired
	ProductService productService;
	// cac mat hang da mua
	@RequestMapping("/order/items-purchased")
	public String ItemsPurchase(Model model, Authentication auth) {
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		Account account = userDetails.getAccount();
		
			List<Product> list = productService.findByUsername(account.getUsername());
			model.addAttribute("list", list);
			return "product/list";
	}
}
