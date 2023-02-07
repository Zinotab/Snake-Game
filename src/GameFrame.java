import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameFrame {

	
	ImageIcon icon  = new ImageIcon("snake.png");
	static JFrame frame = new JFrame();
	
	GameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		frame.setIconImage(icon.getImage());
		frame.add(new GamePanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Snake game");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}
	
}
