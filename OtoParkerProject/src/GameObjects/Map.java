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
    public Timer timer;
    private ArrayList<Obstacle> Obstacles;
    private Target target;
    private final int DELAY = 10;
    private Graphics2D background;
    private Graphics2D carg;
    private Graphics2D backup;
    private BufferedImage backImage1;
    private BufferedImage backImage2;
    private BufferedImage backImage3;
    private Car car = new Car();

    private JLabel elapsedTimeLabel;
    private int elapsedTime;

    private Image star;


    public Map() {

        setLayout(null);

        elapsedTime = 0;
        elapsedTimeLabel = new JLabel();
        elapsedTimeLabel.setBounds(10, 550, 150, 30);
        add(elapsedTimeLabel);

        this.star = Toolkit.getDefaultToolkit().getImage(System.getProperty("os.name").contains("Mac") ? "star.png" : "star.png");

        initBoard();


    }

    private void initBoard() {

        setFocusable(true);

        Obstacles= new ArrayList<Obstacle>();
        Obstacles.add(new Obstacle());
        Obstacles.add(new Obstacle(600,400));

        target = new Target(600,100);

        backImage1 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB); //ImageIO.read(new File("car.jpg"));

        backImage2 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        backImage3 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);

        background = (Graphics2D) backImage1.getGraphics();
        carg = (Graphics2D) backImage2.getGraphics();
        backup = (Graphics2D) backImage3.getGraphics();

        background.drawImage(backImage1, 0, 0, getWidth(), getHeight(), null);
        background.setColor(new Color(123,233,130));
        background.fillRect(target.getX(),target.getY(),target.getWidth(),target.getHeight());

        background.translate(400, 300);
        background.scale(1, -1);

        carg.setColor(Color.WHITE);
        carg.fillRect(0, 0, 800, 600);
        carg.translate(400, 300);
        carg.scale(1, -1);

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                grabFocus();
                requestFocus();
            }
        });

        new Timer().schedule(new MainLoop(), 100, 30);
        new Timer().schedule(new CountDownLoop(), 0, 1000);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        backup.setColor(Color.WHITE);
        backup.fillRect(0, 0, 800, 600);

        carg.setBackground(new Color(255, 255, 255, 0));
        carg.clearRect(-800/2, -600/2, 800, 600);

        AffineTransform at = background.getTransform();
        AffineTransform at2 = carg.getTransform();

        boolean draw=true;
        for(int i=0; i < Obstacles.size(); i++)
            if(Obstacles.get(i).getVisibility()) {
                if (car.checkCollision(Obstacles.get(i).getBorders()))
                    draw = false;
            }

        if(draw) {
            car.draw(carg, background);
        }

        background.setTransform(at);
        carg.setTransform(at2);

        backup.drawImage(backImage1, 0, 0, null);
        backup.drawImage(backImage2, 0, 0, null);

        g2d.drawImage(backImage3, 0, 0, null);

        if(car.checkParking(target.getBorders()))
            System.exit(0);

        for(int i=0; i < Obstacles.size(); i++)
            if (Obstacles.get(i).getVisibility() )
                g2d.drawImage(Obstacles.get(i).getImage(), Obstacles.get(i).getX(), Obstacles.get(i).getY(), this);

        ArrayList<Cannonball> cs = car.getWeapons();
        for (Cannonball c : cs) {
            if (c.getVisibility()) {
                g2d.drawImage(c.getImage(), c.getX(), c.getY(), this);
            }
        }

        //the box for elapsed time and remaining stars

        g2d.drawLine(0, 550, 800, 550);

        g2d.drawImage(star, 710, 560, 10, 10, this);
        g2d.drawImage(star, 730, 560, 10, 10, this);
        g2d.drawImage(star, 750, 560, 10, 10, this);
    }

    public void checkExplosion(){
        ArrayList<Cannonball> cs = car.getWeapons();

        for (Cannonball c : cs) {

            Rectangle r1 = c.getBorders();

            for(int i=0; i < Obstacles.size(); i++) {
                Rectangle2D r2 = Obstacles.get(i).getBorders();
                if (r1.intersects(r2) && Obstacles.get(i).getVisibility()) {
                    c.setVisibility(false);
                    Obstacles.get(i).setVisiblity(false);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            InputManager.keydown[e.getKeyCode()] = true;
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            car.fire();
        }
        else if (e.getKeyCode()== KeyEvent.VK_N){
            new Timer().schedule(new MainLoop(), 100, 10);
        }
        else if (e.getKeyCode()== KeyEvent.VK_P){
            new Timer().schedule(new MainLoop(), 100, 30);
        }
        else if (e.getID() == KeyEvent.KEY_RELEASED) {
            InputManager.keydown[e.getKeyCode()] = false;
        }
    }

    private class MainLoop extends TimerTask {
        @Override
        public void run() {
            car.update();
            checkExplosion();
            repaint();
        }
    }

    private class CountDownLoop extends TimerTask {
        @Override
        public void run() {
            elapsedTime++;
            elapsedTimeLabel.setText("Time Elapsed: " + Integer.toString(elapsedTime));
            repaint();
        }
    }
}
