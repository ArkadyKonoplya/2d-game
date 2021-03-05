/*
 * This class represents the screent where the player is redirected to once the game is complete
 * The game is complete if:
 * 1. The player loses
 * 2. The player completes all the levels
 */
package Game;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameLauncher extends JFrame {
	JLabel lblMessage = new JLabel();
	JButton btnPlay = new JButton();
	/*The message is passed as winning or losing message*/
		public GameLauncher(String message)
		{
			this.setTitle("Game Launcher");
			lblMessage.setText("GAME OVER : "+message);
			lblMessage.setHorizontalAlignment(JLabel.CENTER);
			btnPlay.setText("PLAY AGAIN");
			this.add(lblMessage);
			this.setPreferredSize(new Dimension(400, 300));
			this.pack();
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
}
