package estore.controller;



import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import estore.repository.Account;
import estore.repository.service.AccountService;
import estore.security.UserDetailsImpl;
import estore.service.mail.MailerService;
import estore.service.upload.UploadService;

@Controller
public class AccountController {
		@Autowired
		AccountService accountService;
		@Autowired
		UploadService uploadService; 
		
		@Autowired
		MailerService mailerService;
		
		@Autowired
		BCryptPasswordEncoder passwordEncoder;
		
		/**
		 * đăng ký vào kích hoạt tài khoản người dùng
		 */
	@GetMapping("/account/sign-up") // hiện thì form
	public String SignUp(Model model) {
		Account account = new Account();
		model.addAttribute("form", account);
		return "account/sign-up";
	}
	@PostMapping("/account/sign-up") //xử lý action đăng ký
	public String SignUp(Model model,
			@ModelAttribute("form") Account account,
			@RequestParam("confirm") String confirm,
			@RequestPart("photo_file") MultipartFile photo) {
		if(!confirm.equals(account.getPassword())) {
			model.addAttribute("message", "Xác nhận mật khẩu không đúng!");
		}else if(accountService.existByUsername(account.getUsername())){
			model.addAttribute("message", account.getUsername() + ", đã được sử dụng!");
		}else {
			if(!photo.isEmpty()) {
				File file =uploadService.save(photo, "/images/photos/");
				account.setPhoto(file.getName());
			}
			String pwd = passwordEncoder.encode(account.getPassword()); // lấy pwd thô từ db để mã hóa
			account.setPassword(pwd);
			accountService.create(account, List.of());// tạo ra tài khoản ko có vai trò nào
			mailerService.sendWelcome(account);// send mail dể kích hoạt
			model.addAttribute("message","Đăng ký thành công. Kiểm tra email để kích hoạt");
			return "forward:/security/login/form";
		}
		return "account/sign-up";
	}
	
	/**
	 * @param activate kích hoạt tài khoản
	 * @param model
	 * @return
	 */
	@RequestMapping("/account/activate/{username}")
	public String activate(Model model ,@PathVariable("username") String username) {
		Account account = accountService.getByUsername(username);
		account.setActivated(true);
		accountService.update(account, List.of());
		model.addAttribute("message","Tài khoản đã được kích hoạt!");
		return "forward:/security/login/form";
	}

	
	
	// cập nhật tài khoản
	
	
	@GetMapping("/account/edit-profile")
	public String editProfile(Model model, Authentication auth) { 
		UserDetailsImpl user= (UserDetailsImpl) auth.getPrincipal(); //estore.security.UserDetailsImpl 
		model.addAttribute("form", user.getAccount()); // lấy thông tin tk ghể load lên form
			return "account/edit-profile";// gọi đến gia diện
	}
	
	// xử lý nghiệm vụ
	@PostMapping("/account/edit-profile")
	public String editProfile(Model model, Authentication auth,
			@ModelAttribute("form") Account account,
			@RequestPart("photo_file") MultipartFile photo) {
		if(!accountService.existByUsername(account.getUsername())){
			model.addAttribute("message", account.getUsername() + ", Không tồn tại!");
		}else {
			if(!photo.isEmpty()) {
				File file =uploadService.save(photo, "/images/photos/");
				account.setPhoto(file.getName());
			}
			accountService.update(account, List.of());// tạo ra tài khoản ko có vai trò nào
			model.addAttribute("message","Cập nhật thành công!");
			
			// cập nhật lại thông tin của tai khoan đã đăng nhập
			UserDetailsImpl user= (UserDetailsImpl) auth.getPrincipal();// lấy user từ bộ nhớ ra									 //estore.security.UserDetailsImpl 
			user.setAccount(account);// và đổi lại thành account
		}
			
			return "account/edit-profile";
	}
	
	// đổi mật khẩu
	
	@GetMapping("/account/change-password")
	public String changePassword(Model model) {
			return "account/change-password";
	}
	
	@PostMapping("/account/change-password")
	public String changePassword(Model model, Authentication auth,
			@RequestParam("password") String password,
			@RequestParam("newpass") String newpass,
			@RequestParam("confirm") String confirm) {
			

		if(!newpass.equals(confirm)) {
			model.addAttribute("message", "Xác nhận mật khẩu mới không đúng!");
		}else{
			UserDetailsImpl user= (UserDetailsImpl) auth.getPrincipal();// lấy user từ bộ nhớ ra									 //estore.security.UserDetailsImpl 
			Account account = user.getAccount();
			if(!passwordEncoder.matches(password, account.getPassword())) { // matches có chức năng so sánh mật khẩu thô vs mật khẩu đã mã hóa
				model.addAttribute("message","Sai mật khẩu!");
			}else {
				account.setPassword(passwordEncoder.encode(newpass)); // mã hóa new pwd encode()
				accountService.update(account, List.of());
				model.addAttribute("message","Đổi mật khẩu thành công!");
			}
		}
		
		return "account/change-password";
	}
	
	// quên mật khẩu
	@GetMapping("/account/forgot-password")
	public String forgotPassword(Model model) {
			return "account/forgot-password";
	}
	
	@PostMapping("/account/forgot-password")
	public String forgotPassword(Model model, 
			@RequestParam("username") String username,
			@RequestParam("email") String email) {
			
			if(!accountService.existByUsername(username)) {
				model.addAttribute("message" + username + "Tên người dùng không tồn tại!");
			}else {
				Account account = accountService.getByUsername(username);
				if(!account.getEmail().equalsIgnoreCase(email)) {   //!email.equals(account.getEmail())
					model.addAttribute("message","Sai địa chỉ email!");
				}else {
					// chuyển sang token | chuỗi dạng 32bit
					String token = Integer.toHexString(account.getPassword().hashCode()); 
					// gửi token code tới email người dùng khi reset nha team.
					Long expiry	=System.currentTimeMillis()  + 5 * 24 * 60 * 60 * 1000;
						mailerService.sendToken(token + ":" + expiry, email);
						model.addAttribute("message","Token code đã được gửi qua email vui lòng kiểm tra!");
						return "account/reset-password";
				}
			}
		return "account/forgot-password";
		}
	
	
	@PostMapping("/account/reset-password")
	public String resetPassword(Model model,
			@RequestParam("username") String username,
			@RequestParam("token") String token,
			@RequestParam("newpass") String newpass,
			@RequestParam("confirm") String confirm) {

		if(!newpass.equals(confirm)) {
			model.addAttribute("message", "Xác nhận mật khẩu mới không đúng!");
		}else if(!accountService.existByUsername(username)){
			model.addAttribute("message", username + " Không tồn tại!");
			
		}else {
			String[] parts= token.split(":");
			Account account = accountService.getByUsername(username);
			String currentToken = Integer.toHexString(account.getPassword().hashCode());
			
			if(!parts[0].equals(currentToken)) {
				model.addAttribute("message", "Sai Token code");
			}
			//!token.equals(currentToken) // matches có chức năng so sánh mật khẩu thô vs mật khẩu đã mã hóa
			else if(System.currentTimeMillis() > Long.parseLong(parts[1])) { // TG hiện tại > TG gửi đi
				model.addAttribute("message","Token đã hết hạn!");
			}else {
				account.setPassword(passwordEncoder.encode(newpass)); // mã hóa new pwd encode()
				accountService.update(account, List.of());
				model.addAttribute("message","Đổi mật khẩu thành công!");
				return "forward:/security/login/form";
			}
		}
			return "account/reset-password";
	}
	
	
}
