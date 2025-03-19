import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Button;

public class PlayNPause extends Applet {
    int x = 0, y = 0; // ball positions
    int dx = 5, dy = 5; // movements
    int ball_radius = 25;
    boolean right = true;
    boolean up = true;

    private BallThread ballThread; // Inner class instance
    boolean moving_flag = false;
    private Button startButton, pauseButton;

    public void init() {
        startButton = new Button("Start");
        pauseButton = new Button("Pause");

        add(startButton);
        add(pauseButton);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!moving_flag) {
                    moving_flag = true;
                    ballThread = new BallThread(); // Create a new thread
                    ballThread.start(); // Start the thread
                } else {
                    ballThread.resumeThread(); // Resume the thread if paused
                }
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moving_flag = false;
                ballThread.suspendThread(); // Pause the thread
            }
        });
    }

    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
    }

    // Inner class extending Thread
    private class BallThread extends Thread {
        public void run() {
            while (true) { 
                try {
                    if (right) {
                        x += dx;
                    } else {
                        x -= dx;
                    }

                    if (up) {
                        y -= dy;
                    } else {
                        y += dy;
                    }

                    if (x >= getWidth() - ball_radius) {
                        right = false;
                    } else if (x <= 0) {
                        right = true;
                    }

                    if (y >= getHeight() - ball_radius) {
                        up = true;
                    } else if (y <= 0) {
                        up = false;
                    }

                    repaint();
                    Thread.sleep(50); 
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        // Method to suspend the thread
        public void suspendThread() {
            this.suspend(); 
        }

        // Method to resume the thread
        public void resumeThread() {
            this.resume();
        }
    }
}
