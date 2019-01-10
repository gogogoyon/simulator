package com.samlic.emulator.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.samlic.emulator.client.ManagedStage.StageID;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StageManager {
	private static Map<StageID, ManagedStage> stageMap = new HashMap<StageID, ManagedStage>();
	
	private static Stage primaryStage;
	
	private StageManager() {}
	
	public static void setPrimaryStage(Stage primaryStage) {
		StageManager.primaryStage = primaryStage;
	}
	
	public static void loadStage(StageID id, ManagedStage stage) {
		if(primaryStage == null) {
			throw new IllegalStateException("Primary stage has not been set.");
		}
		
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		stageMap.put(id, stage);
	}
	
	public static Parent loadFxml(String fxml) {
		try {
			Parent parent = FXMLLoader.load(StageManager.class.getResource(fxml));			
			return parent;		
		} catch (IOException e) {
			throw new RuntimeException("Failed to load " + fxml, e);
		}
	}
	
	public static void loadStage(StageID id, String title, String fxml) {		
		Parent parent = loadFxml(fxml);
		if(parent != null) {			
			ManagedStage stage = new ManagedStage(id);
			Scene scene = new Scene(parent);
			stage.setScene(scene);			
			stage.setTitle(title);
			
			loadStage(id, stage);
		}
	}
	
	public static void unloadStage(StageID id) {
		stageMap.remove(id);
	}
	
	public static Stage getStage(StageID id) {
		return stageMap.get(id);
	}
	
	public static void quit() {
		if(primaryStage == null) {
			throw new IllegalStateException("Primary stage has not been set.");
		}
		
		primaryStage.close();
	}
}
