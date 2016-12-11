package Map;

/**
 * Created by HÜSEYİN on 11.12.2016.
 */
public class Cannonball extends Sprite {

    private final int BOARD_WIDTH = 800;

    public Cannonball(int x, int y) {
        super(x, y);

        initCannonball();
    }

    private void initCannonball() {

        loadImage("shot.gif");
        getImageDimensions();
    }

    public void move() {

        x += 5;

        if (x > BOARD_WIDTH)
            vis = false;
    }


}
