package square;

/**
 * Snake square will take the player to the tail of the snake.
 * 
 * @author Jirayu Laungwilawan
 *
 */
public class Snake extends Square {

	public Snake(int number, int destiantion) {
		super(number, destiantion);
		super.setType(SquareType.Snake);
	}

}
