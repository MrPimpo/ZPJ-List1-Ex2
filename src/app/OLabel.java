package app;

import java.awt.*;

public class OLabel extends OComponent {
    private String text;
    OLabel(int x, int y, String text){
        super(x,y);
        this.text = text;
    }

    void update(String text) { this.text = text; }
    //public void update(int text) { this.text = Integer.toString(text); }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(text,iX+5,iY+13);
    }
}
