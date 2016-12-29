package GameObjects;

import javax.swing.*;


public class Cannonball extends Sprite {

    private final int BOARD_WIDTH = 800;
    private double angle;
    private String im;

    public Cannonball(int x, int y, double an, String weaponImageDir) {
        super(x, y);
        angle = an;
        this.im = weaponImageDir;
        initCannonball();
    }

    private void initCannonball() {
        loadImage(im);
        getImageDimensions();
    }

    public void move() {

        x += 10 * Math.sin(angle);
        y += 10 * -Math.cos(angle);

        if (x > BOARD_WIDTH)
            vis = false;
    }

    public void loadImage(String imageName) {

        ImageIcon icon = new ImageIcon(imageName);
        image = icon.getImage();
    }

}
