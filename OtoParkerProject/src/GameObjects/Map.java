package GameObjects;

import GameManagement.Player;
import ViewManagement.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Map extends OtoParkerMenu implements ActionListener {
    private Timer tick;
    private Timer countdown;
    private ArrayList<Obstacle> obstacles;
    private Target target;
    private Graphics2D background;
    private Graphics2D carg;
    private Graphics2D backup;
    private BufferedImage backImage1;
    private BufferedImage backImage2;
    private BufferedImage backImage3;
    private Car car;
    private JLabel elapsedTimeLabel;
    private Image star;

    private int elapsedTime;
    private int currentLevel;
    private int levelTime;
    private boolean isPaused = false;
    private boolean isCrashed = false;
    private boolean isParked = false;

    private LocalDataManager localDataManager;

    private PauseMenu pauseMenu;
    private LevelCompletetionMenu levelCompletionMenu;

    private Player player;

    public Map(MenuManager mgr, LocalDataManager localDataManager, int currentLevel, Player player) {

        super(mgr);

        BorderLayout panelMapLayout = new BorderLayout();
        setLayout(panelMapLayout);

        this.car = new Car(player.getCurrentCarTurningRadius());

        this.levelTime = localDataManager.getLevelTime(currentLevel);
        elapsedTime = 0;
        elapsedTimeLabel = new JLabel();
        elapsedTimeLabel.setBounds(10, -10, 150, 30);
        add(elapsedTimeLabel, BorderLayout.PAGE_END);

        this.star = Toolkit.getDefaultToolkit().getImage("images/star.png");
        this.localDataManager = localDataManager;
        this.currentLevel = currentLevel;

        this.pauseMenu = new PauseMenu(mgr, this.currentLevel);
        this.levelCompletionMenu = new LevelCompletetionMenu(mgr, this.currentLevel);
        this.levelCompletionMenu.setLevel(currentLevel);
        this.player = player;

        initBoard();
    }

    private void initBoard() {
        obstacles = localDataManager.getObstacles(currentLevel);
        target = localDataManager.getTarget(currentLevel);

        try {
            backImage1 = ImageIO.read(new File("images/asphalt_lane.jpg"));
        } catch (Exception ex) { }

        backImage2 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        backImage3 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);

        background = (Graphics2D) backImage1.getGraphics();
        carg = (Graphics2D) backImage2.getGraphics();
        backup = (Graphics2D) backImage3.getGraphics();

        background.drawImage(backImage1, 0, 0, getWidth(), getHeight(), null); //draw the asphalt texture

        //draw the parking area
        int alpha = 127; // 50% transparent
        background.setColor(new Color(123, 233, 130, alpha));
        background.fillRect(target.getX(), target.getY(), target.getWidth(), target.getHeight());

        background.translate(400, 300);
        background.scale(1, -1);

        carg.setColor(Color.WHITE);
        carg.fillRect(0, 0, 800, 600);
        carg.translate(400, 300);
        carg.scale(1, -1);

        EventQueue.invokeLater(() -> {
            grabFocus();
            requestFocus();
        });
        tick = new Timer();
        tick.schedule(new MainLoop(), 0, 30);

        countdown = new Timer();
        countdown.schedule(new CountDownLoop(), 0, 1000);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        carg.setBackground(new Color(255, 255, 255, 0));
        carg.clearRect(-800 / 2, -600 / 2, 800, 600);
        AffineTransform at = background.getTransform();
        AffineTransform at2 = carg.getTransform();

        for (Obstacle o : obstacles)
            if (o.getVisibility()) {
                if (car.checkCollision(o.getBorders()) || car.checkFrame()) {
                    //SoundManager.playSound(SoundManager.FAIL);
                    isCrashed = true;
                }
            }

        if (!car.checkFrame()) {
            car.draw(carg, background);
        }

        background.setTransform(at);
        carg.setTransform(at2);

        backup.drawImage(backImage1, 0, 0, null);
        backup.drawImage(backImage2, 0, 0, null);
        g2d.drawImage(backImage3, 0, 0, null);

        //draw the obstacles
        for (Obstacle o : obstacles)
            if (o.getVisibility())
                g2d.drawImage(o.getImage(), o.getX(), o.getY(), this);

        ArrayList<Cannonball> cs = car.getWeapons();
        for (Cannonball c : cs) {
            if (c.getVisibility()) {
                g2d.drawImage(c.getImage(), c.getX(), c.getY(), this);
            }
        }

        if (car.checkParking(target.getBorders()) && (!isParked && !isCrashed && !isPaused)) {
            SoundManager.playSound(SoundManager.SUCCESS);
            isParked = true;

            int earnedStars = (levelTime - elapsedTime + (levelTime / 3)) / (levelTime / 3);

            player.addNumberOfStars(earnedStars);
            localDataManager.saveLevelStats(currentLevel - 1, earnedStars);
            localDataManager.savePlayerStats(player);
        }

        if (isParked) {
            int alpha = 127; // 50% transparent
            g2d.setColor(new Color(0, 0, 0, alpha));
            g2d.fillRect(0, 0, 800, 600);
            levelCompletionMenu.setSuccessful(true);
            add(levelCompletionMenu, BorderLayout.CENTER);
            tick.cancel();
            tick.purge();
        }

        if (isCrashed) {
            int alpha = 127; // 50% transparent
            g2d.setColor(new Color(0, 0, 0, alpha));
            g2d.fillRect(0, 0, 800, 600);
            levelCompletionMenu.setSuccessful(false);
            add(levelCompletionMenu, BorderLayout.CENTER);
            tick.cancel();
            tick.purge();
        }

        if (isPaused) {
            int alpha = 127; // 50% transparent
            g2d.setColor(new Color(0, 0, 0, alpha));
            g2d.fillRect(0, 0, 800, 600);
            tick.cancel();
            tick.purge();
        }

        //the box for elapsed time and remaining stars
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 550, 800, 50);
        g2d.drawLine(0, 550, 800, 550);

        if ((levelTime - elapsedTime + (levelTime / 3)) / (levelTime / 3) >= 3)
            g2d.drawImage(star, 710, 560, 10, 10, this);
        if ((levelTime - elapsedTime + (levelTime / 3)) / (levelTime / 3) >= 2)
            g2d.drawImage(star, 730, 560, 10, 10, this);
        if ((levelTime - elapsedTime + (levelTime / 3)) / (levelTime / 3) >= 1)
            g2d.drawImage(star, 750, 560, 10, 10, this);
    }

    public void checkExplosion() {
        ArrayList<Cannonball> cs = car.getWeapons();

        for (Cannonball c : cs) {

            Rectangle r1 = c.getBorders();

            for (int i = 0; i < obstacles.size(); i++) {
                Rectangle2D r2 = obstacles.get(i).getBorders();
                if (r1.intersects(r2) && obstacles.get(i).getVisibility()) {
                    c.setVisibility(false);
                    obstacles.get(i).setVisiblity(false);
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
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (isPaused) {
                isPaused = !isPaused;
                tick = new Timer();
                tick.schedule(new MainLoop(), 0, car.getPeriod());
                remove(pauseMenu);
            } else {
                isPaused = !isPaused;
                add(pauseMenu, BorderLayout.CENTER);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            car.fire();

        } else if (e.getKeyCode() == KeyEvent.VK_N) {
            tick.schedule(new MainLoop(), 0, 30);
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
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
            elapsedTimeLabel.setText("Time Elapsed: " + Integer.toString(levelTime - elapsedTime));

            if (elapsedTime == levelTime) {
                countdown.cancel();
                countdown.purge();
                elapsedTimeLabel.setText(" JUST DO IT! :)");
            }

            repaint();
        }
    }
}