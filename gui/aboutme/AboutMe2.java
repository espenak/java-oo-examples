import javax.swing.*;        
import java.awt.*;

public class AboutMe2 extends JFrame {
	JTextField name;
	JTextArea biography;
	JLabel portrait;

	public AboutMe2() {
		super("About me");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(0, 2));

		add(new JLabel("Name"));
		name = new JTextField(); 
		add(name);

		add(new JLabel("Biography"));
		biography = new JTextArea();
		add(biography);

		add(new JLabel("Portrait"));
		ImageIcon image = new ImageIcon("bilder/skate.jpg");
		portrait = new JLabel(image);
		add(portrait);
	}

	public static void main(String[] args) {
		AboutMe2 a = new AboutMe2();
		a.setPreferredSize(new Dimension(500, 400));
		a.pack();
		a.setVisible(true);
	}
}
