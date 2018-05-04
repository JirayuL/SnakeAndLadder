package game;

public class Game {

	private Player[] players;

	private Die die;

	private Board board;

	private int currentPLayerIndex;

	private boolean ended;

	public Game() {
		reset();
	}
	
	public void reset() {
		players = new Player[2];
		players[0] = new Player("P1");
		players[1] = new Player("P2");

		die = new Die();
		board = new Board();
		ended = false;
		
		board.addPiece(players[0].getPiece(), 0);
		board.addPiece(players[1].getPiece(), 0);
	}
	
	public Board getBoard() {
		return board;
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public boolean isEnded() {
		return ended;
	}

	public void end() {
		ended = true;
	}

	public Player currentPlayer() {
		return players[currentPLayerIndex];
	}

	public void switchPlayer() {
		currentPLayerIndex = (currentPLayerIndex + 1) % players.length;
	}

	public String currentPlayerName() {
		return currentPlayer().getName();
	}

	public int currentPlayerRollDice() {
		die.roll();
		return die.getFace();
	}

	public int currentPlayerPosition() {
		return board.getPiecePosition(currentPlayer().getPiece());
	}
	
	public int getPlayerPosition(Player player){
		return board.getPiecePosition(player.getPiece());
	}

	public void currentPlayerMovePiece(int steps) {
		currentPlayer().movePiece(board, steps);
	}

	public boolean currentPlayerWins() {
		return board.pieceIsAtGoal(currentPlayer().getPiece());
	}
	
	public int getBoardSize() {
		return board.getSize();
	}
	
	public boolean ilegalMove(int face){
		return face + currentPlayerPosition() > board.getSize();
	}
	
	public int roll() {
		die.roll();
		return die.getFace();
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

			if (ilegalMove(face)) {
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

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}
