import javax.swing.JButton;

/**
 * JumpInButton is an extension of JButton that will hold its own X and Y value.
 * @author Ashton
 *
 */
public class JumpInButton extends JButton{
	private int x, y;
	
	/**
	 * Create a new JumpInButton
	 * 
	 * @param x the x value of the button
	 * @param y the y value of the button
	 */
	public JumpInButton(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for X
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Getter for Y
	 */
	public int getY() {
		return this.y;
	}
}
