import java.awt.*;

public class ZbysiuEditTab extends ZbysiuTab {
    private String[][] val;
    private boolean[][] ok;
    private int editCol, editRow;
    public ZbysiuEditTab(int cellWidth, int cellHeight) {
        super(cellWidth, cellHeight);
    }

    @Override
    public void setMatrix(MatrixO bm){
        editCol=0;
        editRow=0;
        matrix = bm;
        val = new String[bm.getN()][bm.getM()];
        ok = new boolean[bm.getN()][bm.getM()];
        for (int x=0; x<val[0].length; x++)
            for (int y=0; y<val.length; y++) {
                val[y][x] = "0";
                ok[y][x] = false;
            }
    }

    public void addChar(char ch){
        String cellEdition = val[editRow][editCol];
        switch (ch) {
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (cellEdition.startsWith("0"))
                    cellEdition = "" + ch;
                else
                    cellEdition = cellEdition + ch;
                break;
            case ',':
            case '.':
                if (cellEdition == "")
                    cellEdition = "0.";
                else if (!cellEdition.contains("."))
                    cellEdition = cellEdition + ".";
                break;
        }
        val[editRow][editCol] = cellEdition;
    }

    public String editNext(String fin){
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

    public MatrixO getMatrix() { return matrix; }
    
    @Override
    public void draw(Graphics g){
        String[][] matrix = val;
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(iX,iY,iX-5+matrix[0].length*getCellWidth(),iY-5+matrix.length*getCellHeight());
        for (int x = 1; x < matrix[0].length; x++)
            g.drawLine(iX+x*getCellWidth(),iY,iX+x*getCellWidth(),iY+matrix.length*getCellHeight());

        for (int y=0; y<matrix.length; y++) {
            String s = "";
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