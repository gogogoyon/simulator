package com.samlic.emulator.dao;

import java.util.List;

import com.samlic.emulator.entity.InterfaceCase;

public interface InterfaceCaseDao {
	void add(InterfaceCase data);
	void delete(int id);
	void update(InterfaceCase data);
	InterfaceCase query(int id);
	List<InterfaceCase> queryAll();
}
