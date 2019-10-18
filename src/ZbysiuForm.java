import javax.swing.*;
import java.awt.*;

public class ZbysiuForm extends JFrame {
    private ZbysiuPanel panel;

    public ZbysiuForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        panel = new ZbysiuPanel(this);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(layout);
        add(panel);
    }
}
