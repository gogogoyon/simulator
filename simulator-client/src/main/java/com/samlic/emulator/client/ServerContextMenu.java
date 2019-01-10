package com.samlic.emulator.client;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.samlic.emulator.client.TreeItemData.ItemType;
import com.samlic.emulator.client.data.InterfaceCase;
import com.samlic.emulator.client.data.InterfaceCaseServiceClientImpl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class ServerContextMenu extends ContextMenu {
	private static ServerContextMenu menu = new ServerContextMenu();
	
	private TreeView<TreeItemData> treeView;
	
	private ServerContextMenu() {
		final MenuItem openMenu = new MenuItem("open");
		final MenuItem closeMenu = new MenuItem("close");
		closeMenu.setDisable(true);
		openMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				openMenu.setDisable(true);
				closeMenu.setDisable(false);
				final TreeItem<TreeItemData> item = treeView.getSelectionModel().getSelectedItem();				
				if(!item.isExpanded()) {
					new Thread() {
						public void run() {
							
							String server = (String)item.getValue().getData();						
							InterfaceCaseServiceClientImpl client = new InterfaceCaseServiceClientImpl(server, new RestTemplate());
							List<InterfaceCase> dataList = client.queryAll();
							for(InterfaceCase interfaceCase : dataList) {
								item.getChildren().add(new TreeItem<TreeItemData>(
										new TreeItemData(interfaceCase.getName(), ItemType.Interface, interfaceCase)));
							}
						}
					}.start();		
				}
				item.setExpanded(true);
				menu.hide();
			}			
			
		});
		
		
		closeMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				openMenu.setDisable(false);
				closeMenu.setDisable(true);
				if(treeView != null) {
					TreeItem<TreeItemData> item = treeView.getSelectionModel().getSelectedItem();
					item.getChildren().clear();
					item.setExpanded(false);
				}	
				menu.hide();
			}
			
		});
		
		
		getItems().add(openMenu);
		getItems().add(closeMenu);		
	}
	
	public static ServerContextMenu getInstance() {
		return menu;
	}
	
	public void setTreeView(TreeView<TreeItemData> treeView) {
		this.treeView = treeView;
	}
}
