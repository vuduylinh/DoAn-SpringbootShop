package estore.service.session;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService{
	@Autowired
	HttpSession session;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String name, T defval) {
		T value = (T) session.getAttribute(name);
		return value != null ? value : defval;
	}

	@Override
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}

	@Override
	public void remove(String name) {
		session.removeAttribute(name);
	}
}
