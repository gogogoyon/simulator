package com.samlic.emulator.core;

/**
 * 脚本解析状态机
 * Input:  -------<---s---c---r---i---p---t--->---+++++---<---/---s---c---r---i---p---t--->-------
 * Status: None---Head------------------------Begin-------End-Tail------------------------Ready---
 * @author yuanpeng
 *
 */
public class ScriptStateMachine implements StateMachine<Character, MatchStatus, String> {

	private static final char[] pattern = "<script></script>".toCharArray();
	
	private int index;
	private StringBuilder value;
	private MatchStatus status;
	
	private StringBuilder headBack;
	
	public ScriptStateMachine() {
		reset();
	}
	
	@Override
	public MatchStatus next(Character input) {	
		char match = pattern[index];
		if(input == match) {
			if(status == MatchStatus.None || status == MatchStatus.Ready) {
				status = MatchStatus.Head;
				
				//Back up head character for roll back.
				headBack = new StringBuilder();
				headBack.append(input);
			}				
			
			if(status == MatchStatus.Head && match == '>') {
				status = MatchStatus.Begin;
			}
			
			if(status == MatchStatus.Begin && match == '<') {
				status = MatchStatus.End;				
			}
			
			if(status == MatchStatus.End) {
				status = MatchStatus.Tail;
			}
			
			index++;
			
			if(status == MatchStatus.Tail && match == '>') {
				status = MatchStatus.Ready;
				index = 0;					
			}
		}	

		if(status == MatchStatus.Begin) {
			value.append(input);
		}
		
		if(input != match) {
			if(status == MatchStatus.Head || status == MatchStatus.Ready ) {
				reset();
			}
			
			if(status == MatchStatus.End || status == MatchStatus.Tail) {
				throw new IllegalStateException("Script end tag not found.");
			}
		}
		
		return status;
	}
	
	public String getValue() {
		if(status != MatchStatus.Ready) {
			throw new IllegalStateException("Not ready.");
		}
		
		String result = value.toString();
		reset();
		
		return result.substring(1);
	}
	
	public String getRollback() {		
		String result = "";
		if(headBack != null) {
			result = headBack.toString();
			headBack = null;
		}
		
		return result;
	}
	
	private void reset() {
		index = 0;
		status = MatchStatus.None;
		value = new StringBuilder();
	}
}
