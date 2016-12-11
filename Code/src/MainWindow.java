import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainWindow {

    public static void main(String args[]) throws Exception {
        JFrame myFrame = new JFrame("Sample Frame");
        myFrame.setSize(800,600);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Ball game = new Ball();
        myFrame.add(game);
        myFrame.setVisible(true);

        while (true) {
            game.moveBall();
            game.repaint();
            Thread.sleep(10);
        }
    }
}