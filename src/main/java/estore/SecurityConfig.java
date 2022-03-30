package estore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		@Autowired
		UserDetailsService userDetailsService; // interface do spring cung cấp
	
		// quản lý user
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
		}
		
		
		// phân quyền
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.cors().disable().csrf().disable();
			http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN","SUPER","STAFF")
			.antMatchers("admin/home/index").permitAll()
			.antMatchers("/order/**","/account/edit-profile",
					"/account/change-password"
					,"/spring/logout").authenticated()
			.anyRequest().permitAll(); // bắt buộc đăng nhập

			// => từ chối nếu truy cập với vai trò không hợp lệ
			http.exceptionHandling()
				.accessDeniedPage("/security/access/denied");
			
			// => cấu hình form đăng nhập và đăng xuất
			http.formLogin()
				.loginProcessingUrl("/spring/login")
				.loginPage("/security/login/form") // yêu cầu đăng nhập
				.defaultSuccessUrl("/security/login/success") // đăng nhập thành công
				.failureUrl("/security/login/failure");	// ĐN sai user pwd
			http.rememberMe()
				.tokenValiditySeconds(5 * 24 * 60 * 60);
			
			http.logout()
				.logoutUrl("/spring/logout")
				.logoutSuccessUrl("/security/logout/success") // đăng xuất thành công
				.addLogoutHandler(new SecurityContextLogoutHandler());
		}
		
		// loại bỏ tài nguyên tĩnh
		@Override
		public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
			web.ignoring().antMatchers("/js**","/css/**","/images/**");
		}
		
		// bean cơ chế mã hóa password
		@Bean
		public BCryptPasswordEncoder getBCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
}
