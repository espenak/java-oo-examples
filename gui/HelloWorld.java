import javax.swing.*;
import java.awt.*;

public class HelloWorld extends JFrame {

    public HelloWorld() {
        JLabel label = new JLabel("Hello World!");
        //label.setPreferredSize(new Dimension(400, 400));

        //getContentPane().add();
        add(label, BorderLayout.NORTH);
        add(new JButton("OK"), BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //setSize(400, 400);
        //setPreferredSize(new Dimension(400, 400));

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        //javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {
        new HelloWorld();
        //    }
        //});
    }
}
