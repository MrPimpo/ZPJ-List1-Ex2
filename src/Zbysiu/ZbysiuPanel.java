package Zbysiu;

import main.MatrixO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ZbysiuPanel extends JPanel implements MouseListener{
    private ZbysiuForm padre;
    private byte simulation;
    // ----- SIM 0 ----- // ----- Main Menu ----- //
    private ZbysiuLabel lblQuestion;
    private ZbysiuButton btnClassic, btnCustom, btnMostCustom;
    // ----- SIM 1 ----- // ----- "Final" tab ----- //
    private ZbysiuTab tab;
    private ZbysiuButton btnReturno, btnInverse, btnAdd, btnSubtract, btnMultiply, btnGet;
    private ZbysiuLabel lblDet;
    // ----- SIM 2,4,8 ----- //  ----- Size choosing ----- //
    private ZbysiuLabel lblWidth, lblHeight;
    private ZbysiuButton btnWidthIncrease, btnWidthDecrease, btnHeightIncrease, btnHeightDecrease;
    private ZbysiuButton btnOK;
    private int width, height;
    // ----- SIM 3,5 ----- //  ----- Edit Tab ----- //
    private ZbysiuEditTab tabEdit;
    private ZbysiuButton btnReady;
    // ----- SIM 9 ----- //  ----- Getting V ----- //
    private String V;
    private ZbysiuLabel lblV;
    private ZbysiuButton btnFine;

    ZbysiuPanel(ZbysiuForm papa){
        padre = papa;
        simulation = 0;

        // ----- SIM 0 ----- // ----- Main Menu ----- //
        padre.setSize(520,150);
        padre.setTitle("Wybierz macierz");
        lblQuestion = new ZbysiuLabel(25, 20,"Wybierz macierz:");
        btnClassic = new ZbysiuButton(25,40,125,40,"Macierz losowa");
        btnCustom = new ZbysiuButton(175,40,125,40,"Inne wymiary");
        btnMostCustom = new ZbysiuButton(325,40,150,40, "Sam se wpisuj");
        // ----- SIM 1 ----- // ----- Final table ----- //
        tab = new ZbysiuTab(140,25);
        // ----- SIM 2,4 ----- // ----- Size settings ----- //
        btnWidthDecrease = new ZbysiuButton(10,10,20,20,"<");
        lblWidth = new ZbysiuLabel(40,10,"kolumny: ");
        btnWidthIncrease = new ZbysiuButton(150,10,20,20,">");
        btnHeightDecrease = new ZbysiuButton(10,30,20,20,"<");
        lblHeight = new ZbysiuLabel(40,30,"wiersze: ");
        btnHeightIncrease = new ZbysiuButton(150,30,20,20,">");
        btnOK = new ZbysiuButton(10,60,160,30,"OK");
        // ----- SIM 3 ----- // ----- Fil in table ----- //
        tabEdit = new ZbysiuEditTab(50,30);
        // ----- SIM 9 ----- //
        V="";
        btnFine = new ZbysiuButton(10,60,160,30,"OK");
        lblV = new ZbysiuLabel(10,10,"Wartość = ");

        setFocusable(true);
        addMouseListener(this);
        addKeyListener(new KListener(this));
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private byte getSimulation() {
        return simulation;
    }

    private void showTab(MatrixO matrixO){
        simulation = 1;
        tab = new ZbysiuTab(140,25);
        width = matrixO.getM();
        height = matrixO.getN();
        tab.setMatrix(matrixO);
        int W = width * tab.getCellWidth() + 188, H = Math.max(285,height * tab.getCellHeight() + 50);
        padre.setSize(W, H);
        padre.setTitle("Macierz " + width + "x" + height);
        btnReturno = new ZbysiuButton(10, H - 75, 100, 30, "Wracaj");
        btnInverse = new ZbysiuButton(10, 35, 100, 30, "Zmień znak");
        btnAdd = new ZbysiuButton(10, 70, 100, 30, "Dodaj");
        btnSubtract = new ZbysiuButton(10, 105, 100, 30, "Odejmij");
        btnMultiply = new ZbysiuButton(10, 140, 100, 30, "Przemnóż");
        btnGet = new ZbysiuButton(10, 175, 100, 30, "Pokaż powyżej");
        lblDet = new ZbysiuLabel(10, 10,"Brak wyznacznika");
        if (matrixO.isSquare()) {
            java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
            String s = df.format(matrixO.det()) + "";
            lblDet.update("Det = " + s);
        }
        this.repaint();
    }

    private void editTab(int sim, MatrixO m){
        width = m.getM();
        height = m.getN();
        editTab(sim);
    }
    private void editTab(int sim){
        simulation = (byte)sim;
        tabEdit.setMatrix(new MatrixO(height, width, ""));
        int W = width * tabEdit.getCellWidth() + 135, H = height * tabEdit.getCellHeight() + 50;
        padre.setSize(W, H);
        padre.setTitle("Macierz " + width + "x" + height);
        btnReady = new ZbysiuButton(10, H - 75, 90, 30, "Zrobione");
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = new Point(e.getX(),e.getY());
        System.out.print("Sym: "+simulation+". KlikuKllik. ");
        switch(simulation){
            case 0: { // ----- Main Menu ----- //
                if (btnClassic.intercepts(p)) {
                    showTab(new MatrixO());
                    System.out.print("Klik w losowy ");
                }
                if (btnCustom.intercepts(p)) {
                    simulation = 2;
                    width = 10;
                    height = 10;
                    lblWidth.update("kolumny: 10");
                    lblHeight.update("wiersze: 10");
                    padre.setSize(200, 150);
                    this.repaint();
                    System.out.print("Klik w inny ");
                }
                if (btnMostCustom.intercepts(p)) {
                    simulation = 4;
                    width = 5;
                    height = 5;
                    lblWidth.update("kolumny: 5");
                    lblHeight.update("wiersze: 5");
                    padre.setSize(200, 150);
                    this.repaint();
                    System.out.print("Klik w najinniejszy ");
                }
            }break;
            case 1: { // ----- Final table ----- //
                if (btnReturno.intercepts(p)) {
                    simulation = 0;
                    padre.setSize(520, 150);
                    padre.setTitle("Wybierz macierz");
                    this.repaint();
                    System.out.print("Klik w powrotny ");
                }
                // btnAdd, btnSubtract, btnMultiply, btnGet;
                if (btnInverse.intercepts(p)) {
                    showTab(tab.getMatrix().odd());
                    System.out.print("Klik w odwracanko ");
                }
                if (btnAdd.intercepts(p)) {
                    MatrixO m = tab.getMatrix();
                    editTab(5,m);
                    System.out.print("Klik w dodawanko ");
                }
                if (btnSubtract.intercepts(p)) {
                    MatrixO m = tab.getMatrix();
                    editTab(6,m);
                    System.out.print("Klik w odejmowawanko ");
                }
                if (btnMultiply.intercepts(p)) {
                    simulation = 8;
                    MatrixO m = tab.getMatrix();
                    height = m.getM();
                    width = m.getN(); // ten jest możliwy do zmiany
                    lblWidth.update("kolumny: "+width);
                    lblHeight.update("wiersze: "+height);
                    padre.setSize(200, 150);
                    this.repaint();
                    System.out.print("Klik w inny ");
                    //MatrixO m = tab.getMatrix();
                    //editTab(7,m);
                    //System.out.print("Klik w mnożonko ");
                }
                if (btnGet.intercepts(p)) {
                    simulation = 9;
                    lblV.update("Wartość ="+V);
                    padre.setSize(200, 150);
                    padre.setTitle("Pokaż pola powyżej...");
                    this.repaint();
                    System.out.print("Klik w dziwne coś ");
                }
            }break;
            case 2: case 4: case 8: { // ----- Size settings ----- //
                if (btnWidthIncrease.intercepts(p)) {
                    width++;
                    lblWidth.update("kolumny: " + width);
                    this.repaint();
                }
                if (btnWidthDecrease.intercepts(p)) {
                    width--;
                    if (width < 1)
                        width = 1;
                    lblWidth.update("kolumny: " + width);
                    this.repaint();
                }
                if (simulation!=8) {
                    if (btnHeightIncrease.intercepts(p)) {
                        height++;
                        lblHeight.update("wiersze: " + height);
                        this.repaint();
                    }
                    if (btnHeightDecrease.intercepts(p)) {
                        height--;
                        if (height < 1)
                            height = 1;
                        lblHeight.update("wiersze: " + height);
                        this.repaint();
                    }
                }
                if (btnOK.intercepts(p)) {
                    switch (simulation) {
                        case 2:{
                            showTab(new MatrixO(height, width));
                            System.out.print("Klik w OK ");
                        }break;
                        case 4:{
                            editTab(3);
                            System.out.print("Klik w OK ");
                        }break;
                        case 8: {
                            editTab(7);
                            System.out.print("Klik w OK ");
                        }break;
                    }
                }
            }break;
            case 3: case 5: case 6: case 7: {
                if (tabEdit.intercepts(p)) {
                    tabEdit.select(p);
                    repaint();
                    System.out.print("Klik w tabelkie ");
                    //tab.setCellWidth(50);
                }
                switch (simulation) {
                    case 3: { // ----- Fil in table ----- //
                        if (btnReady.intercepts(p)) {
                            showTab(tabEdit.getMatrix());
                            //tab.setCellWidth(50);
                            System.out.print("Klik w koniec edycji ");
                        }
                    }
                    break;
                    case 5: { // ----- Fill in table for adding ----- //
                        if (btnReady.intercepts(p)) {
                            MatrixO m1 = tab.getMatrix();
                            MatrixO m2 = tabEdit.getMatrix();
                            MatrixO m3 = m1.add(m2);
                            showTab(m3);
                            //tab.setCellWidth(50);
                            System.out.print("Klik w koniec edycji dodawania ");
                        }
                    }
                    break;
                    case 6: { // ----- Fill in table for subtracting ----- //
                        if (btnReady.intercepts(p)) {
                            MatrixO m1 = tab.getMatrix();
                            MatrixO m2 = tabEdit.getMatrix();
                            MatrixO m3 = m1.sub(m2);
                            showTab(m3);
                            //tab.setCellWidth(50);
                            System.out.print("Klik w koniec edycji odejmowania ");
                        }
                    }
                    break;
                    case 7: { // ----- Fill in table for multiplying ----- //
                        if (btnReady.intercepts(p)) {
                            MatrixO m1 = tab.getMatrix();
                            MatrixO m2 = tabEdit.getMatrix();
                            MatrixO m3 = m1.mult(m2);
                            showTab(m3);
                            //tab.setCellWidth(50);
                            System.out.print("Klik w koniec edycji mnożenia ");
                        }
                    }
                    break;
                }
            }
            case 9: {
                if (btnFine.intercepts(p)){
                    double val = Double.parseDouble(V);
                    MatrixO m = tab.getMatrix();
                    m.checkIfHigher(val);
                    showTab(m);
                }
            }break;
        }
        requestFocusInWindow();
        //System.out.println();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        System.out.println(" ==> Sym: "+simulation);

        switch(simulation){
            case 0:
                lblQuestion.draw(g);
                btnCustom.draw(g);
                btnClassic.draw(g);
                btnMostCustom.draw(g);
                break;
            case 1:
                tab.draw(g);
                btnReturno.draw(g);
                lblDet.draw(g);
                btnInverse.draw(g);
                btnAdd.draw(g);
                btnSubtract.draw(g);
                btnMultiply.draw(g);
                btnGet.draw(g);
                break;
            case 2: case 4:
                btnHeightDecrease.draw(g);
                btnHeightIncrease.draw(g);
            case 8:
                btnWidthDecrease.draw(g);
                btnWidthIncrease.draw(g);
                lblWidth.draw(g);
                lblHeight.draw(g);
                btnOK.draw(g);
                break;
            case 3: case 5: case 6: case 7:
                tabEdit.draw(g);
                btnReady.draw(g);
                break;
            case 9:
                lblV.draw(g);
                btnFine.draw(g);
                break;
        }
    }

    private static class KListener extends KeyAdapter {
        ZbysiuPanel madre;
        KListener(ZbysiuPanel mama){
            madre = mama;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if ((madre.getSimulation() == 3) || (madre.getSimulation() == 5) || (madre.getSimulation() == 6) || (madre.getSimulation() == 7)) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    madre.tabEdit.editNext();
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    madre.tabEdit.cut();
                } else {
                    char ch = e.getKeyChar();
                    madre.tabEdit.addChar(ch);
                }
                madre.repaint();
            } else if (madre.getSimulation() == 9){
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    double val = Double.parseDouble(madre.V);
                    MatrixO m = madre.tab.getMatrix();
                    m.checkIfHigher(val);
                    madre.showTab(m);
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    madre.V = ZbysiuEditor.cut(madre.V);
                    madre.lblV.update("Wartość = " + madre.V);
                } else {
                    char ch = e.getKeyChar();
                    madre.V = ZbysiuEditor.addChar(madre.V,ch);
                    madre.lblV.update("Wartość = " + madre.V);
                }
                madre.repaint();
            }
            System.out.println("KlawiszuKlik");
        }
    }
}
