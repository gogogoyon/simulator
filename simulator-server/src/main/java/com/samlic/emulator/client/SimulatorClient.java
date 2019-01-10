package com.samlic.emulator.client;

import com.samlic.emulator.client.ManagedStage.StageID;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SimulatorClient extends Application {
	
	private ManagedStage makeAboutStage() {
		Label label = new Label("This is a simulator client. \n Samlic inc. All rights reserved.");
		label.setLayoutX(50);
		label.setLayoutY(50);		
		
		AnchorPane pane = new AnchorPane(label);
		
		Scene scene = new Scene(pane, 300, 200);
		
		ManagedStage stage = new ManagedStage(StageID.ABOUT, StageStyle.UTILITY);
		stage.setScene(scene);
		stage.setWidth(scene.getWidth());
		stage.setHeight(scene.getHeight());		
		stage.setTitle("About");	
		stage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				stage.close();				
			}
			
		});
		
		return stage;	
	}
	
	@Override
	public void stop() throws Exception {
		ServerInfoManager.serialize();
    }

	@Override
    public void start(Stage primaryStage) throws Exception {
		StageManager.setPrimaryStage(primaryStage);
		
		ManagedStage aboutStage = makeAboutStage();		
		StageManager.loadStage(aboutStage.getId(), aboutStage);		
		StageManager.loadStage(StageID.ADD_SERVER, "Add Server", "AddServer.fxml");
		
        primaryStage.setScene(new Scene(StageManager.loadFxml("Primary.fxml")));
        primaryStage.setTitle("Simulator Client");       
        primaryStage.show();
    } 

    public static void main(String[] args) {
        launch(args);
    }
}
