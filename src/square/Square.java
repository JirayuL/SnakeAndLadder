package square;

import java.util.ArrayList;
import java.util.List;

import game.Piece;

/**
 * Square that piece can land on it
 * 
 * @author Jirayu Laungwilawan
 *
 */
public class Square {

	private List<Piece> pieces;

	private int number;
	private SquareType type;
	private int destination;

	private boolean goal;

	public Square(int number) {
		this.number = number;
		this.type = SquareType.Square;
		pieces = new ArrayList<Piece>();
		goal = false;
	}

	public Square(int number, int destination) {
		this.number = number;
		this.type = SquareType.Square;
		pieces = new ArrayList<Piece>();
		this.destination = destination;
		goal = false;
	}

	/**
	 * Get the destination of the square lead to
	 * 
	 * @return the destination
	 */
	public int getDestination() {
		return destination;
	}

	/**
	 * Set the type of the square
	 * 
	 * @param type
	 *            that wqnt to set to the square
	 */
	protected void setType(SquareType type) {
		this.type = type;
	}

	/**
	 * Get the type of the square.
	 * 
	 * @return the type of the square.
	 */
	public SquareType getType() {
		return type;
	}

	/**
	 * Set the square to be the goal or not.
	 * 
	 * @param goal
	 *            is the boolean
	 */
	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	/**
	 * Add the piece to the square
	 * 
	 * @param piece
	 *            that want to add to the square
	 */
	public void addPiece(Piece piece) {
		pieces.add(piece);
	}

	/**
	 * Remove the piece from the square
	 * 
	 * @param piece
	 */
	public void removePiece(Piece piece) {
		pieces.remove(piece);
	}

	/**
	 * Check if the square has the piece
	 * 
	 * @param piece
	 *            that want to check
	 * @return true if the square has the piece otherwise false.
	 */
	public boolean hasPiece(Piece piece) {
		return pieces.contains(piece);
	}

	/**
	 * Check if the square is the goal.
	 * 
	 * @return true if the square is the goal.
	 */
	public boolean isGoal() {
		return goal;
	}

	/**
	 * Get the number of the square
	 * 
	 * @return the number of the square.
	 */
	public int getNumber() {
		return number;
	}

}
