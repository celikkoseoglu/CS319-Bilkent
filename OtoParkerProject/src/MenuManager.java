import javax.swing.*;
import java.util.Arrays;

public class MenuManager {

    private JFrame mainFrame;

    private Menu mainMenu;
    private Menu upgradeCar;
    private Menu credits;
    private Menu instructions;
    private Menu levels;

    private LocalDataManager mgr;

    public MenuManager(JFrame mainFrame) {

        mgr = new LocalDataManager();

        this.mainFrame = mainFrame;

        mainMenu = new MainMenu(this);
        upgradeCar = new UpgradeCarMenu(this);
        credits = new CreditsMenu(this);
        instructions = new InstructionsMenu(this);
        levels = new LevelsMenu(this, mgr);

        showMainMenu();
    }

    public void showMainMenu() {
        showMenu(mainMenu);
    }

    public void showUpgradeCar() {
        showMenu(upgradeCar);
    }

    public void showCredits() {
        showMenu(credits);
    }

    public void showInstructions() {
        showMenu(instructions);
    }

    public void showLevelScreen() {
        showMenu(levels);
    }

    private void showMenu(Menu m) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(m);
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }

}
