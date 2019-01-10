package com.samlic.emulator.client;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import com.samlic.emulator.client.ManagedStage.StageID;
import com.samlic.emulator.client.TreeItemData.ItemType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class PrimaryController implements Initializable, ServerAddedListener {
	@FXML
	private MenuItem aboutMenu;
	@FXML
	private TreeView<TreeItemData> tvLeft;
	
	@FXML
	public void onTreeViewItemClick(MouseEvent event) {
		
	}
	
	@FXML
	public void onAddServer(ActionEvent event) {
		StageManager.getStage(StageID.ADD_SERVER).show();
	}
	
	@FXML
	public void onRemoveServer(ActionEvent event) {
		TreeItem<TreeItemData> item = tvLeft.getSelectionModel().getSelectedItem();
		if(item.getValue().getType() == ItemType.Server) {
			ServerInfoManager.removeServer((String)item.getValue().getData());
			tvLeft.getRoot().getChildren().remove(item);			
		}
	}
	
	@FXML
	public void onQuit(ActionEvent event) {
		StageManager.quit();		
	}
	
	@FXML
	public void onAboutMenuClick(ActionEvent event) {		
		StageManager.getStage(StageID.ABOUT).show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TreeItemData item = new TreeItemData("Servers", ItemType.Root);
		final TreeItem<TreeItemData> treeRoot = new TreeItem<TreeItemData>(item);
		tvLeft.setRoot(treeRoot);
		
		Set<String> serverList = ServerInfoManager.getServerList();
		if(serverList != null) {
			for(String server : serverList) {
				treeRoot.getChildren().add(makeServerTreeItem(new TreeItemData(server, ItemType.Server, server)));
			}
			treeRoot.setExpanded(true);
		}
		
		ServerInfoManager.registerAddListener(this);
		ServerContextMenu.getInstance().setTreeView(tvLeft);
		
		tvLeft.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {	
				MouseButton button = event.getButton();
				TreeItem<TreeItemData> selectedItem = tvLeft.getSelectionModel().getSelectedItem();
				if(selectedItem.getValue().getType() == ItemType.Server) {
					ServerContextMenu menu = ServerContextMenu.getInstance();
					if(menu.isShowing()) {
						menu.hide();
					} else if(button == MouseButton.SECONDARY){
						menu.show(selectedItem.getGraphic(), Side.BOTTOM, 0, 0);
					}
				} else if(selectedItem.getValue().getType() == ItemType.Interface) {
					
				}
			}
			
		});
	}

	@Override
	public void add(String server) {
		if(server != null && !server.isEmpty()) {
			tvLeft.getRoot().getChildren().add(makeServerTreeItem(new TreeItemData(server, ItemType.Server, server)));
			tvLeft.getRoot().setExpanded(true);
		}
	}
	
	private TreeItem<TreeItemData> makeServerTreeItem(TreeItemData data) {
		TreeItem<TreeItemData> dataItem = new TreeItem<TreeItemData>(data);
		ImageView svrImg = new ImageView(new Image(getClass().getResourceAsStream("server.bmp")));
		dataItem.setGraphic(svrImg);
		return dataItem;
	}
}
