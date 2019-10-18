import java.awt.*;
import java.util.Random;

public class MatrixO {
    private double matrix[][];
    private int n, m;

    public MatrixO(){
        Random Roman = new Random();
        this.n = 2 + Roman.nextInt(9);
        this.m = 2 + Roman.nextInt(9);
        matrix = generate(n,m,20);
    }

    public MatrixO(int n, int m){
        this.n = n;
        this.m = m;
        matrix = generate(n,m,30);
    }

    public MatrixO(int n, int m, String a){
        this.n = n;
        this.m = m;
        matrix = generate(n,m,0);
    }

    public int getM() { return m; }
    public int getN() { return n; }

    public double elemAt(int x, int y){
        return (matrix[x][y]);
    }

    private double[][] generate(int columns, int rows, int range){
        double[][] _matrix = new double[columns][rows];
        Random radek = new Random();
        for (int x=0; x<columns; x++)
            for (int y=0; y<rows; y++){
                _matrix[x][y] = -range + ( (range*2) * radek.nextDouble());
            }
        return (_matrix);
    }

    public void printBasicMatrix (Graphics g, ZbysiuTab t){
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(t.iX,t.iY,t.iX-5+matrix[0].length*t.getCellWidth(),t.iY-5+matrix.length*t.getCellHeight());
        for (int x = 1; x < matrix[0].length; x++)
            g.drawLine(t.iX+x*t.getCellWidth(),t.iY,t.iX+x*t.getCellWidth(),t.iY+matrix.length*t.getCellHeight());

        for (int y=0; y<matrix.length; y++) {
            String s = "";
            g.drawLine(t.iX,t.iY+y*t.getCellHeight(),t.iX+matrix[0].length*t.getCellWidth(),t.iY+y*t.getCellHeight());
            for (int x = 0; x < matrix[y].length; x++){
                //java.text.DecimalFormat df=new java.text.DecimalFormat("0.00");
                //s=df.format(matrix[y][x])+"";
                s=matrix[y][x]+"";
                if (!s.startsWith("-"))
                    s = " "+s;
                g.drawString(s,t.iX+5+x*t.getCellWidth(),(int)(t.iY+(y+(double)2/3)*t.getCellHeight()));
            }
        }
    }
}
