package game;

import square.Square;

/**
 * This class will create a board consist of square that may be ladder, snake,
 * freeze or backward.
 * 
 * @author Jirayu Laungwilawan
 *
 */
public class Board {
	private static final int SIZE = 100;

	private Square[] squares;

	public Board() {
		squares = new Square[SIZE];
		for (int i = 0; i < squares.length; i++) {
			squares[i] = new Square(i);
		}
		squares[squares.length - 1].setGoal(true);
	}

	/**
	 * Add piece to the square[position]
	 * @param piece is the token of player.
	 * @param pos is the position that want to move piece to.
	 */
	public void addPiece(Piece piece, int pos) {
		squares[pos].addPiece(piece);
	}

	/**
	 * Move the piece with the steps
	 * @param piece is the token of player.
	 * @param steps is the step that will take.
	 */
	public void movePiece(Piece piece, int steps) {
		int pos = getPiecePosition(piece);
		squares[pos].removePiece(piece);
		addPiece(piece, pos + steps);
	}

	/**
	 * Get the position of the piece of player
	 * @param piece is the token of player. 
	 * @return the position of the piece if not on the board return -1.
	 */
	public int getPiecePosition(Piece piece) {
		for (Square s : squares) {
			if (s.hasPiece(piece)) {
				return s.getNumber();
			}
		}
		return -1;
	}

	/**
	 * Check whether the piece is at goal.
	 * @param piece is the token of player.
	 * @return true if piece is at the goal otherwise false.
	 */
	public boolean pieceIsAtGoal(Piece piece) {
		return squares[getPiecePosition(piece)].isGoal();
	}

	/**
	 * Get size of the board.
	 * @return size of the board
	 */
	public static int getSize() {
		return SIZE;
	}

}
