package estore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import estore.interceptor.GlobalInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
		@Autowired
		GlobalInterceptor globalInterceptor;
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(globalInterceptor).addPathPatterns("/**") // được kích hoạt tất cả
			.excludePathPatterns("/admin/**"); // khi vào admin thì ko chạy 
			
		}
}
