package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Class BBFrame. Bei New Level kommt das Fenster mit einem Gif hoch, dann
 * verschwindet:) Im Hintergrund ist das Spiel natuerlich pausiert
 */
public class BBFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	JPanel contentPane;

	/** The image label. */
	JLabel imageLabel = new JLabel();

	/** The header label. */
	JLabel headerLabel = new JLabel("New Level", SwingConstants.CENTER);

	/**
	 * Instantiates a new BB frame.
	 */
	public BBFrame() {
		try {
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			contentPane = (JPanel) getContentPane();
			contentPane.setLayout(new BorderLayout());
			setSize(new Dimension(500, 400));
			setTitle("Congratulation!");
			// add the header label
			headerLabel.setFont(new java.awt.Font("Comic Sans MS", Font.BOLD, 45));
			headerLabel.setBackground(Color.YELLOW);
			headerLabel.setOpaque(true);
			contentPane.add(headerLabel, BorderLayout.NORTH);
			// add the image label
			ImageIcon ii = new ImageIcon("gifs/BB_danger.gif");
			imageLabel.setIcon(ii);
			contentPane.add(imageLabel, BorderLayout.CENTER);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			// mach das Fenster Zzu
			// this.dispatchEvent(new WindowEvent(this,
			// WindowEvent.WINDOW_CLOSING));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
