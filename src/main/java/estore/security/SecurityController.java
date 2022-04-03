package estore.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/security/login/form")
	public String loginForm() {
		return "/security/login";
	}
	
	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "forward:/home/index";
	}
	
	@RequestMapping("/security/login/failure")
	public String loginFailure(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "forward:/security/login/form";
	}
	
	@RequestMapping("/security/logout/success")
	public String logoutSuccess(Model model) {
		model.addAttribute("message", "Đã đăng xuất khỏi hệ thống!");
		return "forward:/security/login/form";
	}
	
	@RequestMapping("/security/access/denied")
	public String accessDenied(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy xuất chức năng này!");
		return "forward:/security/login/form";
	}
}
