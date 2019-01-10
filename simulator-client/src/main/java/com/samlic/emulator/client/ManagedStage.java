package com.samlic.emulator.client;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ManagedStage extends Stage {
	private StageID id;
	
	public ManagedStage(StageID id) {
		super();
		this.id = id;
	}
	
	public ManagedStage(StageID id, StageStyle style) {
		super(style);
		this.id = id;
	}
	
	public StageID getId() {
		return id;
	}
	
	static class StageID {
		private String value;
	
		private StageID(String value) {this.value = value;}
		
		public static final StageID ABOUT = new StageID("about"); 
		
		public static final StageID ADD_SERVER = new StageID("add_server");
		
		@Override
		public String toString() {
			return value;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			StageID other = (StageID) obj;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}
	}
}
