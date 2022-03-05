package estore.repository.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estore.repository.ShareDAO;

@Service
public class ShareServiceImpl implements ShareService {
	@Autowired
	ShareDAO dao;
}
