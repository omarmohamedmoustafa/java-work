import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class LineDrawApplet extends Applet {
    private int startX, startY, endX, endY;
    private boolean drawing;

    public void init() {
        startX = startY = endX = endY = 0;
        drawing = false;

        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMouseMotionListener());
    }

    public void paint(Graphics g) {
        g.drawLine(startX, startY, endX, endY);
    }

    // Inner class for MouseListener
    private class MyMouseListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
            drawing = true;
        }

        public void mouseReleased(MouseEvent e) {
            endX = e.getX();
            endY = e.getY();
            drawing = false;
            repaint();
        }

        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    // Inner class for MouseMotionListener
    private class MyMouseMotionListener implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {
            if (drawing) {
                endX = e.getX();
                endY = e.getY();
                repaint();
            }
        }

        public void mouseMoved(MouseEvent e) {
        }
    }
}