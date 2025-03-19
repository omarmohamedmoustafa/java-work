import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ButtonCount extends Applet {
    private int count = 0;
    private Label label;
    private Button incrementButton, decrementButton;

    public void init() {

        label = new Label("ClickCount = " + count);
        add(label);

        // normal inner class
        incrementButton = new Button("Increment");
        incrementButton.addActionListener(new MyButtonListener());
        add(incrementButton);

        // anonymous inner class
        decrementButton = new Button("Decrement");
        decrementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                count--;
                label.setText("ClickCount = " + count);
            }
        });
        add(decrementButton);
    }

    private class MyButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            count++;
            label.setText("ClickCount = " + count);
        }
    }
}