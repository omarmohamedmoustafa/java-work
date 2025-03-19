import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class MultiLine extends Applet {
    private int startX, startY, endX, endY;
    private boolean drawing;
    private List<Line> lines; // List to store multiple lines
    private Label messageLabel; // Label to display the message

    public void init() {
        startX = startY = endX = endY = 0;
        drawing = false;
        lines = new ArrayList<>(); 

        // Initialize the label
        messageLabel = new Label("Limit of 3 lines reached!");
        messageLabel.setVisible(false); // Initially hidden

        setLayout(null);
        messageLabel.setBounds(10, 10, 200, 20); // Position and size of the label
        add(messageLabel);

        // Add mouse listeners
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMouseMotionListener());
    }

    public void paint(Graphics g) {
        for (Line line : lines) {
            g.drawLine(line.startX, line.startY, line.endX, line.endY);
        }
        if (drawing) {
            g.drawLine(startX, startY, endX, endY);
        }
    }

    // Inner class to represent a line
    private class Line {
        int startX, startY, endX, endY;

        Line(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }
    }

    // Inner class for MouseListener
    private class MyMouseListener implements MouseListener {
        public void mousePressed(MouseEvent e) {
            if (lines.size() < 3) {
                startX = e.getX();
                startY = e.getY();
                drawing = true;
            }
        }

        public void mouseReleased(MouseEvent e) {
            if (lines.size() < 3) {
                endX = e.getX();
                endY = e.getY();
                drawing = false;
                lines.add(new Line(startX, startY, endX, endY));
                repaint();
            }

            if (lines.size()  >=3) {
                messageLabel.setVisible(true);
                repaint(); 
            }
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
            if (drawing && lines.size() < 3) {
                endX = e.getX();
                endY = e.getY();
                repaint();
            }
        }

        public void mouseMoved(MouseEvent e) {
        }
    }
}
