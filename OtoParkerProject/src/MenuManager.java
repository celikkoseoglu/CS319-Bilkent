import javax.swing.*;

/**
 * Created by celikkoseoglu on 11/12/2016.
 */
public class MenuManager {

    private JFrame mainFrame;

    private Menu mainMenu;
    private Menu upgradeCar;

    public MenuManager(JFrame mainFrame) {

        this.mainFrame = mainFrame;

        mainMenu = new MainMenu(this);
        upgradeCar = new UpgradeCarMenu(this);

        showMainMenu();
    }

    public void showMainMenu() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(mainMenu);
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }

    public void showUpgradeCar() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(upgradeCar);
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }

    public void showCredits() {

    }

    public void showInstructions() {

    }

    public void showLevelScreen() {

    }


}
