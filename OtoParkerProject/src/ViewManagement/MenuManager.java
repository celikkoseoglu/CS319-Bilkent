package ViewManagement;

import GameManagement.Player;

import javax.swing.*;
import java.util.logging.Level;

public class MenuManager {

    private JFrame mainFrame;

    private OtoParkerMenu mainMenu;
    private OtoParkerMenu upgradeCar;
    private OtoParkerMenu credits;
    private OtoParkerMenu instructions;
    private OtoParkerMenu levels;
    private LocalDataManager mgr;
    private Player player;

    public MenuManager(JFrame mainFrame) {

        mgr = new LocalDataManager();
        player = mgr.getPlayer();

        this.mainFrame = mainFrame;

        mainMenu = new MainMenu(this);
        upgradeCar = new UpgradeCarMenu(this, player);
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
        levels = new LevelsMenu(this, mgr);
        showMenu(levels);
    }

    public void showLevel(int levelNo) {
        GameObjects.Map map = new GameObjects.Map(this, mgr, levelNo, player);
        showMenu(map);
    }

    private void showMenu(JPanel m) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(m);
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }
}
