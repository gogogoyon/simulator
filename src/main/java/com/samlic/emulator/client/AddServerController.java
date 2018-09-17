package com.samlic.emulator.client;

import com.samlic.emulator.client.ManagedStage.StageID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddServerController {
	@FXML
	private TextField txtHost;	
	@FXML
	private TextField txtPort;	
	
	@FXML
	public void onConfirm(ActionEvent event) {
		String host = txtHost.getText();
		String port = txtPort.getText();
		String server = host + ":" + port;
		ServerInfoManager.addServer(server);
		
		StageManager.getStage(StageID.ADD_SERVER).close();
	}
	
	@FXML
	public void onCancel(ActionEvent event) {
		StageManager.getStage(StageID.ADD_SERVER).close();
	}
}
