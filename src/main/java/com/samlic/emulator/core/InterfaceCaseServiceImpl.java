package com.samlic.emulator.core;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samlic.emulator.dao.InterfaceCaseDao;
import com.samlic.emulator.entity.InterfaceCase;

@Service
public class InterfaceCaseServiceImpl implements InterfaceCaseService {	
	
	@Autowired
	private InterfaceCaseDao caseDao;
	
	@PostConstruct
	public void init() {		
		List<InterfaceCase> dataList = caseDao.queryAll();
		InterfaceCaseManager.init(dataList);
	}
	
	@Transactional
	public void addInterfaceCase(InterfaceCase data) {
		caseDao.add(data);
		InterfaceCaseManager.add(data);
	}
	
	@Transactional
	public void deleteInterfaceCase(InterfaceCase data) {
		caseDao.delete(data.getId());
		InterfaceCaseManager.delete(data);
	}
	
	@Transactional
	public void updateInterfaceCase(InterfaceCase data) {
		caseDao.update(data);
		InterfaceCaseManager.update(data);
	}
	
	public List<InterfaceCase> queryAll() {
		return InterfaceCaseManager.queryAll();
	}	
}
