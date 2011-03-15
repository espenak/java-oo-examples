import javax.swing.*;        
import java.awt.*;

public class AboutMe {
	public static void main(String[] args) {
		JFrame frame = new JFrame("About me");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 2));

		frame.add(new JLabel("Name"));
		JTextField name = new JTextField(); 
		frame.add(name);

		frame.add(new JLabel("Biography"));
		JTextArea biography = new JTextArea();
		frame.add(biography);

		frame.add(new JLabel("Portrait"));
		ImageIcon image = new ImageIcon("bilder/skate.jpg");
		JLabel portrait = new JLabel(image);
		frame.add(portrait);

		frame.setPreferredSize(new Dimension(500, 400));
		frame.pack();
		frame.setVisible(true);
	}
}
