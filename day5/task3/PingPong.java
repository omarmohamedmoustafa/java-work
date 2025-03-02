import java.applet.Applet;
import java.awt.Graphics;

public class PingPong extends Applet implements Runnable {
    Thread th;
    int x = 0, y = 0;  
    int step = 10;      
    boolean movingRight = true; 
    boolean movingDown = true;  

    public void init() {
        th = new Thread(this);
        th.start();
    }

    public void paint(Graphics g) {
        g.fillOval(x, y, 20, 20); 
    }

    public void run() {
        while (true) {
            try {
                repaint();
                Thread.sleep(50);

                if (movingRight) {
                    x += step;
                } else {
                    x -= step;
                }

                if (movingDown) {
                    y += step;
                } else {
                    y -= step;
                }

                if (x >= getWidth() - 20) {
                    movingRight = false;
                } else if (x <= 0) {
                    movingRight = true;
                }

                if (y >= getHeight() - 20) {
                    movingDown = false;
                } else if (y <= 0) {
                    movingDown = true;
                }

            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
