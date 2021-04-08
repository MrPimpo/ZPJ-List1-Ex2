package app;

import main.MatrixO;
import java.awt.*;

public class OTab extends OComponent {
    int cW, cH;
    MatrixO matrix;

    OTab(int cellWidth, int cellHeight){
        super(5,5);
        iX=165; iY=5;
        cW = cellWidth;
        cH = cellHeight;
    }

    public int getCellWidth() { return (cW); }
    public int getCellHeight() { return (cH); }

    //public void setCellWidth(int width) { cW = width; }
    //public void setCellHeight(int height) { cH = height; }

    void setMatrix(MatrixO bm){
        matrix = bm;
    }
    MatrixO getMatrix() {
        return matrix;
    }

    @Override
    public void draw(Graphics g){
        matrix.draw(g,this);
    }
}
