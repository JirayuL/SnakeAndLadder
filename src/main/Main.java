package main;

import game.Game;
import ui.SnakeAndLadderUI;

public class Main {
	
	public static void main(String[] args) {
		Game game = new Game();
		SnakeAndLadderUI ui = new SnakeAndLadderUI(game);
		ui.run();
	}

}
