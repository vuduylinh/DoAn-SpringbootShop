package estore.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import estore.repository.Category;
import estore.repository.service.CategoryService;
//dùng để cung cấp dữ liệu ra list-group Danh mục
// chạy sau khi đến controller
// 1 interceptor gồm 3 sự kiện postHandle ,preHandle, afterCompletion
@Service
public class GlobalInterceptor implements HandlerInterceptor{ 
			@Autowired
			CategoryService categoryService;
			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				List<Category> list = categoryService.findAll();
				request.setAttribute("categories", list);
			}
}
