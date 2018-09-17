package com.samlic.emulator.client;

public class TreeItemData {
	private String name;
	private ItemType type;
	private Object data;
	
	public TreeItemData(String name, ItemType type) {
		this.name = name;
		this.type = type;
	}
	
	public TreeItemData(String name, ItemType type, Object data) {
		this(name, type);
		this.data = data;
	}
	
	public ItemType getType() {
		return type;
	}
	
	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return name;
	}
	
	static enum ItemType {
		Root, Server, Grouping, Interface 
	}
}
