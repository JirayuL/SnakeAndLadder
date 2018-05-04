package square;

public class Ladder extends Square {

	private int destination;

	public Ladder(int number, int destination) {
		super(number);
		this.destination = destination;
	}

	public int getDestination() {
		return destination;
	}

}
