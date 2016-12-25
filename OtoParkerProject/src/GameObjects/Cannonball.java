package GameObjects;

/**
 * Created by HÜSEYİN on 11.12.2016.
 */
public class Cannonball extends Sprite {

    private final int BOARD_WIDTH = 800;
    double angle;

    public Cannonball(int x, int y,double an) {
        super(x, y);
        angle=an;

        initCannonball();
    }

    private void initCannonball() {

        loadImage(System.getProperty("os.name").contains("Mac") ? "shot.gif" : "shot.gif");
        getImageDimensions();
    }

    public void move() {

        x += 5 * Math.sin(angle);
        y += 5 * -Math.cos(angle);

        if (x > BOARD_WIDTH)
            vis = false;
    }


}
