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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Game;
import game.Player;

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

	private JLabel red;

	private JLabel blue;

	private Game game;

	public SnakeAndLadderUI(Game game) {
		this.game = game;
		new JFrame("Snake and Ladder");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponent();
	}

	private void initComponent() {
		JPanel south = new JPanel();

		bgBoard = new BoardPanel();

		// red = new JLabel(new ImageIcon("src/images/red.png"));
		//
		// blue = new JLabel(new ImageIcon("src/images/blue.png"));
		//
		// bgBoard.add(red, null);
		// bgBoard.add(blue, null);
		// // piece place at 15 15 size 30 30
		// red.setBounds(15, 520, 30, 30);
		// blue.setBounds(15, 520, 30, 30);

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
				currentPlayer.setText(game.currentPlayerName());
				System.out.println("Current: " + game.currentPlayerName());
				System.out.println(game.currentPlayerName() + " is at " + (game.currentPlayerPosition() + 1));
				int face = game.currentPlayerRollDice();
				numberOfFace.setText(String.format("%d", face));
				System.out.println("roll: " + face);
				if (game.ilegalMove(face)) {
					int distance = game.getBoardSize() - game.currentPlayerPosition();
					face = game.getBoardSize() - face - distance - game.currentPlayerPosition();
				}
				game.currentPlayerMovePiece(face);
				System.out.println(game.currentPlayerName() + " is at " + (game.currentPlayerPosition() + 1));
				if (game.getBoard().pieceIsAtGoal(game.currentPlayer().getPiece())) {

				}
				System.out.println("-----------------------------------------------");
				bgBoard.repaint();
				game.switchPlayer();
			}
		});

		south.add(currentPlayerLabel);
		south.add(currentPlayer);
		south.add(faceLabel);
		south.add(numberOfFace);
		south.add(rollButton);

		this.add(bgBoard, BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);

		this.pack();
	}

	public void run() {
		this.setVisible(true);
	}

	private class BoardPanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Image img;
		private static final int SIZE = 600;

		public BoardPanel() {
			super();
			this.setPreferredSize(new Dimension(SIZE, SIZE));
			try {
				img = ImageIO.read(SnakeAndLadderUI.class.getResourceAsStream("/images/bg.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void paintComponent(Graphics g) {
			final int BLOCK_SIZE = SIZE / 10;
			g.drawImage(img, 0, 0, SIZE, SIZE, null);
			for (Player player : game.getPlayers()) {
				int index = game.getPlayerPosition(player);
				// int index = 19;
//				 for (int index = 0; index < 100; index++) {

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
				} else {
					g.setColor(Color.BLUE);
				}
				g.fillOval(x + 10, SIZE - (BLOCK_SIZE * ((index / 10) + 1)) + 10, BLOCK_SIZE / 2, BLOCK_SIZE / 2);
				g.drawString(player.getName(), x + 10, SIZE - (BLOCK_SIZE * ((index / 10) + 1)) + 10);
//				 }

				// System.out.println("x: " + x);
				// System.out.println("y: " + (SIZE - (BLOCK_SIZE * ((index / 10) + 1))));
			}
		}
	}

}