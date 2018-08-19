package com.samlic.emulator.core;

import org.junit.Assert;
import org.junit.Test;

public class ScriptStateMachineTest {
	@Test
	public void Test() {
		String input = "fdsafd<script>hello world!</script>few<script>hello yp!</script>";
		String output = new ScriptOutputParser(null).parse(input);
	
		System.out.println(output);
		String result = "fdsafdhello world!fewhello yp!";
		Assert.assertTrue(result.equals(output));		
	}
	
	@Test
	public void TestText() {
		String input = "<h>Get method test successfully.</h>";
		String output = new ScriptOutputParser(null).parse(input);
	
		System.out.println(output);
		Assert.assertTrue(input.equals(output));
	}
}
