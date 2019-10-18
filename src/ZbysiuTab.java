import java.awt.*;

public class ZbysiuTab extends ZbysiuComponent {
    protected int cW, cH;
    protected MatrixO matrix;

    public ZbysiuTab(int cellWidth, int cellHeight){
        iX=5; iY=5;
        cW = cellWidth;
        cH = cellHeight;
    }

    public int getCellWidth() { return (cW); }
    public int getCellHeight() { return (cH); }

    public void setMatrix(MatrixO bm){ matrix = bm; }

    @Override
    public void draw(Graphics g){
        matrix.printBasicMatrix(g,this);
    }
}
