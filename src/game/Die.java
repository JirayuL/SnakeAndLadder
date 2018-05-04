package game;
import java.util.Random;

public class Die {

	private static final int MAX_FACE = 6;

	private int face;

	public Die() {
		face = 1;
	}

	public void roll() {
		face = new Random().nextInt(MAX_FACE) + 1;
	}
	
	public int getFace() {
		return face;
	}

}
