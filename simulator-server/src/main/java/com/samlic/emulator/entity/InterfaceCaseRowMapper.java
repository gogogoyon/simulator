package com.samlic.emulator.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class InterfaceCaseRowMapper implements RowMapper<InterfaceCase> {

	@Override
	public InterfaceCase mapRow(ResultSet rs, int rowNum) throws SQLException {
		InterfaceCase interfaceCase = new InterfaceCase();
		
		interfaceCase.setContentType(rs.getString("CONTENT_TYPE"));
		interfaceCase.setCreateTime(rs.getDate("CREATE_TIME"));
		interfaceCase.setId(rs.getInt("ID"));
		interfaceCase.setMatchRule(rs.getString("MATCH_RULE"));
		interfaceCase.setName(rs.getString("NAME"));
		interfaceCase.setResponse(rs.getString("RESPONSE"));
		interfaceCase.setStatus(rs.getInt("STATUS"));
		interfaceCase.setUpdateTime(rs.getDate("UPDATE_TIME"));
		interfaceCase.setUrl(rs.getString("URL"));
		interfaceCase.setGrouping(rs.getString("GROUPING"));
		return interfaceCase;
	}

}
