package estore.service.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	@Override
	public Cookie get(String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equalsIgnoreCase(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	@Override
	public String getValue(String name, String defval) {
		Cookie cookie = this.get(name);
		return cookie == null ? defval : cookie.getValue();
	}

	@Override
	public Cookie create(String name, String value, int days) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(days * 24 * 60 * 60);
		response.addCookie(cookie);
		return cookie;
	}

	@Override
	public void delete(String name) {
		this.create(name, "", 0);
	}
}
