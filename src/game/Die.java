package game;

import java.util.Random;

/**
 * Dice class contain all behavior of dice.
 * 
 * @author Jirayu Laungwilawan
 *
 */
public class Die {

	/* Maximum face that dice can have */
	private static final int MAX_FACE = 6;

	/* face value of the dice */
	private int face;

	public Die() {
		face = 1;
	}

	/**
	 * Roll the dice
	 */
	public void roll() {
		face = new Random().nextInt(MAX_FACE) + 1;
	}

	/**
	 * Get the face value of the dice
	 * @return face of dice
	 */
	public int getFace() {
		return face;
	}

}
