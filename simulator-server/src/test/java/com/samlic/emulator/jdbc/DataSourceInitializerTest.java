package com.samlic.emulator.jdbc;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.samlic.emulator.dao.InterfaceCaseDao;
import com.samlic.emulator.entity.InterfaceCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceInitializerTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private InterfaceCaseDao caseDao;
	
	@Test @Transactional
	public void test() {
		InterfaceCase interfaceCase = new InterfaceCase();
		interfaceCase.setContentType("text/plain");
		caseDao.add(interfaceCase);
		
		InterfaceCase other = caseDao.query(interfaceCase.getId());
		Assert.assertTrue(interfaceCase.equals(other));
		
		int count = jdbcTemplate.queryForObject("select count(1) from T_INTERFACE_CASE", Integer.class);
		
		List<InterfaceCase> caseList = caseDao.queryAll();
		Assert.assertTrue(caseList.size() == count);
	}
	
	
	public void insert() {
		InterfaceCase data = new InterfaceCase();
		data.setContentType("text/plain");
		data.setCreateTime(new Date());
		data.setMatchRule("method=='GET'");
		data.setName("getTest");
		data.setResponse("<h>Get method test successfully.</h>");
		data.setStatus(0);
		data.setUpdateTime(new Date());
		data.setUrl("/test/getTest");
		data.setGrouping("Grouping");
		
		caseDao.add(data);
	}
}
