import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Jon Petter
 * Date: 13.apr.2010
 * Time: 16:13:41
 */
public class GuiFreeze extends JFrame {

    JButton b1;
    JButton b2;

    GuiFreeze(String title) {
        super(title);

        b1 = new JButton("Freeze");
        b2 = new JButton("Click me");

        b1.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   (new Thread("Long for loop") {
                       public void run() {
                           work(50);
                       }
                   }).start();
               }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JButton src = (JButton)e.getSource();

                Color forground = src.getForeground();

                if (forground == Color.GREEN)
                    src.setForeground(Color.BLACK);
                else
                    src.setForeground(Color.GREEN);
            }
        });

        add(b1, BorderLayout.NORTH);
        add(b2, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void work(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    System.out.println("(" + i + "," + j + "," + k + ")");    
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiFreeze("Gui Freeze Example");
            }
        });
    }
}
