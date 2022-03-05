package estore.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import estore.repository.Category;
import estore.repository.service.CategoryService;

@Controller
public class CategoryAController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/admin/category/reset")
	public String reset() {
		return "forward:/admin/category/index";
	}
	
	@RequestMapping("/admin/category/index")
	public String index(Model model) {
		model.addAttribute("item", new Category());
		
		return this.forward(model);
	}
	
	@RequestMapping("/admin/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category item = categoryService.getById(id);
		model.addAttribute("item", item);
		
		return this.forward(model);
	}
	
	@RequestMapping("/admin/category/create")
	public String create(Model model, @ModelAttribute("item") Category item) {
		categoryService.create(item);
		model.addAttribute("message", "Create successfully!");
		
		return this.forward(model);
	}
	
	@RequestMapping("/admin/category/update")
	public String update(Model model, @ModelAttribute("item") Category item) {
		categoryService.create(item);
		model.addAttribute("message", "Update successfully!");
		
		return this.forward(model);
	}
	
	@RequestMapping("/admin/category/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		categoryService.deleteById(id);
		model.addAttribute("message", "Delete successfully!");
		
		model.addAttribute("item", new Category());
		return this.forward(model);
	}
	
	String forward(Model model) {
		List<Category> items = categoryService.findAll();
		model.addAttribute("items", items);
		return "admin/category/index";
	}
}