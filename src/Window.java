
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

//Setup the window for game play
public class Window extends Canvas {

	public static final long serialVersionUID = 1L;

	public Window(int width, int height, String title, Main main) {

		JFrame frame = new JFrame(title);

		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(main);
		frame.setVisible(true);

		// Start running the content of the game
		main.start();
	}

}
