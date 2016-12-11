import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainWindow {

    public static void main(String args[]) throws Exception {
        JFrame myFrame = new JFrame("OtoParker");
        myFrame.setSize(800,600);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MenuManager menuManager = new MenuManager();
        myFrame.add(menuManager.showMainMenu());
        myFrame.setVisible(true);

        /*while (true) {
            menu.repaint();
            Thread.sleep(10);
        }*/
    }
}