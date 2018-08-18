package com.samlic.emulator.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.samlic.emulator.dao.InterfaceCaseDao;
import com.samlic.emulator.entity.InterfaceCase;
import com.samlic.emulator.entity.InterfaceCaseRowMapper;

@Repository
@Transactional
public class InterfaceCaseDaoImpl implements InterfaceCaseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(InterfaceCase data) {
		Integer id = jdbcTemplate.queryForObject("values next value for interface_case_id", Integer.class);
		jdbcTemplate.update("insert into t_interface_case values(?, ?, ?, ?, ?, ?, ?, ?, ?)", 
				id, data.getName(), data.getUrl(), data.getMatchRule(), data.getResponse(), data.getContentType(), data.getStatus(), data.getCreateTime(), data.getUpdateTIme());
		data.setId(id);
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update("delete from t_interface_case where id = ?", id);
	}

	@Override
	public void update(InterfaceCase data) {
		jdbcTemplate.update("update t_interface_case set name=?, url=?, match_rule=?, output=?, content_type=?, create_time=?, update_time=?  where id = ?",
				data.getName(), data.getUrl(), data.getMatchRule(), data.getResponse(), data.getContentType(), data.getStatus(), data.getCreateTime(), data.getUpdateTIme(), data.getId());		
	}

	@Override
	public InterfaceCase query(int id) {
		
		return jdbcTemplate.queryForObject("select * from t_interface_case where id = " + id, new InterfaceCaseRowMapper());
	}

	@Override
	public List<InterfaceCase> queryAll() {
		return jdbcTemplate.query("select * from t_interface_case", new InterfaceCaseRowMapper());
	}

}
