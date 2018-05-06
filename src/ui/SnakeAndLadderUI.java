package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import game.Board;
import game.Game;
import game.Player;
import square.Square;
import square.SquareType;

public class SnakeAndLadderUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton rollButton;

	private JLabel currentPlayerLabel;

	private JTextField currentPlayer;

	private JTextField numberOfFace;

	private JPanel bgBoard;

	private JLabel faceLabel;

	private JButton restartButton;

	private Game game;

	public SnakeAndLadderUI(Game game) {
		this.game = game;
		new JFrame("Snake and Ladder");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponent();
	}

	/**
	 * Initialize all component of UI.
	 */
	private void initComponent() {
		JPanel south = new JPanel();

		bgBoard = new BoardPanel();

		currentPlayerLabel = new JLabel("Current player:");

		currentPlayer = new JTextField(5);
		currentPlayer.setEnabled(false);

		faceLabel = new JLabel("Face:");

		numberOfFace = new JTextField(5);
		numberOfFace.setEnabled(false);

		rollButton = new JButton("roll");
		rollButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (game.currentPlayer().isFreeze()) {
					game.currentPlayer().unFreeze();
					game.switchPlayer();
				}
				currentPlayer.setText(game.currentPlayerName());

				System.out.println("Current: " + game.currentPlayerName());

				System.out.println(game.currentPlayerName() + " is at " + (game.currentPlayerPosition() + 1));

				int face = game.currentPlayerRollDice();
				numberOfFace.setText(String.format("%d", face));
				System.out.println("roll: " + face);

				if (game.isPlayerMoveOverBoard(face)) {
					final int boardSize = game.getBoardSize();
					face = boardSize - ((face + game.currentPlayerPosition()) % boardSize)
							- game.currentPlayerPosition() - 1;
					System.out.println("actual face: " + face);
				}
				game.currentPlayerMovePiece(face);
				bgBoard.repaint();
				System.out.println(game.currentPlayerName() + " is at " + (game.currentPlayerPosition() + 1));
//				 try {
//				 Thread.sleep(500);
//				 } catch (InterruptedException e1) {
//				// e1.printStackTrace();
//				 }
				Board board = game.getBoard();
				Square playerSquare = board.getPlayerSquare(game.currentPlayer().getPiece());
				if (board.pieceIsAtGoal(game.currentPlayer().getPiece())) {
					rollButton.setEnabled(false);
					restartButton.setEnabled(true);
					JOptionPane.showMessageDialog(null,
	                        game.currentPlayerName() + " wins!");
				} else if (playerSquare.getType() == SquareType.Freeze) {
					game.currentPlayer().freeze();
					System.out.println("Freeze");
				} else if (playerSquare.getType() == SquareType.BackWard) {
					game.currentPlayerMovePiece(-1);
					System.out.println("BackWard");
				} else if (playerSquare.getType() == SquareType.Ladder) {
					System.out.println("Ladder: " + (playerSquare.getDestination() - game.currentPlayerPosition()));
					game.currentPlayerMovePiece(playerSquare.getDestination() - game.currentPlayerPosition());
				} else if (playerSquare.getType() == SquareType.Snake) {
					System.out.println("Snake: " + (playerSquare.getDestination() - game.currentPlayerPosition()));
					System.out.println("destination: " + playerSquare.getDestination());
					System.out.println("position" + game.currentPlayerPosition());
					game.currentPlayerMovePiece(playerSquare.getDestination() - game.currentPlayerPosition());
				}
				System.out.println(game.currentPlayerName() + " is at " + (game.currentPlayerPosition() + 1));
				System.out.println("-----------------------------------------------");
				bgBoard.repaint();
				game.switchPlayer();
			}
		});

		restartButton = new JButton("Restart");
		restartButton.setEnabled(false);
		restartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.reset();
				rollButton.setEnabled(true);
				restartButton.setEnabled(false);
				bgBoard.repaint();
			}
		});

		south.add(currentPlayerLabel);
		south.add(currentPlayer);
		south.add(faceLabel);
		south.add(numberOfFace);
		south.add(rollButton);
		south.add(restartButton);

		this.add(bgBoard, BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);

		this.pack();
	}

	/**
	 * Set the visibility to true
	 */
	public void run() {
		this.setVisible(true);
	}

	/**
	 * JPanel that want to paint.
	 * 
	 * @author Jirayu Laungwilawan
	 *
	 */
	private class BoardPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image img;
		private Image stop;
		private Image back;
		private static final int SIZE = 600;

		public BoardPanel() {
			super();
			this.setPreferredSize(new Dimension(SIZE, SIZE));
			try {
				img = ImageIO.read(SnakeAndLadderUI.class.getResourceAsStream("/images/bg.jpg"));
				stop = ImageIO.read(SnakeAndLadderUI.class.getResourceAsStream("/images/stop.png"));
				back = ImageIO.read(SnakeAndLadderUI.class.getResourceAsStream("/images/back.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			final int BLOCK_SIZE = SIZE / 10;
			g.drawImage(img, 0, 0, SIZE, SIZE, null);
			for (Player player : game.getPlayers()) {
				int index = game.getPlayerPosition(player);
				// for (int index = 0; index < 100; index++) {
				int x;
				if ((index / 10) % 2 == 0) {
					x = BLOCK_SIZE * (index % 10);
				} else if (index == 19 || index == 39 || index == 59 || index == 79 || index == 99) {
					x = 0;
				} else {
					x = SIZE - (BLOCK_SIZE * ((index + 1) % 10));
				}
				if (player.getName().equals("P1")) {
					g.setColor(Color.RED);
				} else if (player.getName().equals("P2")) {
					g.setColor(Color.BLUE);
				} else if (player.getName().equals("P3")) {
					g.setColor(Color.YELLOW);
				} else if (player.getName().equals("P4")) {
					g.setColor(Color.GREEN);
				}
				g.fillOval(x + 10, SIZE - (BLOCK_SIZE * ((index / 10) + 1)) + 10, BLOCK_SIZE / 2, BLOCK_SIZE / 2);
				g.drawString(player.getName(), x + 10, SIZE - (BLOCK_SIZE * ((index / 10) + 1)) + 10);
				// }

				// System.out.println("x: " + x);
				// System.out.println("y: " + (SIZE - (BLOCK_SIZE * ((index / 10) + 1))));
			}
		}
	}

}