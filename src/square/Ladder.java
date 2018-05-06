package square;

public class Ladder extends Square {

	public Ladder(int number, int destination) {
		super(number, destination);
		super.setType(SquareType.Ladder);
	}

}
