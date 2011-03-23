import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CircleGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CircleWindow();
            }
        });
    }
}

class FigurePanel extends JPanel {
    private Point ovalCenter = new Point(61, 61);
    private Dimension ovalDim = new Dimension(100, 100);
    
    FigurePanel() {
        setPreferredSize(new Dimension(330, 330));
        addMouseListener(new OnMousePressed());
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                updatePoint(ovalCenter, e.getPoint());
            }
        });
    }


    private class OnMousePressed extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            updatePoint(ovalCenter, e.getPoint());
        }
    }

    private void updatePoint(Point to, Point from) {
        to.setLocation(from);
        repaint();
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw red oval
        g.setColor(Color.RED);
        g.drawOval(ovalCenter.x, ovalCenter.y, ovalDim.width, ovalDim.height);

        // Draw blue rectangle
        g.setColor(Color.BLUE);
        g.drawRect(161, 161, 100, 100);
    }
}


class CircleWindow extends JFrame {
    CircleWindow() {
        super("Figure frame");

        JPanel panel = new FigurePanel();
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(150, 100); // position on screen
        pack();
        setVisible(true);
    }
}
