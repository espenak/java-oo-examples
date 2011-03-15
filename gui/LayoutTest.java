import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LayoutTest {

    protected JFrame frame;

    public LayoutTest() {
        frame = new JFrame("SWING layout test");
        //frame.setLayout(new BorderLayout()); // default layout

        frame.add(new JLabel("Welcome to the SWING layout test:)"),
                BorderLayout.NORTH);

        JPanel p = new JPanel();
        
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Superheroes "));

        p.setLayout(new GridLayout(3, 2));
        p.add(new JLabel("1"));
        p.add(new JLabel("Lynvingen"));
        p.add(new JLabel("2"));
        p.add(new JLabel("Superman"));
        p.add(new JLabel("3"));
        p.add(new JLabel("Invisible Woman"));
        frame.add(p, BorderLayout.CENTER);

        JButton b = new JButton("Click here!");
        frame.add(b, BorderLayout.SOUTH);

        // Show the window
        frame.pack();
        frame.setSize(new Dimension(300, 200));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LayoutTest();
            }
        });
    }
}
