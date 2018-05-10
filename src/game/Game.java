package game;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
	private List<Turn> turnls;

	private Player[] players;

	private Die die;

	private Board board;

	private int currentPLayerIndex;

	private boolean ended;
	
	private State state = State.OVER;
	
	private int playerAmount;

	public Game(int playerAmount) {
		this.playerAmount = playerAmount;
		reset();
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State s) {
		state = s;
	}

	/**
	 * Reset method that restart everything
	 */
	public void reset() {
		
		state = State.RUNNING;
		turnls = new ArrayList<Turn>();
		die = new Die();
		board = new Board();
		ended = false;
	
		currentPLayerIndex = 0;
		players = new Player[playerAmount];

		for (int i = 0; i < players.length; i++) {
			players[i] = new Player("P" + (i + 1));
			board.addPiece(players[i].getPiece(), 0);
		}
	}

	/**
	 * Get the board
	 * 
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}
	
	public void setPlayers(int playerAmount) {
		this.playerAmount = playerAmount;
		reset();
	}

	/**
	 * Get the players
	 * 
	 * @return array of players
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * Check whether the game ended.
	 * 
	 * @return true if the game ended otherwise false.
	 */
	public boolean isEnded() {
		return ended;
	}

	/**
	 * Change the value of ended to true
	 */
	public void end() {
		ended = true;
	}

	/**
	 * Get current player
	 * 
	 * @return current player
	 */
	public Player currentPlayer() {
		return players[currentPLayerIndex];
	}

	/**
	 * Switch player's turn
	 */
	public void switchPlayer() {
		currentPLayerIndex = (currentPLayerIndex + 1) % players.length;
	}

	/**
	 * Get current player's name
	 * 
	 * @return current player's name
	 */
	public String currentPlayerName() {
		return currentPlayer().getName();
	}

	/**
	 * Get the face value of the dice rolled
	 * 
	 * @return the face value of the dice rolled
	 */
	public int currentPlayerRollDice() {
		die.roll();
		return die.getFace();
	}

	/**
	 * Get the position of the current player.
	 * 
	 * @return the position of the current player.
	 */
	public int currentPlayerPosition() {
		return board.getPiecePosition(currentPlayer().getPiece());
	}

	/**
	 * Get position of the player.
	 * 
	 * @param player
	 *            that want to know their position.
	 * @return the position of that player.
	 */
	public int getPlayerPosition(Player player) {
		return board.getPiecePosition(player.getPiece());
	}

	/**
	 * Move current player's piece by step
	 * 
	 * @param steps
	 *            that want to move
	 */
	public void currentPlayerMovePiece(int steps) {
		currentPlayer().movePiece(board, steps);
	}

	/**
	 * Check whether current player win.
	 * 
	 * @return true if current player win otherwise false.
	 */
	public boolean currentPlayerWins() {
		return board.pieceIsAtGoal(currentPlayer().getPiece());
	}

	/**
	 * Get size of the board.
	 * 
	 * @return size of the board
	 */
	public int getBoardSize() {
		return board.getSize();
	}

	/**
	 * Check if the piece move out of the board
	 * 
	 * @param face
	 *            value of the dice rolled
	 * @return true if the piece move out of board otherwise false.
	 */
	public boolean isPlayerMoveOverBoard(int face) {
		return face + currentPlayerPosition() > board.getSize();
	}

	public void start() {
		board.addPiece(players[0].getPiece(), 0);
		board.addPiece(players[1].getPiece(), 0);

		currentPLayerIndex = 0;

		while (!ended) {
			System.out.println("-------------------------------------------");

			Player currentPLayer = players[currentPLayerIndex];

			System.out.println("Current Player is " + currentPLayer.getName());
			System.out.println("The piece is at " + board.getPiecePosition(currentPLayer.getPiece()));

			int face = currentPLayer.roll(die);

			System.out.println("The die is rolled! Face is " + face);

			if (isPlayerMoveOverBoard(face)) {
				System.out.println("not legal");
				face = board.getSize() - (board.getPiecePosition(currentPLayer.getPiece()) + face);
			}

			currentPLayer.movePiece(board, face);

			System.out.println("The piece is at " + currentPlayerPosition());

			if (board.pieceIsAtGoal(currentPLayer.getPiece())) {
				System.out.println(currentPLayer.getName() + " wins.");
				ended = true;
			}
			switchPlayer();
		}
	}
	
	public void addTurn(Turn t) {
		turnls.add(t);
	}
	
	public List<Turn> getTurns() {
		return turnls;
	}

	public static void main(String[] args) {
		Game game = new Game(2);
		game.start();
	}

}
