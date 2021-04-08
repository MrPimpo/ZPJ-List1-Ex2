package main;

import app.OTab;

import java.awt.*;
import java.util.Random;

public class MatrixO {
    private double[][] matrix;
    private final int n, m;
    private MrHigher mrHigher;

    public MatrixO(){
        Random Randy = new Random();
        this.n = 2 + Randy.nextInt(9);
        this.m = 2 + Randy.nextInt(9);
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

    public void checkIfHigher(double val){
        mrHigher = new MrHigher(matrix, val);
        print_g(val);
    }
    public void set(int x, int y, double val){
        matrix[x][y] = val;
    }

    public double elemAt(int x, int y){
        return (matrix[x%n][y%m]);
    }

    private double[][] generate(int columns, int rows, int range){
        mrHigher = null;
        double[][] _matrix = new double[columns][rows];
        Random radek = new Random();
        for (int x=0; x<columns; x++)
            for (int y=0; y<rows; y++){
                _matrix[x][y] = -range + ( (range*2) * radek.nextDouble());
            }
        return (_matrix);
    }

    public MatrixO multiply(MatrixO matrixSecond) {
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

        { // --------- Printing --------- //
            System.out.println();
            System.out.println("Pomnożono macież: ");
            printM();
            System.out.println("Przez macierz:");
            matrixSecond.printM();
            System.out.println("Wynikiem mnożenia jest macierz:");
            matrixFinal.printM();
        }
        return matrixFinal;
    }

    public MatrixO add(MatrixO matrix2){
        MatrixO matrixFinal = new MatrixO(n,m);
        for (int x=0; x<n; x++)
            for (int y=0; y<m; y++){
                matrixFinal.matrix[x][y] = this.matrix[x][y] + matrix2.matrix[x][y];
            }
        { // --------- Printing --------- //
            System.out.println();
            System.out.println("Dodano macierz: ");
            printM();
            System.out.println("Do macierzy:");
            matrix2.printM();
            System.out.println("Wynikiem dodawania jest macierz:");
            matrixFinal.printM();
        }
        return (matrixFinal);
    }

    public MatrixO odd(){
        MatrixO matrixFinal = new MatrixO(n,m);
        for (int x=0; x<n; x++)
            for (int y=0; y<m; y++){
                if ((int)this.matrix[x][y]!=0)
                    matrixFinal.matrix[x][y] = -this.matrix[x][y];
                else
                    matrixFinal.matrix[x][y] = this.matrix[x][y];
            }
        return (matrixFinal);
    }

    public MatrixO sub(MatrixO matrixSecnd){
        MatrixO matrixFinal = new MatrixO(n,m);
        for (int x=0; x<n; x++)
            for (int y=0; y<m; y++){
                matrixFinal.matrix[x][y] = this.matrix[x][y] - matrixSecnd.matrix[x][y];
            }
        { // --------- Printing --------- //
            System.out.println();
            System.out.println("Od macierzy: ");
            printM();
            System.out.println("Odjęto macierz:");
            matrixSecnd.printM();
            System.out.println("Wynikiem odejmowania jest macierz:");
            matrixFinal.printM();
        }
        return (matrixFinal);
    }

    public boolean isSquare(){
        return (n == m);
    }

    public double det() {
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

            double product = 1;
            int _i;

            for (int i = 0; i < matrix.length; i++) {
                _i = i;
                for (int j = 0; j < matrix[0].length; j++) {
                    product *= nTab[_i][j];
                    _i++;
                }
                det += product;
                product = 1;
            }

            product = 1;
            for (int i = 0; i < matrix.length; i++) {
                _i = i;
                for (int j = matrix[0].length - 1; j >= 0; j--) {
                    product *= nTab[_i][j];
                    _i++;
                }
                det -= product;
                product = 1;
            }
        }
        return det;
    }

    private void printM(){
        int max=0;
        for (double[] doubles : matrix) {
            for (int x = 0; x < matrix[0].length; x++) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                String s = df.format(doubles[x]) + "";
                if (s.length()>max)
                    max=s.length();
            }
        }
        for (double[] doubles : matrix) {
            for (int x = 0; x < matrix[0].length; x++) {
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                StringBuilder s = new StringBuilder(df.format(doubles[x]) + "");
                for (int i=0; i<(max-s.length()); i++)
                    s.insert(0, " ");
                System.out.print("| " + s + " |");
            }
            System.out.println();
        }
    }

    private void print_g(double wart){
        System.out.println("Z macierzy: ");
        printM();
        System.out.println("Wartości większe niż "+wart+" to: ");
        for (double[] doubles : matrix)
            for (int x = 0; x < matrix[0].length; x++)
                if (doubles[x]>wart) {
                    java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                    String s = df.format(doubles[x]) + "";
                    System.out.print(s+"; ");
                }
        System.out.println();
    }

    public void draw(Graphics g, OTab t){
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(t.getX(),t.getY(),matrix[0].length*t.getCellWidth(),matrix.length*t.getCellHeight());
        for (int x = 1; x < matrix[0].length; x++)
            g.drawLine(t.getX()+x*t.getCellWidth(),t.getY(),t.getX()+x*t.getCellWidth(),t.getY()+matrix.length*t.getCellHeight());

        g.setColor(Color.gray);
        if (mrHigher!=null) {
            for (int y = 0; y < matrix.length; y++)
                for (int x = 0; x < matrix[y].length; x++)
                    if (mrHigher.isOn(y,x)){
                        g.fillRect(t.getX()+1+x*t.getCellWidth(),t.getY()+1+y*t.getCellHeight(),t.getCellWidth()-2,t.getCellHeight()-2);
                    }

        }

        g.setColor(Color.LIGHT_GRAY);
        for (int y=0; y<matrix.length; y++) {
            String s;
            g.drawLine(t.getX(),t.getY()+y*t.getCellHeight(),t.getX()+matrix[0].length*t.getCellWidth(),t.getY()+y*t.getCellHeight());
            for (int x = 0; x < matrix[y].length; x++){
                java.text.DecimalFormat df=new java.text.DecimalFormat("0.00");
                s=df.format(matrix[y][x])+"";
                //s=matrix[y][x]+"";
                if (!s.startsWith("-"))
                    s = " "+s;
                g.drawString(s,t.getX()+5+x*t.getCellWidth(),(int)(t.getY()+(y+(double)2/3)*t.getCellHeight()));
            }
        }
    }

    private static class MrHigher {
        private final boolean[][] matrix;

        MrHigher(double[][] matrix, double val) {
            this.matrix = new boolean[matrix.length][matrix[0].length];
            for (int y = 0; y < matrix.length; y++)
                for (int x = 0; x < matrix[y].length; x++) {
                    this.matrix[y][x] = (matrix[y][x] > val);
                }
        }

        boolean isOn(int column, int row) {
            return (matrix[column][row]);
        }
    }
}
