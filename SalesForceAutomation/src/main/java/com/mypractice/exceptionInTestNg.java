package com.mypractice;
import org.testng.annotations.Test;
public class exceptionInTestNg {

	
	public class TestNGExamples {
		
		@Test(expectedExceptions=ArithmeticException.class)
		public void dividedByZeroExample1(){
			int e = 1/0;
		}
		
		@Test
		public void dividedByZeroExample2(){
			int e = 1/0;
		}
	}
	
	
	
	
}
