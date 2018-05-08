package square;

/**
 * Ladder square that will take the player to the end of the ladder.
 * 
 * @author Jirayu Laungwilawan
 *
 */
public class Ladder extends Square {

	public Ladder(int number, int destination) {
		super(number, destination);
		super.setType(SquareType.Ladder);
	}

}
