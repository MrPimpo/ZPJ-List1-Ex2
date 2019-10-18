import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ZbysiuPanel extends JPanel implements MouseListener{
    private ZbysiuForm padre;
    private byte simulation;
    // ----- SIM 0 ----- //
    private ZbysiuLabel lblQuestion;
    private ZbysiuButton btnClassic, btnCustom, btnMostCustom;
    // ----- SIM 1 ----- //
    private ZbysiuTab tab;
    private ZbysiuButton btnReturno;
    // ----- SIM 2 ----- //
    private ZbysiuLabel lblWidth, lblHeight;
    private ZbysiuButton btnWidthIncrease, btnWidthDecrease, btnHeightIncrease, btnHeightDecrease;
    private ZbysiuButton btnOK;
    private int width, height;
    // ----- SIM 3 ----- //
    private ZbysiuEditTab tabEdit;
    private ZbysiuButton btnReady;
    private ZbysiuLabel lblCorrect;
    private String cellEdition;

    public ZbysiuPanel(ZbysiuForm papa){
        padre = papa;
        simulation = 0;

        // ----- SIM 0 ----- //
        padre.setSize(520,150);
        padre.setTitle("Wybierz macierz");
        lblQuestion = new ZbysiuLabel(25, 20,"Wybierz macierz:");
        btnClassic = new ZbysiuButton(25,40,125,40,"Macierz losowa");
        btnCustom = new ZbysiuButton(175,40,125,40,"Inne wymiary");
        btnMostCustom = new ZbysiuButton(325,40,150,40, "Sam se wpisuj");
        // ----- SIM 1 ----- //
        tab = new ZbysiuTab(140,25);
        // ----- SIM 2 ----- //
        btnWidthDecrease = new ZbysiuButton(10,10,20,20,"<");
        lblWidth = new ZbysiuLabel(40,10,"kolumny: 10");
        btnWidthIncrease = new ZbysiuButton(150,10,20,20,">");
        btnHeightDecrease = new ZbysiuButton(10,30,20,20,"<");
        lblHeight = new ZbysiuLabel(40,30,"wiersze: 10");
        btnHeightIncrease = new ZbysiuButton(150,30,20,20,">");
        btnOK = new ZbysiuButton(10,60,160,30,"OK");
        // ----- SIM 3 ----- //
        tabEdit = new ZbysiuEditTab(50,30);
        lblCorrect = new ZbysiuLabel(10,10,"");

        setFocusable(true);
        addMouseListener(this);
        addKeyListener(new KListener());
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public byte getSimulation() {
        return simulation;
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
                break;
            case 2: case 4:
                btnWidthDecrease.draw(g);
                btnWidthIncrease.draw(g);
                btnHeightDecrease.draw(g);
                btnHeightIncrease.draw(g);
                lblWidth.draw(g);
                lblHeight.draw(g);
                btnOK.draw(g);
                break;
            case 3:
                tabEdit.draw(g);
                btnReady.draw(g);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = new Point(e.getX(),e.getY());
        System.out.print("Sym: "+simulation+". KlikuKllik. ");
        switch(simulation){
            case 0:
                if (btnClassic.intercepts(p)){
                    simulation = 1;
                    MatrixO m = new MatrixO();
                    width = m.getM();
                    height = m.getN();
                    tab.setMatrix(m);
                    int W = width*tab.getCellWidth()+28, H = height*tab.getCellHeight()+85;
                    padre.setSize(W,H);
                    padre.setTitle("Macierz "+width+"x"+height);
                    btnReturno = new ZbysiuButton(W/2-30,H-75,60,30,"Wracaj");
                    this.repaint();
                    System.out.print("Klik w losowy ");
                }
                if (btnCustom.intercepts(p)){
                    simulation = 2;
                    width = 10;
                    height = 10;
                    lblWidth.update("kolumny: 10");
                    lblHeight.update("wiersze: 10");
                    padre.setSize(200,150);
                    this.repaint();
                    System.out.print("Klik w inny ");
                }
                if (btnMostCustom.intercepts(p)){
                    simulation = 4;
                    width = 10;
                    height = 10;
                    lblWidth.update("kolumny: 10");
                    lblHeight.update("wiersze: 10");
                    padre.setSize(200,150);
                    this.repaint();
                    System.out.print("Klik w najinniejszy ");
                }
                break;
            case 1:
                if (btnReturno.intercepts(p)){
                    simulation = 0;
                    padre.setSize(520,150);
                    padre.setTitle("Wybierz macierz");
                    this.repaint();
                    System.out.print("Klik w powrotny ");
                }
                break;
            case 2: case 4:
                if (btnWidthIncrease.intercepts(p)){
                    width++;
                    lblWidth.update("kolumny: "+width);
                    this.repaint();
                }
                if (btnWidthDecrease.intercepts(p)){
                    width--;
                    if (width<1)
                        width=1;
                    lblWidth.update("kolumny: "+width);
                    this.repaint();
                }
                if (btnHeightIncrease.intercepts(p)){
                    height++;
                    lblHeight.update("wiersze: "+height);
                    this.repaint();
                }
                if (btnHeightDecrease.intercepts(p)){
                    height--;
                    if (height<1)
                        height=1;
                    lblHeight.update("wiersze: "+height);
                    this.repaint();
                }
                if (btnOK.intercepts(p)){
                    if (simulation==2) {
                        simulation = 1;
                        tab.setMatrix(new MatrixO(height, width));
                        int W = width * tab.getCellWidth() + 35, H = height * tab.getCellHeight() + 85;
                        padre.setSize(W, H);
                        padre.setTitle("Macierz " + width + "x" + height);
                        btnReturno = new ZbysiuButton(W / 2 - 30, H - 75, 60, 30, "OK");
                        this.repaint();
                        System.out.print("Klik w OK ");
                    }else if (simulation==4){
                        simulation = 3;
                        tabEdit.setMatrix(new MatrixO(height, width,""));
                        cellEdition = "";
                        int W = width * tabEdit.getCellWidth() + 35, H = height * tabEdit.getCellHeight() + 85;
                        padre.setSize(W, H);
                        padre.setTitle("Macierz " + width + "x" + height);
                        btnReady = new ZbysiuButton(W / 2 - 30, H - 75, 60, 30, "Zrobione");
                        this.repaint();
                        System.out.print("Klik w OK ");
                    }
                }
                break;
            case 3:
                if (btnReady.intercepts(p)){
                    simulation = 1;
                    MatrixO m = tabEdit.getMatrix();
                    width = m.getM();
                    height = m.getN();
                    tab.setMatrix(m);
                    int W = width * tab.getCellWidth() + 35, H = height * tab.getCellHeight() + 85;
                    padre.setSize(W, H);
                    padre.setTitle("Macierz " + width + "x" + height);
                    btnReturno = new ZbysiuButton(W/2-30,H-75,60,30,"Wracaj");
                    this.repaint();
                }
                break;
        }
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

    private static class KListener extends KeyAdapter {
        ZbysiuPanel madre;

        public void setMama(ZbysiuPanel mama) { madre = mama; }

        @Override
        public void keyPressed(KeyEvent e) {
            if (madre.getSimulation() == 3) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    madre.cellEdition = madre.tabEdit.editNext(madre.cellEdition);
                } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    madre.cellEdition = madre.cellEdition.substring(0, madre.cellEdition.length() - 1);
                } else {
                    char ch = e.getKeyChar();
                    madre.tabEdit.addChar(ch);
                }
                madre.repaint();
            }
            System.out.println("KlawiszuKlik");
        }
    }
}
