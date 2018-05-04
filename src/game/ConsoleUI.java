package game;
import java.util.Scanner;

public class ConsoleUI {

	private Scanner sc = new Scanner(System.in);

	public void start(Game game) {
		while (!game.isEnded()) {
			System.out.println("---------------------------");
			System.out.println(game.currentPlayerName() + "'s turn");
			System.out.println("Position: " + game.currentPlayerPosition());
			System.out.println("Please hit enter to roll a dice.");

			// sc.nextLine();

			int face = game.currentPlayerRollDice();

			System.out.println("Dice face is " + face);

			if (face + game.currentPlayerPosition() > game.getBoardSize()) {
				face += game.getBoardSize() - game.currentPlayerPosition();
			}

			game.currentPlayerMovePiece(face);
			System.out.println("Positoin: " + game.currentPlayerPosition());
			if (game.currentPlayerWins()) {
				System.out.println(game.currentPlayer().getName() + "wins");
				game.end();
			}
			game.switchPlayer();
		}
	}

	public static void main(String[] args) {
		ConsoleUI ui = new ConsoleUI();
		Game game = new Game();
		ui.start(game);
	}

}
