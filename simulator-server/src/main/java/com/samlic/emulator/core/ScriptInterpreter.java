package com.samlic.emulator.core;

public interface ScriptInterpreter<T> {
	T interpret(String script);
	boolean equals(String ruleOne, String ruleTwo);
}
