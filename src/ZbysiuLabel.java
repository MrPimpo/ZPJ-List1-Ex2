import java.awt.*;

public class ZbysiuLabel extends ZbysiuComponent {
    private String text;
    public ZbysiuLabel(int x, int y, String text){
        iX = x;
        iY = y;
        this.text = text;
    }

    public void update(String text) { this.text = text; }
    public void update(int text) { this.text = Integer.toString(text); }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.drawString(text,iX+5,iY+13);
    }
}
