package estore.admin.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import estore.admin.bean.AccountFilter;
import estore.repository.Account;
import estore.repository.Role;
import estore.repository.service.AccountService;
import estore.repository.service.RoleService;
import estore.service.session.SessionService;
import estore.service.upload.UploadService;

@Controller
public class AccountAController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	UploadService uploadService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/admin/account/paginate/{pageNumber}")
	public String paginate(Model model, @PathVariable("pageNumber") Integer pageNumber) {
		sessionService.set("pageNumber", pageNumber);
		model.addAttribute("edit", false);
		return "forward:/admin/account/index";
	}
	
	@RequestMapping("/admin/account/filter")
	public String filter(AccountFilter filter) {
		sessionService.set("filter", filter);
		return "forward:/admin/account/paginate/0";
	}
	
	@RequestMapping("/admin/account/reset")
	public String reset(Model model) {
		model.addAttribute("edit", true);
		return "forward:/admin/account/index";
	}
	
	@RequestMapping("/admin/account/index")
	public String index(Model model) {
		model.addAttribute("item", new Account());
		
		return this.forward(model);
	}
	
	@RequestMapping("/admin/account/edit/{id}")
	public String edit(Model model, @PathVariable("id") String username) {
		Account item = accountService.getByUsername(username);
		model.addAttribute("item", item);
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/account/create")
	public String create(Model model, 
			@RequestPart("photo_file") MultipartFile photoFile,
			@ModelAttribute("item") Account item,
			@RequestParam("roleIds") Optional<List<String>> roleIds) {
		if(!photoFile.isEmpty()) {
			File photo = uploadService.save(photoFile, "/images/photos/");
			item.setPhoto(photo.getName());
		}
		accountService.create(item, roleIds.orElse(List.of()));
		model.addAttribute("message", "Create successfully!");
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/account/update")
	public String update(Model model, 
			@RequestPart("photo_file") MultipartFile photoFile,
			@ModelAttribute("item") Account item,
			@RequestParam("roleIds") Optional<List<String>> roleIds) {
		if(!photoFile.isEmpty()) {
			File photo = uploadService.save(photoFile, "/images/photos/");
			item.setPhoto(photo.getName());
		}
		accountService.update(item, roleIds.orElse(List.of()));
		model.addAttribute("message", "Update successfully!");
		model.addAttribute("edit", true);
		return this.forward(model);
	}
	
	@RequestMapping("/admin/account/delete/{id}")
	public String delete(Model model, @PathVariable("id") String username) {
		accountService.deleteByUsername(username);
		model.addAttribute("message", "Delete successfully!");
		
		model.addAttribute("item", new Account());
		model.addAttribute("edit", false);
		return this.forward(model);
	}
	
	String forward(Model model) {
		AccountFilter filter = sessionService.get("filter", new AccountFilter());
		model.addAttribute("filter", filter);
		
		Integer pageNumber = sessionService.get("pageNumber", 0);
		Pageable pageable = PageRequest.of(pageNumber, 8);
		Page<Account> page = accountService.findPageByFilter(filter, pageable);
		model.addAttribute("page", page);
		
		return "admin/account/index";
	}
	
	@ModelAttribute("roles")
	public List<Role> getRoles(){
		return roleService.findAll();
	}
}