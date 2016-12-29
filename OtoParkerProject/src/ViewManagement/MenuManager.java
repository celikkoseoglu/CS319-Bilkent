package ViewManagement;

import GameManagement.MapManager;
import GameManagement.Player;

import javax.swing.*;

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
        upgradeCar = new UpgradeCarMenu(this, player, mgr);
        credits = new CreditsMenu(this);
        instructions = new InstructionsMenu(this);
        levels = new LevelsMenu(this, mgr);

        showMainMenu();
    }

    public void showMainMenu() {
        mainMenu = new MainMenu(this);
        showMenu(mainMenu);
    }

    public void showUpgradeCar() {
        upgradeCar = new UpgradeCarMenu(this, player, mgr);
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
        MapManager map = new MapManager(this, mgr, levelNo, player);
        showMenu(map);
    }

    private void showMenu(JPanel m) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(m);
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }
}
