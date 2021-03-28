package leastsquares;

import java.applet.Applet;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LS extends Applet implements   MouseMotionListener, MouseListener,ComponentListener, ChangeListener, ActionListener {
    private static final long serialVersionUID = 1L;
    private boolean isEditMode=true,isErrorVisible=true,isRotationEnabled=false,isBestModel=false;
    private double mx=0, my=0;
    private int N = 0;
    private double sumx  = 0.0;
    private double sumy  = 0.0;
    private double sumx2 = 0.0;
    private double sumy2 = 0.0;
    private double sumxy = 0.0;
    private double[] x = new double[10000];
    private double[] y = new double[10000];
    private double a, an, b, bn;
    private int width=700, height=530;
    private double scaleX=1, scaleY=1;
    private double rotX=0,rotY=0;
    private DecimalFormat df = new DecimalFormat("#.#");
    private DecimalFormat extf = new DecimalFormat("#.####");
    //Elementy interfejsu graficznego
    private JButton clearB;
    private JButton loadCSVB;
    private JCheckBox errorsB;
    private JCheckBox addRotationPoint;
    private JCheckBox editModeB;
    private JCheckBox findBestModel;
    private JSlider scaleSlider;
    private JLabel xyInfo;
    private Panel btnPanel;
    private JLabel sliderLabel;
    //Kolory u¿ytkownika
    private final Color axisColor=new Color(220,220,204);
    private final Color lsqColor=new Color(255,51,102);
    private final Color lsqRectColor=new Color(255,255,158);
    private final Color barLsqColor=new Color(255,51,102);
    private final Color appBgColor=new Color(204,204,153);
    private final Color btnBgColor=new Color(0,0,97);
    private final Color barGreenColor=new Color(0,204,0);

    public void init() {
// Przygotowanie sk³adowych pow³oki graficznej appletu do dzialania
        //Elementy interfejsu u¿ytkownika
        clearB = new JButton("Resetuj");
        clearB.setFont(new Font("TimesRoman", Font.PLAIN,10));
        clearB.setMargin(new Insets(1,1,1,1));
        clearB.addActionListener(this);
        clearB.setBackground(btnBgColor);
        clearB.setForeground(Color.white);

        loadCSVB = new JButton("<html>Import z CSV</html>");
        loadCSVB.setFont(new Font("TimesRoman", Font.PLAIN,10));
        loadCSVB.setMargin(new Insets(1,1,1,1));
        loadCSVB.addActionListener(this);
        loadCSVB.setBackground(btnBgColor);
        loadCSVB.setForeground(Color.white);

        errorsB=new JCheckBox("<html>Poka¿<br> b³êdy</html>");
        errorsB.setSelected(true);
        errorsB.setFont(new Font("TimesRoman", Font.PLAIN,10));
        errorsB.addActionListener(this);
        errorsB.setBackground(appBgColor);
        errorsB.setForeground(Color.black);

        findBestModel=new JCheckBox("<html>Najlepszy<br> model</html>");
        findBestModel.setSelected(false);
        findBestModel.setFont(new Font("TimesRoman", Font.PLAIN,10));
        findBestModel.addActionListener(this);
        findBestModel.setBackground(appBgColor);
        findBestModel.setForeground(Color.black);

        addRotationPoint=new JCheckBox("<html>Ustaw punkt<br>obrotu</html>");
        addRotationPoint.setFont(new Font("TimesRoman", Font.PLAIN,10));
        addRotationPoint.addActionListener(this);
        addRotationPoint.setBackground(appBgColor);
        addRotationPoint.setForeground(Color.black);

        editModeB = new JCheckBox("<html>Dodaj<br>punkty</html>");
        editModeB.setSelected(true);
        editModeB.setFont(new Font("TimesRoman", Font.PLAIN,10));
        editModeB.addActionListener(this);
        editModeB.setBackground(appBgColor);
        editModeB.setForeground(Color.black);

        sliderLabel = new JLabel("Powiêkszenie", JLabel.CENTER);
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sliderLabel.setFont(new Font("TimesRoman", Font.PLAIN,10));

        scaleSlider=new JSlider();
        scaleSlider.setPreferredSize(new Dimension(90,40));
        scaleSlider.addChangeListener(this);
        scaleSlider.setMajorTickSpacing(20);
        scaleSlider.setMinorTickSpacing(1);
        scaleSlider.setPaintLabels(true);
        scaleSlider.setValue(scaleSlider.getMaximum());
        scaleSlider.setMaximum(100);
        scaleSlider.setMinimum(0);
        scaleSlider.setBackground(appBgColor);
        scaleSlider.setForeground(Color.black);
        scaleSlider.setFont(new Font("TimesRoman", Font.PLAIN,10));

        xyInfo = new JLabel("");
        xyInfo.setPreferredSize(new Dimension(95, 40));
        xyInfo.setFont(new Font("TimesRoman", Font.PLAIN,10));

        btnPanel = new Panel();
        btnPanel.setBackground(Color.blue);
        btnPanel.add(clearB);
        btnPanel.add(loadCSVB);
        btnPanel.add(editModeB);
        btnPanel.add(errorsB);
        btnPanel.add(addRotationPoint);
        btnPanel.add(findBestModel);
        btnPanel.add(sliderLabel);
        btnPanel.add(scaleSlider);
        btnPanel.add(xyInfo);
        btnPanel.setBackground(appBgColor);
//Inicjalizacja appletu
        setLayout(new BorderLayout());
        add(btnPanel, BorderLayout.SOUTH);
        this.addComponentListener(this);
        this.setBackground(appBgColor);
        addMouseMotionListener(this);
        addMouseListener(this);
        setSize((int)width, (int)height);
        setVisible(true);
//odczyt parametru z kodu HTML
        String xValues = getParameter("XVAL");
        String yValues = getParameter("YVAL");

        if (xValues!=null){
            String[] xD = xValues.split(" ");
            String[] yD = yValues.split(" ");
            if(xD.length==yD.length){
                for(N=0;N<xD.length;N++){
                    x[N]=Double.parseDouble((xD[N]));
                    y[N]=Double.parseDouble((yD[N]));
                    sumx  += x[N];
                    sumy  += y[N];
                    sumx2 += x[N]*x[N];
                    sumy2 += y[N]*y[N];
                    sumxy += x[N]*y[N];
                    an = (N*sumxy - sumx*sumy) / (N*sumx2 - sumx*sumx);
                    bn = (sumy - an*sumx) / N;
                }}
            repaint();
        }

    }
    //Obs³uga zdarzeñ
    public void actionPerformed(ActionEvent e) {

    }
    //Resetuj model
    public void reset(){
        for(int i=0;i<x.length;i++) {
            x[i]=0; y[i]=0;
        }
        N=0; sumx=0; sumy=0; sumx2=0; sumy2=0; sumxy=0;
        rotX=0;rotY=0;
        repaint();
    }
    //Dodanie punktów za pomoc¹ klikniêæ
    public void mousePressed(MouseEvent e) {
        width=getWidth();
        height=getHeight();
        if(isRotationEnabled) {
            rotX = (e.getX()/scaleX)-(width/2)/scaleX;
            rotY = (-e.getY()/scaleY)+(height/2)/scaleY;
            addRotationPoint.setSelected(false);
            isRotationEnabled=false;
            mx=0;my=0;
            repaint();
            return;
        }
        if(!isEditMode) {return;}
        x[N] = (e.getX())/scaleX-(width/2)/scaleX;
        y[N] = (-e.getY())/scaleY+(height/2)/scaleY;
        sumx  += x[N];
        sumy  += y[N];
        sumx2 += x[N]*x[N];
        sumy2 += y[N]*y[N];
        sumxy += x[N]*y[N];
        N++;
        an = (N*sumxy - sumx*sumy) / (N*sumx2 - sumx*sumx);
        bn = (sumy - an*sumx) / N;
        repaint();
    }
    //podwójne bufforowanie aby zapobiec migotaniu obrazu
    public void update(Graphics g) {
        Graphics doublebuff;
        Image offscreen = null;
        Dimension d = getSize();
        offscreen = createImage(d.width, d.height);
        doublebuff = offscreen.getGraphics();
        doublebuff.setColor(getBackground());
        doublebuff.fillRect(0, 0, d.width, d.height);
        doublebuff.setColor(getForeground());
        paint(doublebuff);
        g.drawImage(offscreen, 0, 0, this);
    }
    //g³owna funkcja rysuj¹ca grafikê
    public void paint(Graphics g) {
        //konfiguracja obszaru rysowania
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("TimesRoman", Font.PLAIN,10));
        g2d.translate(width/2,height/2);
        g2d.scale(1, -1);

        //rysowanie uk³adu wspó³rzêdnych
        g2d.setColor(axisColor);
        g2d.drawLine(-width, 0, width, 0);
        g2d.drawLine(0, -height, 0, height);
        for(int m=-width;m<width/2;m++)
        {
            g2d.drawLine(m*10, -2, m*10, 2);
        }
        g2d.scale(1, -1);
        g2d.setColor(Color.black);
        g2d.drawString("y",10, -height/2+10);
        g2d.drawString("x",width/2-10, 10);
        for(int m=-width/2;m<width/2;m=m+50)
        {
            g2d.drawString(String.valueOf(df.format(m/scaleX)), m-7, 10);
        }
        for(int m=-height/2;m<height/2;m=m+50)
        {
            g2d.drawString(String.valueOf(df.format(-m/scaleY)), 5, m);
        }
        g2d.scale(1, -1);
        g2d.setColor(axisColor);
        for(int m=-height;m<height/2;m++)
        {
            g2d.drawLine(-2,m*10,2, m*10);
        }
        g2d.drawLine(-5, height/2-5, 0, height/2);
        g2d.drawLine(5, height/2-5, 0, height/2);
        g2d.drawLine(width/2, 0, width/2-5,5 );
        g2d.drawLine(width/2, 0, width/2-5,-5 );
        g2d.drawLine(0, -height, 0, height);
        if(!isBestModel){
            g2d.setColor(Color.blue);
            g2d.fillOval((int)(rotX*scaleX)-3, (int)(rotY*scaleY)-3, 6, 6);
        }
        if(N>1) {
            // rysowanie prostej regresji liniowej oszacowanej mnk
            g2d.setColor(lsqColor);
            g2d.drawLine((int)(-width), (int)(scaleY*(an*(-width/scaleX)+bn)), (int)(width), (int)(scaleY*(an*(width/scaleX) + bn)));
            g2d.setColor(Color.blue);
            //wyznaczenie wpolczynnikow prostej u¿ytkownika
            a=(my-rotY)/(mx-rotX);
            b=-a*rotX+rotY;
            if (isBestModel){
                a=an;
                b=bn;
                g2d.setColor(lsqColor);
            }
            g2d.drawLine((int)(-width), (int)((a*(-width/scaleY)+b)*scaleY), (int)(width), (int)((a*width/scaleY+b)*scaleY));	 	//	 y=(y2/x2)x

            // rysowanie punktów i kwadratów
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 7 * 0.1f));
        }
        double squares=0,squaresn=0;
        for (int i = 0; i < N; i++) {
            g2d.setColor(Color.blue);
            g2d.fillOval((int)(x[i]*scaleX), (int)(y[i]*scaleY), (int)5,(int)5);
            if(N>1)
            {
                squares+=(int)(Math.pow(Math.abs(((a*x[i] + b)-y[i])),2));
                squaresn+=(int)(Math.pow(Math.abs(((an*x[i] + bn)-y[i])),2));
                g2d.setColor(Color.black);
                if((int)y[i]<(int)(a*x[i] + b)) {
                    g2d.setColor(lsqRectColor);
                    if (isErrorVisible) g2d.fillRect((int)((x[i])*scaleX), (int)((y[i])*scaleX), (int)(scaleX*Math.abs(((a*(x[i]) + b)-y[i]))),(int)(scaleY*Math.abs(((a*(x[i]) + b)-y[i]))));
                    g2d.setColor(barLsqColor);
                    if (isErrorVisible) g2d.drawRect((int)((x[i])*scaleX), (int)((y[i])*scaleX), (int)(scaleX*Math.abs(((a*(x[i]) + b)-y[i]))),(int)(scaleY*Math.abs(((a*(x[i]) + b)-y[i]))));
                }
                else {
                    g2d.setColor(lsqRectColor);
                    if (isErrorVisible) g2d.fillRect((int)(x[i]*scaleX), (int)((a*x[i] + b)*scaleY), (int)(scaleX*Math.abs(((a*x[i] + b)-y[i]))),(int)(scaleY*Math.abs(((a*x[i] + b)-y[i]))));
                    g2d.setColor(barLsqColor);
                    if (isErrorVisible) g2d.drawRect((int)(x[i]*scaleX), (int)((a*x[i] + b)*scaleY), (int)(scaleX*Math.abs(((a*x[i] + b)-y[i]))),(int)(scaleY*Math.abs(((a*x[i] + b)-y[i]))));
                }
            }
        }
        //Wykres z bledami
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 10 * 0.1f));
        g2d.setColor(barLsqColor);
        g2d.fillRect((int)(-width/2), -height/2+65, 25,(int)(scaleY*Math.sqrt(squares)));
        g2d.scale(1, -1);
        g2d.setColor(Color.black);
        g2d.drawString(String.valueOf("B³¹d="+df.format(squares)), 1-width/2, -50+height/2);
        g2d.drawString("x="+df.format(mx)+" y="+df.format(my),-width/4,15-height/2);
        g2d.drawString("Model: y="+extf.format(a)+"*x+"+extf.format(b),width/4-70,15-height/2);
        g2d.scale(1, -1);
        g2d.setColor(barGreenColor);
        g2d.fillRect((int)(-width/2), (int)(scaleY*Math.sqrt(squares))-height/2+65, 25,height);
        g2d.setColor(Color.black);
        g2d.drawRect((int)(-width/2), -height/2+65, 25,height);
        g2d.drawRect((int)(-width/2), -height/2+65, 25,(int)(scaleY*Math.sqrt(squaresn)));
    }

    public void mouseDragged(MouseEvent e) {
        mx = (e.getX())/scaleX-(width/2)/scaleX;
        my= (-e.getY())/scaleY+(height/2)/scaleY;
        repaint();
    }

    public void mouseMoved(MouseEvent e) {
        mx = (e.getX())/scaleX-(width/2)/scaleX;
        my= (-e.getY())/scaleY+(height/2)/scaleY;
        repaint();
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {
    }
    //Aktualizacja modelu gdy zmieni sie rozmiar okna
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        double scale=(double)source.getValue()/100;
        scaleX=scale;
        scaleY=scale;
        repaint();
    }

    public void componentHidden(ComponentEvent arg0) {}
    public void componentMoved(ComponentEvent arg0) {}
    public void componentResized(ComponentEvent arg0) {
        width=getWidth();
        height=getHeight();
        repaint();
    }

    public void componentShown(ComponentEvent arg0) {
    }
    //Klasa wewnetrzna dla importu pliku CSV
    class CSVReader {
        public void readCsv(String dir, String csvFile) {
            BufferedReader br = null;
            String line = "";
            String splitBy = ",";
            try {
                br = new BufferedReader(new FileReader(dir+File.separator+csvFile));
                N=0;
                while ((line = br.readLine()) != null) {
                    String[] vals = line.split(splitBy);
                    x[N]=Double.parseDouble((vals[0]));
                    y[N]=Double.parseDouble((vals[1]));
                    sumx  += x[N];
                    sumy  += y[N];
                    sumx2 += x[N]*x[N];
                    sumy2 += y[N]*y[N];
                    sumxy += x[N]*y[N];
                    N++;
                    an = (N*sumxy - sumx*sumy) / (N*sumx2 - sumx*sumx);
                    bn = (sumy - an*sumx) / N;
                }
                repaint();
                isEditMode=false;
                editModeB.setSelected(false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}