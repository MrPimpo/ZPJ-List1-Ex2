import java.awt.*;

public class ZbysiuComponent {
    protected String id;
    protected int iX, iY;

    public void ZbysiuComponent(int x, int y){
        iX = x;
        iY = y;
    }

    public void draw(Graphics g){
        g.drawLine(iX-5,iY,iX+5,iY);
        g.drawLine(iX,iY-5,iX,iY+5);
    }
}
