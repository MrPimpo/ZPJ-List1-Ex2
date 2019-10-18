import java.awt.*;

public class ZbysiuTab extends ZbysiuComponent {
    private int cW, cH;
    MatrixO matrix;

    ZbysiuTab(int cellWidth, int cellHeight){
        super(5,5);
        iX=115; iY=5;
        cW = cellWidth;
        cH = cellHeight;
    }

    int getCellWidth() { return (cW); }
    int getCellHeight() { return (cH); }

    public void setCellWidth(int width) { cW = width; }
    public void setCellHeight(int height) { cH = height; }

    public void setMatrix(MatrixO bm){ matrix = bm; }

    @Override
    public void draw(Graphics g){
        matrix.printBasicMatrix(g,this);
    }
}
