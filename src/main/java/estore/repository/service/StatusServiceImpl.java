package estore.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estore.repository.Status;
import estore.repository.StatusDAO;

@Service
public class StatusServiceImpl implements StatusService {
	@Autowired
	StatusDAO dao;

	@Override
	public List<Status> findAll() {
		return dao.findAll();
	}
}
