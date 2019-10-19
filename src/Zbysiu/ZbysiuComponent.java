package Zbysiu;

import java.awt.*;

public class ZbysiuComponent {
    //protected String id;
    int iX, iY;

    ZbysiuComponent(int x, int y){
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
