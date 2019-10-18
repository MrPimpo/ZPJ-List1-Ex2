import javax.swing.*;
import java.awt.*;

class ZbysiuForm extends JFrame {

    ZbysiuForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        ZbysiuPanel panel = new ZbysiuPanel(this);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        panel.setLayout(layout);
        add(panel);
    }
}
