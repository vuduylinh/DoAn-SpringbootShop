package estore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import estore.repository.Product;
import estore.repository.Share;
import estore.repository.service.ProductService;
//@RequestParam annotation được sử dụng để trích xuất dữ liệu từ query parameters
//form parameters, và kể cả các tập tin từ request.
import estore.repository.service.ShareService;

@Controller
public class ProductController {
		@Autowired
		ProductService productService; 
		
		@RequestMapping("/product/category/{id}")
		public String category(Model model,@PathVariable("id") Integer id) {
			Page<Product> page = productService.findByCategoryId(id, Pageable.unpaged());//tìm các sản phẩm theo loại
			model.addAttribute("list", page.getContent());
			return "product/list";
		}
		
		@RequestMapping("/product/search") // tìm kiến theo sản phẩm theo keywords
		public String search(Model model,@RequestParam("keywords") String keywords) {
			Page<Product> page = productService.findByKeywords(keywords, Pageable.unpaged());
			model.addAttribute("list", page.getContent());
			return "product/list";
		}
		
		@RequestMapping("/product/discount") // tìm kiến theo sản phẩm khuyến mại
		public String discount(Model model) {
			Page<Product> page = productService.findByDiscount(Pageable.unpaged());
			model.addAttribute("list", page.getContent());
			return "product/list";
		}
		
		@RequestMapping("/product/latest") // tìm kiến theo sản phẩm mới nhất
		public String latest(Model model) {
			Pageable pageable = PageRequest.of(0, 10);
			Page<Product> page = productService.findByLatest(pageable);
			model.addAttribute("list", page.getContent());
			return "product/list";
		}
		
		
		
		@RequestMapping("/product/favorite") // tìm kiến theo sản phẩm yêu thích
		public String favorite(Model model) {
			Pageable pageable = PageRequest.of(0, 10);
			Page<Product> page = productService.findByFavorite(pageable);
			model.addAttribute("list", page.getContent());
			return "product/list";
		}
		
		@RequestMapping("/product/special") // tìm kiến theo sản phẩm đặc biệt
		public String special(Model model) {
			Pageable pageable = PageRequest.of(0, 10);
			Page<Product> page = productService.findBySpecial(pageable);
			model.addAttribute("list", page.getContent());
			return "product/list";
		}
		
		@RequestMapping("/product/share") // tìm kiến theo sản phẩm đc chia sẽ
		public String share(Model model) {
			Pageable pageable = PageRequest.of(0, 10);
			Page<Product> page = productService.findByShare(pageable);
			model.addAttribute("list", page.getContent());
			return "product/list";
		}
		
		@RequestMapping("/product/detail/{id}") // details product
		public String detail(Model model, @PathVariable("id") Integer id) {
			Product product = productService.getById(id);
			model.addAttribute("item", product);
			return "product/detail";
		}
		
		@ResponseBody
		@RequestMapping("/product/like/{id}") // like
		public Integer like(Model model, @PathVariable("id") Integer id) {
			Product product = productService.getById(id);
			product.setLikeCount(product.getLikeCount()+ 1);
			productService.update(product);
			model.addAttribute("item", product);
			return product.getLikeCount();
		}
		@Autowired
		ShareService shareService;
		
		@ResponseBody
		@RequestMapping("/product/share_send") // like
		public void share(Model model,@RequestBody Share share) {
			shareService.create(share);
		}
		
		
}
