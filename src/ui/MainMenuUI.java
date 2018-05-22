package ui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import game.Game;
/**
 * This class will launch the main menu of the Snake and Ladder.
 * @author Theeruth Borisuth
 *
 */
public class MainMenuUI extends JFrame {

	
	private static final long serialVersionUID = 1L;

	private JButton twoPlayer;

	private JButton threePlayer;

	private JButton fourPlayer;

	private JButton exit;



	public MainMenuUI() {
		initComponent();
	}
	
	/**
	 * Initialize all component of menu's UI.
	 */
	private void initComponent() {
		this.setTitle("Snake And Ladder");
		this.setLayout(new BorderLayout());
		try {
			BufferedImage myPicture = ImageIO.read(MainMenuUI.class.getResourceAsStream("/images/mainmenupic.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			this.add(picLabel, BorderLayout.NORTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

		twoPlayer = new JButton("2P");
		threePlayer = new JButton("3P");
		fourPlayer = new JButton("4P");
		exit = new JButton("Exit");

		JPanel panel = new JPanel();
		panel.add(twoPlayer);
		panel.add(threePlayer);
		panel.add(fourPlayer);		
		this.add(panel,BorderLayout.CENTER);

		JPanel panel2 = new JPanel();
		panel2.add(exit);
		this.add(panel2, BorderLayout.SOUTH);

		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);  

		twoPlayer.addActionListener(new PlayerListener(2));
		threePlayer.addActionListener(new PlayerListener(3));
		fourPlayer.addActionListener(new PlayerListener(4));
		exit.addActionListener(new ExitListener());


	}
	
	/**
	 * Set the visibility to true
	 */
	public void run() {
		this.setVisible(true);
	}
	
	/**
	 * for testing
	 * @param args
	 */
	public static void main(String[] args) {
		MainMenuUI menu = new MainMenuUI();
		menu.run();
	}
	
	/**
	 * ActionListener for player buttons.
	 * @author Theeruth Borisuth
	 *
	 */
	class PlayerListener implements ActionListener{

		private int player;

		public PlayerListener(int player) {
			this.player = player;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Game game = new Game(player);
			setVisible(false);
			new SnakeAndLadderUI(game).run();
		}		
	}
	
	/**
	 * ActionListener for exit button
	 * @author Theeruth Borisuth
	 *
	 */
	class ExitListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}		
	}
}

