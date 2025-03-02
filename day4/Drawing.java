import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

public class Drawing extends Applet {
    public void paint(Graphics g) {
 
 
        // top oval of lamp 
        g.setColor(Color.YELLOW);
        g.fillOval(500, 80, 150, 30);
    
		// the oval ouline color
		g.setColor(Color.BLACK);
		// the oval outline
        g.drawOval(500, 80, 150, 30);
		
		
		

        // lamp body ( two line +arc )
        g.drawLine(500, 100, 450, 250);  
        g.drawLine(650, 100, 700, 250); 
		
        g.drawArc(450, 230, 250, 45, 0, -180); 

       // 3 ovals in lamps
        g.setColor(Color.YELLOW);
        g.fillOval(555, 140, 40, 60);
        g.fillOval(505, 160, 40, 40);
        g.fillOval(605, 160, 40, 40);
        g.setColor(Color.BLACK);
        g.drawOval(555, 140, 40, 60);
        g.drawOval(505, 160, 40, 40);
        g.drawOval(605, 160, 40, 40);
		
		
		// Lamp stand
        g.setColor(Color.BLACK);
        g.drawLine(570, 275, 540, 320);
        g.drawLine(580, 275, 610, 320);

     
       // Base
        g.drawRect(525, 320, 100, 20);
    }
}
