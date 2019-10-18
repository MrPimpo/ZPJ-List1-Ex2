import java.awt.*;
import java.util.Random;

public class MatrixO {
    private double[][] matrix;
    private int n, m;

    MatrixO(){
        Random Roman = new Random();
        this.n = 2 + Roman.nextInt(9);
        this.m = 2 + Roman.nextInt(9);
        matrix = generate(n,m,20);
    }

    MatrixO(int n, int m){
        this.n = n;
        this.m = m;
        matrix = generate(n,m,30);
    }

    MatrixO(int n, int m, String a){
        this.n = n;
        this.m = m;
        matrix = generate(n,m,0);
    }

    int getM() { return m; }
    int getN() { return n; }
    void set(int x, int y, double val){
        matrix[x][y] = val;
    }

    public double elemAt(int x, int y){
        return (matrix[x%n][y%m]);
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

    public MatrixO mult(MatrixO matrixSecond) {
        double[][] m1 = matrix, m2 = matrixSecond.matrix,
                mFinal = new double[m1.length][m2[0].length];
        if (m1[0].length == m2.length) {
            for (int i = 0; i < m1.length; i++) {//ilosc wierszy tab1
                for (int j = 0; j < m2[0].length; j++) { //ilosc kolumn tab2
                    double temp = 0;
                    for (int w = 0; w < m2.length; w++) { //ilosc wierszy tab2
                        temp += m1[i][w] * m2[w][j];
                    }
                    mFinal[i][j] = temp;
                }
            }
        }
        MatrixO matrixFinal = new MatrixO(m1.length,m2[0].length);
        matrixFinal.matrix = mFinal;
        return matrixFinal;
    }

    public MatrixO add(MatrixO matrixSecnd){
        MatrixO matrixFinal = new MatrixO(n,m);
        for (int x=0; x<n; x++)
            for (int y=0; y<m; y++){
                matrixFinal.matrix[x][y] = this.matrix[x][y] + matrixSecnd.matrix[x][y];
            }
        return (matrixFinal);
    }

    public MatrixO odd(){
        MatrixO matrixFinal = new MatrixO(n,m);
        for (int x=0; x<n; x++)
            for (int y=0; y<m; y++){
                matrixFinal.matrix[x][y] = -this.matrix[x][y];
            }
        return (matrixFinal);
    }

    public MatrixO sub(MatrixO matrixSecnd){
        MatrixO matrixFinal = new MatrixO(n,m);
        for (int x=0; x<n; x++)
            for (int y=0; y<m; y++){
                matrixFinal.matrix[x][y] = this.matrix[x][y] - matrixSecnd.matrix[x][y];
            }
        return (matrixFinal);
    }

    boolean isSquare(){
        return (n == m);
    }

    double det() {
        double det = 0;

        if (matrix.length == 1) {
            det = matrix[0][0];
        } else if (matrix.length == 2) {
            det = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
        } else {
            double[][] nTab = new double[matrix.length + (matrix.length - 1)][matrix[0].length];
            for (int i = 0, _i = 0; i < nTab.length; i++, _i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (_i < matrix.length) {
                        nTab[i][j] = matrix[_i][j];
                    } else {
                        _i = 0;
                        nTab[i][j] = matrix[_i][j];
                    }
                }
            }

            double iloczyn = 1;
            int _i;

            for (int i = 0; i < matrix.length; i++) {
                _i = i;
                for (int j = 0; j < matrix[0].length; j++) {
                    iloczyn *= nTab[_i][j];
                    _i++;
                }
                det += iloczyn;
                iloczyn = 1;
            }

            iloczyn = 1;
            for (int i = 0; i < matrix.length; i++) {
                _i = i;
                for (int j = matrix[0].length - 1; j >= 0; j--) {
                    iloczyn *= nTab[_i][j];
                    _i++;
                }
                det -= iloczyn;
                iloczyn = 1;
            }
        }
        return det;
    }

    void printBasicMatrix(Graphics g, ZbysiuTab t){
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(t.iX,t.iY,matrix[0].length*t.getCellWidth(),matrix.length*t.getCellHeight());
        for (int x = 1; x < matrix[0].length; x++)
            g.drawLine(t.iX+x*t.getCellWidth(),t.iY,t.iX+x*t.getCellWidth(),t.iY+matrix.length*t.getCellHeight());

        for (int y=0; y<matrix.length; y++) {
            String s;
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
