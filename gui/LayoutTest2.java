import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LayoutTest2 extends JFrame {
    
    /**
     * A bordered panel.
     */
    public class SuperPanel extends JPanel {

        /**
         * Create the panel and add some data.
         *
         * @param title The title of the panel.
         */
        public SuperPanel(String title) {
            setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(),
                    title));
            setLayout(new GridLayout(3, 2));
            add(new JLabel("1"));
            add(new JLabel("Lynvingen"));
            add(new JLabel("2"));
            add(new JLabel("Superman"));
            add(new JLabel("3"));
            add(new JLabel("Invisible Woman"));
        }
    }

    public LayoutTest2() {
        super("SWING layout test"); // set the window title

        //setLayout(new BorderLayout()); // default
        add(new JLabel("Welcome to the SWING layout test:)"),
                BorderLayout.NORTH);

        add(new SuperPanel("Superheroes"), BorderLayout.CENTER);

        JButton b = new JButton("Click here!");
        b.addActionListener(new ButtonListener());
        add(b, BorderLayout.SOUTH);

        // Show the window
        pack();
        setSize(new Dimension(300, 200));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            getContentPane().getComponent(1).setBackground(Color.CYAN);
        }
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LayoutTest2();
            }
        });
	}
}
