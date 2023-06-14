package test.runme;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestMe {
	
	@Test
	public void successTest() {
		System.out.println("JUnit running and test passing!");
	}
	
	@Test
	public void failureTest() {
		
		fail("JUnit running and test failing successfully!");
		
	}

}
