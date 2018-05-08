package game;
/**
 * 
 * @author Wuttipat Nilsiri EZ
 *
 */
public class Turn {
	
	private Player p;
	private int steps;
	
	public Turn(Player p,int steps) {
		this.p = p;
		this.steps = steps;
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public int getDistance() {
		return steps;
	}

}
