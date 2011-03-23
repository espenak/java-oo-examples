import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Simple drawing application.
 * @author Jon Petter Åsen - jpaasen@ifi.uio.no
 */
public class Paint1010 extends JFrame {

    private enum BrushType {SQUARE, LINE, CIRCLE};

    private DrawablePanel dp;
    private JButton bEraser;
    private JButton bColor;
    private JButton bBrushSize;
    private JButton bBrushType;

    public Paint1010() {
        super("Paint1010");

        dp = new DrawablePanel();

        add(makeButtonPanel(), BorderLayout.NORTH);
        add(dp, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(50, 50);
        pack();
        setVisible(true);
    }

    private JPanel makeButtonPanel() {

        ButtonListener b = new ButtonListener();

        bColor = new JButton("Color");
        bColor.addActionListener(b);

        bBrushSize = new JButton("Brush size");
        bBrushSize.addActionListener(b);

        bBrushType = new JButton("Brush type");
        bBrushType.addActionListener(b);

        bEraser = new JButton("Eraser");
        bEraser.addActionListener(b);

        JPanel p = new JPanel();
        p.add(bColor);
        p.add(bBrushSize);
        p.add(bBrushType);
        p.add(bEraser);
        return p;
    }

    public class DrawablePanel extends JPanel {

        // Pre defined color and stroke tables.
        private Color[] colors = new Color[]{
                Color.BLACK, Color.RED, Color.CYAN, Color.YELLOW, Color.GREEN
        };
        private BasicStroke[] brushSizes = new BasicStroke[]{
                new BasicStroke(2), new BasicStroke(5),
                new BasicStroke(10), new BasicStroke(20)
        };

        private Color clearColor = Color.WHITE;
        private int currentColor = 0;
        private int currentBrushSize = 0;
        private BrushType brushType = BrushType.LINE;

        private Point prevMousePos;
        private Point currMousePos;

        private Image backBuffer;
        private Dimension panelDim;

        private boolean eraserOn;

        public DrawablePanel() {

            panelDim = new Dimension(600, 600);
            setPreferredSize(panelDim);

            // Create the back buffer. Paint it in the clearColor.
            backBuffer = new BufferedImage(
                    panelDim.width,
                    panelDim.height,
                    BufferedImage.TYPE_INT_RGB
            );
            Graphics g = backBuffer.getGraphics();
            g.setColor(clearColor);
            g.fillRect(0,0,panelDim.width, panelDim.height);
            g.setColor(colors[currentColor]);

            // Add brush listeners.
            BrushListener bs = new BrushListener();
            addMouseListener(bs);
            addMouseMotionListener(bs);
        }

        /**
         * Works as an iterator over available brush colors.
         * @return the next color.
         */
        public Color getNextColor() {
            currentColor = (currentColor + 1) % colors.length;
            return colors[currentColor];
        }

        /**
         * Works as an iterator over available brush sizes.
         * @return the next brush size.
         */
        public float getNextBrushSize() {
            currentBrushSize = (currentBrushSize + 1) % brushSizes.length;
            return brushSizes[currentBrushSize].getLineWidth();
        }

        public void moveToNextBrushType() {
            switch (brushType) {
                case SQUARE: brushType = BrushType.LINE; break;
                case LINE: brushType = BrushType.CIRCLE; break;
                case CIRCLE: brushType = BrushType.SQUARE; break;
            }
        }

        /**
         * Switch the erase state. on -> off or off -> on.
         */
        public void changeEraserState() {
            eraserOn = !eraserOn;
            if (eraserOn) {
                bEraser.setForeground(Color.RED);
                bColor.setEnabled(false);
            } else {
                bEraser.setForeground(Color.BLACK);
                bColor.setEnabled(true);
            }      
        }

        /**
         * Updates the internal mouse positions.
         * @param p is the new mouse position.
         */
        public void updateCurrPoint(Point p) {
            prevMousePos = currMousePos;
            currMousePos = p;
        }

        /**
         * Updates the back buffer based on the input point.
         * @param p The new line is drawn to this point.
         */
        public void updatePanel(Point p) {
            updateCurrPoint(p);

            Graphics2D g = (Graphics2D)backBuffer.getGraphics();

            g.setStroke(brushSizes[currentBrushSize]);

            if (eraserOn) {// we draw the line using the clearColor.
                g.setColor(clearColor);
            } else {
                g.setColor(colors[currentColor]);
            }

            if (prevMousePos != null) {

                int size = (int)brushSizes[currentBrushSize].getLineWidth();
                int half = size / 2;
                
                switch (brushType) {
                    case SQUARE:
                        g.fillRect(currMousePos.x - half, currMousePos.y - half, size, size);
                        break;
                    case LINE:
                        g.drawLine(prevMousePos.x, prevMousePos.y, currMousePos.x, currMousePos.y);
                        break;
                    case CIRCLE:
                        g.fillOval(currMousePos.x - half, currMousePos.y - half, size, size);
                        break;
                }
            }
                
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backBuffer, 0, 0, this); // display the back buffer.
        }
    }

    public class BrushListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            dp.updatePanel(e.getPoint());
        }

        public void mouseMoved(MouseEvent e) {
            dp.updateCurrPoint(e.getPoint());
        }

        public void mouseDragged(MouseEvent e) {
            dp.updatePanel(e.getPoint());
        }
    }

    class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == bEraser) {
                dp.changeEraserState();

            } else if (e.getSource() == bColor) {
                bColor.setForeground(dp.getNextColor());

            } else if (e.getSource() == bBrushSize) {
                bBrushSize.setText("Brush size (" + dp.getNextBrushSize()  + ")");

            } else if (e.getSource() == bBrushType) {
                dp.moveToNextBrushType();
            }
        }   
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Paint1010();
            }
        });
    }
}
