package GameObjects;

/**
 * Created by HÜSEYİN on 11.12.2016.
 */

import java.awt.Color;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class Map extends JPanel implements ActionListener {
    private Timer timer;
    private Obstacle obs;
    private Target target;
    private final int DELAY = 10;
    private Graphics2D g1;
    private Graphics2D g2;
    private Graphics2D g3;
    private BufferedImage back1;
    private BufferedImage back2;
    private BufferedImage back3;
    private Vehicle vehicle = new Vehicle();
    public Map() {

        initBoard();
    }

    private void initBoard() {

        setFocusable(true);

        obs=new Obstacle();
        target = new Target(600,100);

        //try {
        back1 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB); //ImageIO.read(new File("car.jpg"));
        //} catch (IOException ex) {
        //    Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        //}
        back2 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        back3 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);

        g1 = (Graphics2D) back1.getGraphics();
        g2 = (Graphics2D) back2.getGraphics();
        g3 = (Graphics2D) back3.getGraphics();

        g1.drawImage(back1, 0, 0, getWidth(), getHeight(), null);
        g1.setColor(new Color(123,233,130));
        g1.fillRect(target.getX(),target.getY(),target.getWidth(),target.getHeight());

        //g1.setColor(Color.WHITE);
        //g1.fillRect(0, 0, getWidth(), getHeight());
        g1.translate(400, 300);
        g1.scale(1, -1);

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 800, 600);
        g2.translate(400, 300);
        g2.scale(1, -1);


        new Timer().schedule(new MainLoop(), 100, 30);

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g3.setColor(Color.WHITE);
        g3.fillRect(0, 0, 800, 600);

        g2.setBackground(new Color(255, 255, 255, 0));
        g2.clearRect(-800/2, -600/2, 800, 600);

        AffineTransform at = g1.getTransform();
        AffineTransform at2 = g2.getTransform();
        if(obs.isVisible()){
            if(!vehicle.checkCollision(obs.getBounds()))
                vehicle.draw(g2, g1);
        }
        else
            vehicle.draw(g2, g1);
        vehicle.position.print();
        g1.setTransform(at);
        g2.setTransform(at2);

        g3.drawImage(back1, 0, 0, null);
        g3.drawImage(back2, 0, 0, null);
        //g2d.drawImage(back1, 0, 0, null);

        g2d.drawImage(back3, 0, 0, null);

        if(vehicle.checkParking(target.getBounds()))
            System.exit(0);


        if (obs.isVisible() )
            g2d.drawImage(obs.getImage(), obs.getX(), obs.getY(), this);



        ArrayList<Cannonball> cs = vehicle.getWeapons();
        for (Cannonball c : cs) {
            if (c.isVisible()) {
                g2d.drawImage(c.getImage(), c.getX(), c.getY(), this);
            }
        }


    }

    public void checkExplosion(){
        ArrayList<Cannonball> cs = vehicle.getWeapons();

        for (Cannonball c : cs) {

            Rectangle r1 = c.getBounds();
            Rectangle2D r2 = obs.getBounds();

            if (r1.intersects(r2) && obs.isVisible()) {
                c.setVisible(false);
                obs.setVisible(false);
            }

        }
    }


    public void actionPerformed(ActionEvent e) {
        repaint();
    }


    @Override
    protected void processKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            Keyboard.keydown[e.getKeyCode()] = true;
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            vehicle.fire();
        }
        else if (e.getID() == KeyEvent.KEY_RELEASED) {
            Keyboard.keydown[e.getKeyCode()] = false;
        }
    }

    private class MainLoop extends TimerTask {
        @Override
        public void run() {
            vehicle.update();
            checkExplosion();
            repaint();
        }
    }

}
