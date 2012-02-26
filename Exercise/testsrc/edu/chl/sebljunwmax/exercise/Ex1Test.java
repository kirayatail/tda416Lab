package edu.chl.sebljunwmax.exercise;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class Ex1Test {

	@Test
	public void stringWeave() {
		String a = "anna";
		String b = "patrik";
		String result = "apnantarik";
		
		assertEquals(result, Ex1.three("", a, b));
	}
	
	@Test
	public void six(){
		int n = 8;
		int k = 5;
		int result = 56;
		
		assertEquals(result, Ex1.six(n, k));
	}
}
