package Map;

/**
 * Created by HÜSEYİN on 11.12.2016.
 */

import java.awt.Color;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.awt.geom.AffineTransform;


public class Map extends JPanel implements ActionListener {
    private Timer timer;
    private Car car;
    private Obstacle obs;
    private final int DELAY = 10;

    public Map() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);

        car = new Car(50,50);

        obs=new Obstacle();

        timer = new Timer(DELAY, this);
        timer.start();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        if(car.isVisible()) {
            if(car.dy != 0)
                g2d.rotate(Math.atan((double)car.dx/car.dy),(double) (car.getX()+car.getImage().getWidth(null)+100)%600,
                        (double) 300);

            g2d.drawImage(car.getImage(), car.getX(), car.getY(), this);
            System.out.println(car.getX());
            System.out.println(car.getY());

            if(car.dy != 0)
                g2d.rotate(-Math.atan((double)car.dx/car.dy));

        }

        if (obs.isVisible() )
            g2d.drawImage(obs.getImage(), obs.getX(), obs.getY(), this);

        ArrayList<Cannonball> ms = car.cnn;


        for (Cannonball m : ms) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }


    }


    public void actionPerformed(ActionEvent e) {

        updateCar();
        updateCannonball();
        checkCollisions();

        repaint();
    }

    public void checkCollisions() {

        Rectangle r3 = car.getBounds();
        Rectangle r2 = obs.getBounds();

        if (r3.intersects(r2) && obs.isVisible()) {
            car.setVisible(false);
            obs.setVisible(false);
        }

        ArrayList<Cannonball> ms = car.cnn;

        for (Cannonball m : ms) {

            Rectangle r1 = m.getBounds();

            if (r1.intersects(r2) && obs.isVisible()) {
                m.setVisible(false);
                obs.setVisible(false);
            }
        }
    }

    private void updateCar() {
        if(car.isVisible())
            car.move();
    }

    private void updateCannonball() {

        ArrayList<Cannonball> ms = car.cnn;

        for (int i = 0; i < ms.size(); i++) {

            Cannonball m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }




    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            car.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            car.keyPressed(e);
        }
    }

}
