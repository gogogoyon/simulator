package com.samlic.emulator.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeView;

public class ServerContextMenu extends ContextMenu {
	private static ServerContextMenu menu = new ServerContextMenu();
	
	private TreeView<TreeItemData> treeView;
	
	private ServerContextMenu() {
		MenuItem openMenu = new MenuItem("open");
		openMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				menu.hide();
			}			
			
		});
		
		MenuItem closeMenu = new MenuItem("close");
		closeMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(treeView != null) {
					treeView.getSelectionModel().getSelectedItem().setExpanded(false);
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
