package estore.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estore.repository.AuthorityDAO;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	AuthorityDAO dao;
}
