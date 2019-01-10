package com.samlic.emulator.client.data;

import java.util.List;

public interface InterfaceCaseService {
	void addInterfaceCase(InterfaceCase data);
	void deleteInterfaceCase(InterfaceCase data);
	void updateInterfaceCase(InterfaceCase data);
	List<InterfaceCase> queryAll();
}
