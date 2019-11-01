package test;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestFox {

	@Before
	public void setUp() throws Exception {
		
	}

	/*Test if the fox's head and tail are on the correct coordinate
	 *eg. Not outside of wall, on top of obstacle, or on top of other Gamepiece.
	 * 
	 * */
	@Test
	public void testFoxPosition() {
		fail("Not yet implemented");
	}
	
	/* If fox's head and tail both on the same column, can only move vertically
	 * Case 1: No gamepiece in path
	 * Case 2: A gamepiece block the path
	 * Case 3: Hit the wall
	 * 
	 * */
	@Test
	public void testFoxMoveVertical() {
		fail("Not yet implemented");
	}
	
	/* If fox's head and tail both on the same row, can only move horizontally
	 * Case 1: No gamepiece in path
	 * Case 2: A gamepiece block the path
	 * Case 3: Hit the wall
	 * Case 4: 
	 * 
	 * */
	@Test
	public void testFoxMoveHorizontal() {
		fail("Not yet implemented");
	}
	
	
}
