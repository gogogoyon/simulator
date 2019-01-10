package com.samlic.emulator.core;

import java.util.List;

import com.samlic.emulator.entity.InterfaceCase;

public interface InterfaceCaseService {
	void addInterfaceCase(InterfaceCase data);
	void deleteInterfaceCase(InterfaceCase data);
	void updateInterfaceCase(InterfaceCase data);
	List<InterfaceCase> queryAll();
}
