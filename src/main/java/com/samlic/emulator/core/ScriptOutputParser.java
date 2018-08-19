package com.samlic.emulator.core;

public class ScriptOutputParser {
	private JavascriptInterpreter<String> interpreter;
	
	public ScriptOutputParser(JavascriptInterpreter<String> interpreter) {
		this.interpreter = interpreter;
	}
	
	public String parse(String scriptOutput) {		
		char[] charArray = scriptOutput.toCharArray();
		StringBuilder sb = new StringBuilder();
		ScriptStateMachine stateMachine = new ScriptStateMachine();
		
		for(int i=0; i < charArray.length; i ++ ) {
			MatchStatus status = stateMachine.next(charArray[i]);
			if(status == MatchStatus.None) {
				sb.append(stateMachine.getRollback());
				sb.append(charArray[i]);
			}
			
			if(status == MatchStatus.Ready) {
				if(interpreter != null) {
					sb.append(interpreter.interpret(stateMachine.getValue()));
				} else {
					sb.append(stateMachine.getValue());
				}
			}
		}
		
		return sb.toString();
	}
}
