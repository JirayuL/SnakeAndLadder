package square;

public class Snake extends Square {

	public Snake(int number, int destiantion) {
		super(number, destiantion);
		super.setType(SquareType.Snake);
	}

}
