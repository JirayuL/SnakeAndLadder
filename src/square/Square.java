package square;

import java.util.ArrayList;
import java.util.List;

import game.Piece;

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
	
	public Square(int number,int destination){
		this.number = number;
		this.type = SquareType.Square;
		pieces = new ArrayList<Piece>();
		this.destination = destination;
		goal = false;
	}
	
	public int getDestination() {
		return destination;
	}
	
	public void setType(SquareType type) {
		this.type = type;
	}
	
	public SquareType getType() {
		return type;
	}

	public void setGoal(boolean goal) {
		this.goal = goal;
	}

	public void addPiece(Piece piece) {
		pieces.add(piece);
	}

	public void removePiece(Piece piece) {
		pieces.remove(piece);
	}

	public boolean hasPiece(Piece piece) {
		return pieces.contains(piece);
	}

	public boolean isGoal() {
		return goal;
	}

	public int getNumber() {
		return number;
	}

}
