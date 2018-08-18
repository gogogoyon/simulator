package com.samlic.emulator.core;

import org.junit.Assert;
import org.junit.Test;

public class ScriptStateMachineTest {
	@Test
	public void Test() {
		ScriptStateMachine stateMachine = new ScriptStateMachine();
		String input = "fdsafd<script>hello world!</script>few<script>hello yp!</script>";
		char[] charArray = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < charArray.length; i ++ ) {
			MatchStatus status = stateMachine.next(charArray[i]);
			if(status == MatchStatus.None) {
				String rollbackValue = stateMachine.getRollback();
				System.out.println("rollbackValue=" + rollbackValue);
				sb.append(rollbackValue);
				sb.append(charArray[i]);
			}
			
			if(status == MatchStatus.Ready) {				
				sb.append(stateMachine.getValue());
			}
		}
		
		System.out.println(sb.toString());
		String result = "fdsafdhello world!fewhello yp!";
		Assert.assertTrue(result.equals(sb.toString()));		
	}
	
	@Test
	public void TestText() {
		ScriptStateMachine stateMachine = new ScriptStateMachine();
		String input = "<h>Get method test successfully.</h>";
		char[] charArray = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < charArray.length; i ++ ) {
			MatchStatus status = stateMachine.next(charArray[i]);
			if(status == MatchStatus.None) {
				String rollbackValue = stateMachine.getRollback();
				System.out.println("rollbackValue=" + rollbackValue);
				sb.append(rollbackValue);
				sb.append(charArray[i]);
			}
			
			if(status == MatchStatus.Ready) {				
				sb.append(stateMachine.getValue());
			}
		}
		
		System.out.println(sb.toString());		
		Assert.assertTrue(input.equals(sb.toString()));		
	}
}
