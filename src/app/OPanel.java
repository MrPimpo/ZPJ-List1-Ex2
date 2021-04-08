package app;

import main.MatrixO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OPanel extends JPanel implements MouseListener{
    private final OForm padre;
    private byte simulation;
    // ----- SIM 0 ----- // ----- Main Menu ----- //
    private final OLabel lblQuestion;
    private final OButton btnClassic, btnCustom, btnMostCustom;
    // ----- SIM 1 ----- // ----- "Final" tab ----- //
    private OTab tab;
    private OButton btnReturno, btnInverse, btnAdd, btnSubtract, btnMultiply, btnGet;
    private OLabel lblDet;
    // ----- SIM 2,4,8 ----- //  ----- Size choosing ----- //
    private final OLabel lblWidth, lblHeight;
    private final OButton btnWidthIncrease, btnWidthDecrease, btnHeightIncrease, btnHeightDecrease;
    private final OButton btnOK;
    private int width, height;
    // ----- SIM 3,5 ----- //  ----- Edit Tab ----- //
    private final OEditTab tabEdit;
    private OButton btnReady;
    // ----- SIM 9 ----- //  ----- Getting V ----- //
    private String V;
    private final OLabel lblV;
    private final OButton btnFine;

    OPanel(OForm papa){
        padre = papa;
        simulation = 0;

        // ----- SIM 0 ----- // ----- Main Menu ----- //
        padre.setSize(260,250);
        padre.setTitle("Choose matrix");
        lblQuestion = new OLabel(15, 15,"What matrix are you interested in?");
        btnClassic = new OButton(15,45,210,40,"Random size, random content");
        btnCustom = new OButton(15,95,210,40,"NxM, random content");
        btnMostCustom = new OButton(15,145,210,40, "Custom");
        // ----- SIM 1 ----- // ----- Final table ----- //
        tab = new OTab(50,25);
        // ----- SIM 2,4 ----- // ----- Size settings ----- //
        int leftMargin=60;
        btnWidthDecrease = new OButton(leftMargin,10,20,20,"<");
        lblWidth = new OLabel(leftMargin+30,10,"cols: ");
        btnWidthIncrease = new OButton(leftMargin+140,10,20,20,">");
        btnHeightDecrease = new OButton(leftMargin,30,20,20,"<");
        lblHeight = new OLabel(leftMargin+30,30,"rows: ");
        btnHeightIncrease = new OButton(leftMargin+140,30,20,20,">");
        btnOK = new OButton(leftMargin,60,160,30,"OK");
        // ----- SIM 3 ----- // ----- Fil in table ----- //
        tabEdit = new OEditTab(50,30);
        // ----- SIM 9 ----- //
        V="";
        btnFine = new OButton(10,60,160,30,"OK");
        lblV = new OLabel(10,10,"Value = ");

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
        tab = new OTab(50,25);
        width = matrixO.getM();
        height = matrixO.getN();
        tab.setMatrix(matrixO);
        int W = width * tab.getCellWidth() + 188, H = Math.max(285,height * tab.getCellHeight() + 50);
        padre.setSize(W, H);
        padre.setTitle("Matrix " + width + "x" + height);
        btnReturno = new OButton(10, H - 75, 150, 30, "Back");
        btnInverse = new OButton(10, 35, 150, 30, "Invert");
        btnAdd = new OButton(10, 70, 150, 30, "Add");
        btnSubtract = new OButton(10, 105, 150, 30, "Subtract");
        btnMultiply = new OButton(10, 140, 150, 30, "Multiply");
        btnGet = new OButton(10, 175, 150, 30, "Print values larger than...");
        lblDet = new OLabel(10, 10,"No determinant");
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
        padre.setTitle("matrix " + width + "x" + height);
        btnReady = new OButton(10, H - 75, 90, 30, "Done");
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = new Point(e.getX(),e.getY());
        System.out.print("Sym: "+simulation+". MouseClick -> ");
        switch(simulation){
            case 0: { // ----- Main Menu ----- //
                if (btnClassic.intercepts(p)) {
                    showTab(new MatrixO());
                    System.out.print("Main-random(size&content) ");
                }
                if (btnCustom.intercepts(p)) {
                    simulation = 2;
                    width = 10;
                    height = 10;
                    lblWidth.update("cols: 10");
                    lblHeight.update("rows: 10");
                    padre.setSize(300, 150);
                    padre.setTitle("Choose matrix size");
                    this.repaint();
                    System.out.print("Main-custom-size|random-content ");
                }
                if (btnMostCustom.intercepts(p)) {
                    simulation = 4;
                    width = 5;
                    height = 5;
                    lblWidth.update("cols: 5");
                    lblHeight.update("rows: 5");
                    padre.setSize(300, 150);
                    padre.setTitle("Choose matrix size");
                    this.repaint();
                    System.out.print("Main-custom ");
                }
            }break;
            case 1: { // ----- Final table ----- //
                if (btnReturno.intercepts(p)) {
                    simulation = 0;
                    padre.setSize(260, 250);
                    padre.setTitle("Choose matrix");
                    this.repaint();
                    System.out.print("TabView-back ");
                }
                // btnAdd, btnSubtract, btnMultiply, btnGet;
                if (btnInverse.intercepts(p)) {
                    showTab(tab.getMatrix().odd());
                    System.out.print("TabView-inverse ");
                }
                if (btnAdd.intercepts(p)) {
                    MatrixO m = tab.getMatrix();
                    editTab(5,m);
                    System.out.print("TabView-addition ");
                }
                if (btnSubtract.intercepts(p)) {
                    MatrixO m = tab.getMatrix();
                    editTab(6,m);
                    System.out.print("TabView-subtraction ");
                }
                if (btnMultiply.intercepts(p)) {
                    simulation = 8;
                    MatrixO m = tab.getMatrix();
                    height = m.getM();
                    width = m.getN(); // ten jest możliwy do zmiany
                    lblWidth.update("cols: "+width);
                    lblHeight.update("rows: "+height);
                    padre.setSize(300, 150);
                    this.repaint();
                    System.out.print("Other ");
                    //MatrixO m = tab.getMatrix();
                    //editTab(7,m);
                    //System.out.print("Klik w mnożonko ");
                }
                if (btnGet.intercepts(p)) {
                    simulation = 9;
                    lblV.update("Val ="+V);
                    padre.setSize(200, 150);
                    padre.setTitle("Values above "+V+": ");
                    this.repaint();
                    System.out.print("Something ");
                }
            }break;
            case 2: case 4: case 8: { // ----- Size settings ----- //
                if (btnWidthIncrease.intercepts(p)) {
                    width++;
                    lblWidth.update("cols: " + width);
                    this.repaint();
                }
                if (btnWidthDecrease.intercepts(p)) {
                    width--;
                    if (width < 1)
                        width = 1;
                    lblWidth.update("cols: " + width);
                    this.repaint();
                }
                if (simulation!=8) {
                    if (btnHeightIncrease.intercepts(p)) {
                        height++;
                        lblHeight.update("rows: " + height);
                        this.repaint();
                    }
                    if (btnHeightDecrease.intercepts(p)) {
                        height--;
                        if (height < 1)
                            height = 1;
                        lblHeight.update("rows: " + height);
                        this.repaint();
                    }
                }
                if (btnOK.intercepts(p)) {
                    switch (simulation) {
                        case 2:{
                            showTab(new MatrixO(height, width));
                            System.out.print("OK ");
                        }break;
                        case 4:{
                            editTab(3);
                            System.out.print("OK ");
                        }break;
                        case 8: {
                            editTab(7);
                            System.out.print("OK ");
                        }break;
                    }
                }
            }break;
            case 3: case 5: case 6: case 7: {
                if (tabEdit.intercepts(p)) {
                    tabEdit.select(p);
                    repaint();
                    System.out.print("Tab-click ");
                    //tab.setCellWidth(50);
                }
                switch (simulation) {
                    case 3: { // ----- Fil in table ----- //
                        if (btnReady.intercepts(p)) {
                            showTab(tabEdit.getMatrix());
                            //tab.setCellWidth(50);
                            System.out.print("Edit-fin ");
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
                            System.out.print("Addition-edit-fin ");
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
                            System.out.print("Subtraction-edin-fin ");
                        }
                    }
                    break;
                    case 7: { // ----- Fill in table for multiplying ----- //
                        if (btnReady.intercepts(p)) {
                            MatrixO m1 = tab.getMatrix();
                            MatrixO m2 = tabEdit.getMatrix();
                            MatrixO m3 = m1.multiply(m2);
                            showTab(m3);
                            //tab.setCellWidth(50);
                            System.out.print("Multiplication-edit-fin ");
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
        System.out.println(" ==> Sim: "+simulation);

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
        OPanel madre;
        KListener(OPanel mama){
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
                    madre.V = OEditor.cut(madre.V);
                    madre.lblV.update("Val = " + madre.V);
                } else {
                    char ch = e.getKeyChar();
                    madre.V = OEditor.addChar(madre.V,ch);
                    madre.lblV.update("Val = " + madre.V);
                }
                madre.repaint();
            }
            System.out.println("Button clicked: ");
        }
    }
}
