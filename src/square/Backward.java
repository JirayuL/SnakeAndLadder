package square;

/**
 * Backward square when land on will step back
 * 
 * @author Jirayu Laungwilawan
 *
 */
public class Backward extends Square {

	public Backward(int number, int destination) {
		super(number, destination);
		super.setType(SquareType.BackWard);
	}

}
