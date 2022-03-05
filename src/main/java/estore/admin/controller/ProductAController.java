package estore.admin.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import estore.repository.Category;
import estore.repository.Product;
import estore.repository.service.CategoryService;
import estore.repository.service.ProductService;
import estore.service.session.SessionService;
import estore.service.upload.UploadService;

@Controller
public class ProductAController {
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	SessionService sessionService;
	
	@RequestMapping("/admin/product/paginate/{pageNumber}")
	public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		model.addAttribute("edit", false);
		return "forward:/admin/product/index";
	}
	
	@RequestMapping("/admin/product/category/{cid}")
	public String category(Model model, @PathVariable("cid") Integer cid) {
		sessionService.set("cid", cid);
		return "forward:/admin/product/paginate/0";
	}
	
	@RequestMapping("/admin/product/reset")
	public String reset(Model model) {
		model.addAttribute("edit", true);
		return "forward:/admin/product/index";
	}
	
	@RequestMapping("/admin/product/index")
	public String index(Model model) {
		model.addAttribute("item", new Product());
		return this.forward(model);
	}
	
	@RequestMapping("/admin/product/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Product item = productService.getById(id);
		model.addAttribute("item", item);
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/product/create")
	public String create(Model model, 
			@RequestPart("image_file") MultipartFile imageFile,
			@ModelAttribute("item") Product item) {
		if(!imageFile.isEmpty()) {
			File image = uploadService.save(imageFile, "/images/items/");
			item.setImage(image.getName());
		}
		productService.create(item);
		model.addAttribute("message", "Create successfully!");
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/product/update")
	public String update(Model model, 
			@RequestPart("image_file") MultipartFile imageFile,
			@ModelAttribute("item") Product item) {
		if(!imageFile.isEmpty()) {
			File image = uploadService.save(imageFile, "/images/items/");
			item.setImage(image.getName());
		}
		productService.update(item);
		model.addAttribute("message", "Update successfully!");
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/product/delete/{id}")
	public String delete(Model model, @PathVariable("id") Integer id) {
		productService.deleteById(id);
		model.addAttribute("message", "Delete successfully!");
		
		model.addAttribute("item", new Product());
		model.addAttribute("edit", false);
		return this.forward(model);
	}
	
	String forward(Model model) {
		Integer cid = sessionService.get("cid", 1000);
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageable = PageRequest.of(pageNumber, 5);
		Page<Product> page = productService.findByCategoryId(cid, pageable);
		model.addAttribute("page", page);
		return "admin/product/index";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryService.findAll();
	}
}