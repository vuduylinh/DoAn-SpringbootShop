package estore.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estore.repository.Role;
import estore.repository.RoleDAO;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleDAO dao;

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}
}
