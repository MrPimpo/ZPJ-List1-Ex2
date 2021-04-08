package app;

import java.awt.*;

public class OButton extends OComponent {
    private final int iW, iH;
    private final String text;

    OButton(int x, int y, int width, int height, String text){
        super(x,y);
        iW = width;
        iH = height;
        this.text = text;
    }

    boolean intercepts(Point p){
        if (p.x<iX)
            return false;
        if (p.y<iY)
            return false;
        if (p.x>iX+iW)
            return false;
        if (p.y>iY+iH)
            return false;
        return true;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(iX,iY,iW,iH);
        g.drawString(text,iX+(iW/10),iY+(iH*2/3));
    }
}
