package app;

import java.awt.*;

public class OComponent {
    //protected String id;
    int iX, iY;

    OComponent(int x, int y){
        iX = x;
        iY = y;
    }

    public int getX() {return iX; }
    public int getY() {return iY; }

    public void draw(Graphics g){
        g.drawLine(iX-5,iY,iX+5,iY);
        g.drawLine(iX,iY-5,iX,iY+5);
    }
}
