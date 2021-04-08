package app;

import javax.swing.*;
import java.awt.*;

public class OForm extends JFrame {

    public OForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        OPanel panel = new OPanel(this);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(layout);
        add(panel);
    }
}
