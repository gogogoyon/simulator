package com.samlic.emulator.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerInfoManager {
	private static Set<String> serverList;
	
	private static List<ServerAddedListener> listenerList = new ArrayList<ServerAddedListener>();
	
	private ServerInfoManager() {}
	
	private static final Logger logger = LoggerFactory.getLogger(ServerInfoManager.class);
	
	private static void init() {
		if(serverList == null) {
			serverList = new HashSet<String>();
			String userDir = System.getenv("user.dir");			
			Path filePath = Paths.get(userDir, ".simulator", "serverInfo.txt");
			File file = filePath.toFile();
			File parent = file.getParentFile();
			
			if(!file.exists()) {
				parent.mkdirs();	
			} else {
				try {
					serverList.addAll(FileUtils.readLines(file));
				} catch (IOException e) {
					logger.error("Failed to read file {}", file);
					logger.error("Exception: ", e);
				}
			}
		}
	}
	
	public static Set<String> getServerList() {		
		init();		
		
		return serverList;
	}
	
	public static void addServer(String server) {		
		init();		
		
		serverList.add(server);
		for(ServerAddedListener listener : listenerList) {
			listener.add(server);
		}
	}
	
	public static void removeServer(String server) {		
		init();
				
		serverList.remove(server);
	}
	
	public static void registerAddListener(ServerAddedListener listener) {
		listenerList.add(listener);
	}
	
	public static void serialize() {
		if(serverList != null) {
			String userDir = System.getenv("user.dir");		
			Path filePath = Paths.get(userDir, ".simulator", "serverInfo.txt");
			File file = filePath.toFile();
			try {
				FileUtils.writeLines(file, serverList);
			} catch (IOException e) {
				logger.error("Failed to write file {}", file);
				logger.error("Exception: ", e);
			}
		}
	}
}
