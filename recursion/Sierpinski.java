package recursion;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Random;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: Jon Petter Åsen
 * Date: 17.mar.2010
 * Time: 20:42:35
 */
public class Sierpinski extends JFrame {

    JPanel panel;
    Dimension dim;

    int levels; // number of refinement levels ak number of recursive calls

    Stack<Triangle> triangles;  // buffer for saving the final triangles
    Triangle parent;            // start triangle

    Sierpinski(int levels) {
        super("Sierpinski fractal");

        this.levels = levels;
        triangles = new Stack<Triangle>();

        JSlider s = new JSlider(0,6,0);
        s.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider)e.getSource();
                updateLevels(s.getValue());
                panel.repaint();
            }
        });
        s.setSnapToTicks(true);
        add(s, BorderLayout.SOUTH);

        panel = new TriPanel();
        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void updateLevels(int l) {
        if (l >= 0)
            levels = l;
    }

    public class TriPanel extends JPanel {

        Dimension dim;

        public TriPanel() {
            dim = new Dimension(600, 600);
            setPreferredSize(dim);

            // make the initial triangle
            parent = new Triangle(
                    0, dim.width/2, dim.width, // x1, x2, x3,
                    dim.height, // y1
                    (int)(dim.width*(1.0 - Math.sin(Math.PI/3))), // y2
                    dim.height); // y3
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            makeTriangles(parent, 0); // call the recursive method calculating the triangles

            Random r = new Random(); // for some random coloring 

            for (Polygon p: triangles) { // draw triangles
                g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                g.fillPolygon(p);
            }
            triangles.clear();
        }

        /**
         * Recursive method calculating new smaller triangles
         * @param t
         * @param curLevel
         */
        private void makeTriangles(Triangle t, int curLevel) {
            
            if (curLevel == levels) {
                // base level reached. Push the triangle on the stack.
                triangles.push(t);
            } else {
                // calculate 3 new triangles based on @param t
                // and do a recursive call with each on.
                Triangle[] smallerTriangles = t.getSierpinskiTri();
                for (Triangle tt: smallerTriangles)
                    makeTriangles(tt, curLevel+1);
            }
        }
    }

    /**
     * Class representing a triangle. Based on the java api class Polygon.
     */
    public class Triangle extends Polygon {
        
        public Triangle(int x1, int x2, int x3, int y1, int y2, int y3) {
             super(new int[]{x1, x2, x3}, new int[]{y1, y2, y3}, 3);
        }

        public Triangle(Point p1, Point p2, Point p3) {
            super(new int[]{p1.x, p2.x, p3.x}, new int[]{p1.y, p2.y, p3.y}, 3);
        }

        /**
         * @param i Point index.
         * @return The point at the specified index.
         */
        public Point getPoint(int i) {
            if (i >= npoints) return null;
            return new Point(xpoints[i], ypoints[i]);
        }

        /**
         * Calculates the mean point from two given points.
         * @param p1 Point one.
         * @param p2 Point two.
         * @return The mean point.
         */
        private Point mean(Point p1, Point p2) {
              return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
        }

        /**
         * Generates 3 new triangles inside this triangle.
         * @return The calculated triangles.
         */
        public Triangle[] getSierpinskiTri() {
            
            Triangle[] t = new Triangle[3];

            Point newP1 = mean(getPoint(0), getPoint(1));
            Point newP2 = mean(getPoint(1), getPoint(2));
            Point newP3 = mean(getPoint(2), getPoint(0));

            t[0] = new Triangle(getPoint(0), newP1, newP3);
            t[1] = new Triangle(newP1, getPoint(1), newP2);
            t[2] = new Triangle(newP3, newP2, getPoint(2));
            
            return t;
        }
    }

    public static void main(String[] args) {
        new Sierpinski(0);
    }
}
