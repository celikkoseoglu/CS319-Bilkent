package ViewManagement;

import javax.swing.*;

public class MenuManager {

    private JFrame mainFrame;

    private OtoParkerMenu mainMenu;
    private OtoParkerMenu upgradeCar;
    private OtoParkerMenu credits;
    private OtoParkerMenu instructions;
    private OtoParkerMenu levels;
    private OtoParkerMenu pauseMenu;

    private LocalDataManager mgr;

    public MenuManager(JFrame mainFrame) {

        mgr = new LocalDataManager();

        this.mainFrame = mainFrame;

        mainMenu = new MainMenu(this);
        upgradeCar = new UpgradeCarMenu(this);
        credits = new CreditsMenu(this);
        instructions = new InstructionsMenu(this);
        levels = new LevelsMenu(this, mgr);
        pauseMenu = new PauseMenu(this);

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
        showMenu(pauseMenu);
    }

    public void showLevelScreen() {
        showMenu(levels);
    }

    public void showLevel(int levelNo) {
//        GameObjects.MovingSpriteEx level = new MovingSpriteEx(this, levelNo);
//        showMenu(level);
        GameObjects.Map map = new GameObjects.Map(mgr, levelNo, pauseMenu);
        showMenu(map);
    }

    private void showMenu(JPanel m) {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(m);
        mainFrame.getContentPane().revalidate();
        mainFrame.getContentPane().repaint();
    }

}
