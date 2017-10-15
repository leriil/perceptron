package com.snt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Panel extends JPanel {
    private final int DEFAULT_WIDTH = 500;
    private final int DEFAULT_HEIGHT = 500;
    private final Color BACK_COLOR = Color.WHITE;


    private Graphics2D g;
    private Point pointStart;
    private Point pointEnd;
    private BufferedImage bi;


public static int [][]pixs = new int[100][2500];

    public Panel() {
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        MyMouseHandler handler = new MyMouseHandler();

        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);

        bi = new BufferedImage(DEFAULT_WIDTH, DEFAULT_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) bi.getGraphics();
        g.setColor(BACK_COLOR);
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bi, 0, 0, null);
    }

    private void setUpDrawingGraphics() {
        g = (Graphics2D) bi.getGraphics();
    }



    private class MyMouseHandler extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            pointStart = e.getPoint();
            setUpDrawingGraphics();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            pointStart = null;
        }

        public void mouseDragged(MouseEvent e) {
            pointEnd = e.getPoint();
            g.setStroke(new BasicStroke(5));
            g.setColor(Color.green);
            g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
            repaint();
            pointStart = e.getPoint();
        }

    }

    public void clear(){
        g.setColor(BACK_COLOR);
g.fillRect(0,0,bi.getWidth(),bi.getHeight());
repaint();
    }

    public void save() {
        try {
            ImageIO.write(bi, "PNG",
                    new File("c://Users//Leriil//IdeaProjects//stars_moon_lab1//imagetest.png"));//C:\Users\Leriil\IdeaProjects
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void breakPictureIntoArrays(){
        BufferedImage workingImage = null;

        try {
            workingImage = ImageIO.read(new File("c://Users//Leriil//IdeaProjects//stars_moon_lab1//imagetest.png"));
int k=0;
            for (int i = 0; i <workingImage.getHeight() ; i+=50) {
                for (int j = 0 ; j <workingImage.getWidth() ;j+=50) {
                    pixs[k] = handlepixels(workingImage, i, j, 50,  50);
                    k++;
                }

            }

        } catch (IOException e) {
        }
    }

    public int [] handlepixels(Image img, int x, int y, int w, int h) {
        int[] pixels = new int[w * h];
        PixelGrabber pg = new PixelGrabber(img, x, y, w, h, pixels, 0, w);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
            System.err.println("interrupted waiting for pixels!");
        }
        return pixels;
    }


}
