package game;

import java.util.*;
import square.*;

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

	private Map<Integer, Integer> ability;

	public Board() {
		squares = new Square[SIZE];
		ability = new HashMap<Integer, Integer>();
		initSquare();
		squares[squares.length - 1].setGoal(true);
	}

	private void initSquare() {
		initAbility();
		for (int i = 0; i < squares.length; i++) {
			if (ability.containsKey(i)) {
				Integer destination = ability.get(i);
				if (destination == 0) {
					squares[i] = new Freeze(i);
				} else if (destination == i-1) {
					squares[i] = new Backward(i, destination);
				} else if (destination > i) {
					squares[i] = new Ladder(i, destination);
				} else {
					squares[i] = new Snake(i, destination);
				}
			} else
				squares[i] = new Square(i);
		}
	}

	/**
	 * Add piece to the square[position]
	 * 
	 * @param piece
	 *            is the token of player.
	 * @param pos
	 *            is the position that want to move piece to.
	 */
	public void addPiece(Piece piece, int pos) {
		if (pos > SIZE - 1) {
			pos = SIZE - 1;
		}
		squares[pos].addPiece(piece);
	}

	/**
	 * Move the piece with the steps
	 * 
	 * @param piece
	 *            is the token of player.
	 * @param steps
	 *            is the step that will take.
	 */
	public void movePiece(Piece piece, int steps) {
		int pos = getPiecePosition(piece);
		squares[pos].removePiece(piece);
		addPiece(piece, pos + steps);
	}

	/**
	 * Get the position of the piece of player
	 * 
	 * @param piece
	 *            is the token of player.
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
	 * 
	 * @param piece
	 *            is the token of player.
	 * @return true if piece is at the goal otherwise false.
	 */
	public boolean pieceIsAtGoal(Piece piece) {
		return squares[getPiecePosition(piece)].isGoal();
	}

	/**
	 * Get square that player are on
	 * 
	 * @param piece
	 *            that want to know the square
	 * @return square that piece are on
	 */
	public Square getPlayerSquare(Piece piece) {
		for (Square s : squares) {
			if (s.hasPiece(piece)) {
				return s;
			}
		}
		return new Square(0);
	}

	/**
	 * Get size of the board.
	 * 
	 * @return size of the board
	 */
	public static int getSize() {
		return SIZE;
	}

	/**
	 * Initialize the ability square in the map.
	 */
	private void initAbility() {
		ability.put(1, 37);
		ability.put(4, 0);
		ability.put(6, 13);
		ability.put(7, 30);
		ability.put(8, 7);
		ability.put(13, 12);
		ability.put(14, 25);
		ability.put(15, 5);
		ability.put(18, 0);
		ability.put(20, 41);
		ability.put(21, 20);
		ability.put(26, 0);
		ability.put(27, 83);
		ability.put(31, 0);
		ability.put(35, 43);
		ability.put(36, 35);
		ability.put(43, 0);
		ability.put(45, 24);
		ability.put(47, 46);
		ability.put(48, 10);
		ability.put(50, 66);
		ability.put(53, 52);
		ability.put(58, 0);
		ability.put(61, 18);
		ability.put(63, 59);
		ability.put(65, 64);
		ability.put(68, 0);
		ability.put(70, 90);
		ability.put(71, 70);
		ability.put(73, 52);
		ability.put(77, 97);
		ability.put(79, 0);
		ability.put(82, 81);
		ability.put(86, 93);
		ability.put(88, 0);
		ability.put(88, 67);
		ability.put(91, 87);
		ability.put(91, 90);
		ability.put(94, 74);
		ability.put(96, 0);
		ability.put(98, 79);
	}
}
