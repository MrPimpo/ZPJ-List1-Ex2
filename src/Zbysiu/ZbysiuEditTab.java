package Zbysiu;

import main.MatrixO;
import java.awt.*;

public class ZbysiuEditTab extends ZbysiuTab {
    private String[][] val;
    private int editCol, editRow;
    ZbysiuEditTab(int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
        iX=5;
        iY=5;
    }

    @Override
    public void setMatrix(MatrixO bm){
        editCol=0;
        editRow=0;
        matrix = bm;
        val = new String[bm.getN()][bm.getM()];
        for (int x=0; x<val[0].length; x++)
            for (int y=0; y<val.length; y++) {
                val[y][x] = "0";
            }
    }

    boolean intercepts(Point p){
        if (p.x<iX)
            return false;
        if (p.y<iY)
            return false;
        if (p.x>iX+(val[0].length*cW))
            return false;
        if (p.y>iY+(val.length)*cH)
            return false;
        return true;
    }

    void select(Point p){
        int relX = p.x - iX;
        int relY = p.y - iY;
        editCol = ( relX / getCellWidth() ) % val[0].length;
        editRow = ( relY / getCellHeight() ) % val.length;
    }

    void cut(){
        val[editRow][editCol] = val[editRow][editCol].substring(0, val[editRow][editCol].length() - 1);
    }

    void addChar(char ch){
        String cellEdition = val[editRow][editCol];
        switch (ch) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (cellEdition.equals("0"))
                    cellEdition = "" + ch;
                else
                    cellEdition = cellEdition + ch;
                break;
            case ',':
            case '.':
                if (cellEdition.isEmpty())
                    cellEdition = "0.";
                else if (!cellEdition.contains("."))
                    cellEdition = cellEdition + ".";
                break;
            case '-':
                if (cellEdition.isEmpty())
                    cellEdition = "-";
                break;
        }
        val[editRow][editCol] = cellEdition;
    }

    String editNext(String fin){
        //double dFin = Double.parseDouble(fin);
        editCol++;
        if (editCol >= val[0].length) {
            editCol=0;
            editRow++;
            if (editRow >= val.length) {
                editRow = 0;
            }
        }
        return val[editRow][editCol];
    }

    @Override
    MatrixO getMatrix() {
        for (int x=0; x<val[0].length; x++)
            for (int y=0; y<val.length; y++){
                matrix.set(x,y,Double.parseDouble(val[x][y]));
            }
        return matrix;
    }
    
    @Override
    public void draw(Graphics g){
        String[][] matrix = val;
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(iX,iY,iX-5+matrix[0].length*getCellWidth(),iY-5+matrix.length*getCellHeight());
        for (int x = 1; x < matrix[0].length; x++)
            g.drawLine(iX+x*getCellWidth(),iY,iX+x*getCellWidth(),iY+matrix.length*getCellHeight());

        for (int y=0; y<matrix.length; y++) {
            String s;
            g.drawLine(iX,iY+y*getCellHeight(),iX+matrix[0].length*getCellWidth(),iY+y*getCellHeight());
            for (int x = 0; x < matrix[y].length; x++){
                //java.texDecimalFormat df=new java.texDecimalFormat("0.00");
                //s=df.format(matrix[y][x])+"";
                s=matrix[y][x]+"";
                if ((y==editRow)&&(x==editCol)){
                    g.setColor(Color.BLUE);
                    g.fillRect(iX+1+x*getCellWidth(),iY+1+y*getCellHeight(),getCellWidth()-2,getCellHeight()-2);
                    g.setColor(Color.LIGHT_GRAY);
                }
                if (!s.startsWith("-"))
                    s = " "+s;
                g.drawString(s,iX+5+x*getCellWidth(),(int)(iY+(y+(double)2/3)*getCellHeight()));
            }
        }
    }
}
