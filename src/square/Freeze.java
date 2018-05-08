package square;

/**
 * Freeze square that will freeze the player that land on
 * 
 * @author Jirayu Laungwilawan
 *
 */
public class Freeze extends Square {

	public Freeze(int number) {
		super(number);
		super.setType(SquareType.Freeze);
	}

}
