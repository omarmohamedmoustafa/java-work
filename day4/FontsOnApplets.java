import java.awt.Toolkit;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
  
// public class FontsOnApplets extends Applet {
//     String[] s;
//     public void init() {
//         s = Toolkit.getDefaultToolkit().getFontList();
//     }
//     public void paint(Graphics g) {
//         for (int i = 0; i < s.length; i++) {
//             Font f = new Font(s[i],Font.PLAIN, 20);
//             g.setFont(f);
//             g.drawString(s[i], 10, 20 + 20 * i);
//         }
//     }
// }

public class FontsOnApplets extends Applet {
    String[] s;
    public void init() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        s = ge.getAvailableFontFamilyNames();
        System.out.println("Number of available fonts: " + s.length);
    }
    public void paint(Graphics g) {
        for (int i = 0; i < s.length; i++) {
            Font f = new Font(s[i],Font.PLAIN, 20);
            g.setFont(f);
            g.drawString(s[i], 10, 20 + 20 * i);
        }
    }
}
