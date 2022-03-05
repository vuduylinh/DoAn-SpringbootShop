package estore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import estore.repository.Order;
import estore.repository.Status;
import estore.repository.service.OrderService;
import estore.repository.service.StatusService;
import estore.service.session.SessionService;

@Controller
public class OrderAController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/admin/order/paginate/{pageNumber}")
	public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		model.addAttribute("edit", false);
		return "forward:/admin/order/index";
	}
	
	@RequestMapping("/admin/order/status/{statusId}")
	public String status(@PathVariable("statusId") Integer statusId) {
		sessionService.set("statusId", statusId);
		return "forward:/admin/order/paginate/0";
	}
	
	@RequestMapping("/admin/order/reset")
	public String reset(Model model) {
		model.addAttribute("edit", true);
		return "forward:/admin/order/index";
	}
	
	@RequestMapping("/admin/order/index")
	public String index(Model model) {
		model.addAttribute("item", new Order());
		
		return this.forward(model);
	}
	
	@RequestMapping("/admin/order/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		Order item = orderService.getById(id);
		model.addAttribute("item", item);
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/order/create")
	public String create(Model model, @ModelAttribute("item") Order item) {
		orderService.create(item);
		model.addAttribute("message", "Create successfully!");
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/order/update")
	public String update(Model model, @ModelAttribute("item") Order item) {
		orderService.update(item);
		model.addAttribute("message", "Update successfully!");
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/order/delete/{id}")
	public String delete(Model model, @PathVariable("id") Long id) {
		orderService.deleteById(id);
		model.addAttribute("message", "Delete successfully!");
		
		model.addAttribute("item", new Order());
		model.addAttribute("edit", false);
		return this.forward(model);
	}
	
	String forward(Model model) {
		Integer statusId = sessionService.get("statusId", 0);
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageable = PageRequest.of(pageNumber, 8, Direction.DESC, "orderDate");
		Page<Order> page = orderService.findPageByStatusId(statusId, pageable);
		model.addAttribute("page", page);
		return "admin/order/index";
	}
	
	@ModelAttribute("statuses")
	public List<Status> getStatusList(){
		return statusService.findAll();
	}
}