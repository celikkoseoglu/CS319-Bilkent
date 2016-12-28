package ViewManagement;

import GameManagement.Player;
import GameObjects.Obstacle;
import GameObjects.Target;

import javax.lang.model.type.ArrayType;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * the Local Data Manager class for OtoParker. Manages Local Data
 * bu class'i da kim bilir kaç defa revize ettim. versiyon 17 olmus 2015'den beri...
 *
 * @author Çelik Köseoğlu
 * @version 17
 */

public class LocalDataManager {
    private String OS_FILE_PATH;

    public LocalDataManager() {
        setOS_FILE_PATH();
    }

    /**
     * @return true if operating system is supported by OtoParker
     * sets the default OS file path for OtoParker's data.
     * Windows : C:/Users/user.name/Documents/OtoParker
     * macOS : user.home/OtoParker/
     * Linux? Same with macOS
     */
    public boolean setOS_FILE_PATH() {
        String osName = System.getProperty("os.name");
        System.out.println("Operating System: " + osName + "\n");

        if (osName.contains("Mac"))
            OS_FILE_PATH = System.getProperty("user.home") + "/OtoParker/";

        else if (osName.contains("Windows")) //what if we changed user.name to user.home? will try soon...
            OS_FILE_PATH = "C:/Users/" + System.getProperty("user.name") + "/Documents/OtoParker/";

        else if (osName.contains("Linux"))
            OS_FILE_PATH = System.getProperty("user.home") + "/OtoParker/";

        System.out.println("Default OS FilePath: " + OS_FILE_PATH);
        System.out.println("\nOS FILE PATH SET SUCCESS\n");
        return true;
    }

    private boolean saveText(String filePath, String fileName, String text) {
        try {
            directoryExists(filePath);
            FileWriter fw = new FileWriter(filePath + fileName);
            fw.write(text);
            fw.close();
            return true;
        } catch (Exception ioe) {
            System.err.println("IOException: " + ioe.getMessage());
            return false;
        }
    }

    /**
     * creates and returns a note object from the specified noteName
     *
     * @param textFile is the name of the note
     * @return String object or null if note file is not found
     */
    public String readText(String textFile, boolean internal) {
        try {
            FileReader fr = new FileReader((internal ? "data/" : OS_FILE_PATH) + textFile);
            BufferedReader textReader = new BufferedReader(fr);
            StringBuffer strBuffer = new StringBuffer();
            String tempString = textReader.readLine();

            while (tempString != null) {
                strBuffer.append(tempString);
                tempString = textReader.readLine();
            }

            textReader.close();
            fr.close();
            return strBuffer.toString();
        } catch (FileNotFoundException fNEF) {
            if (textFile.equals("levelProgress.txt") || textFile.equals("stats.txt") || textFile.equals("unlocks.txt"))
                return readText(textFile, true);
            else
                return null;
        } catch (Exception e) {
            System.out.println(e.toString() + ": Something is wrong with OtoParker's note files");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * checks if the specified directory exists. If yes, returns true, if no, creates the directory and returns false
     *
     * @param directoryName the directory that is to be created if not found
     * @return true if exists
     */
    private boolean directoryExists(String directoryName) {
        File f = new File(directoryName);
        if (f.exists() && f.isDirectory())
            return true;
        else {
            try {
                new File(directoryName).mkdirs();
            } catch (SecurityException sException) {
                System.err.println(sException.toString() + " :Cannot create " + directoryName + " directory because of security permissions");
            } catch (Exception ex) {
                System.err.println(ex.toString() + " :Unknown exception occured while creating \"" + directoryName + "\" directory.");
            }
            return false;
        }
    }

    public ArrayList<Obstacle> getObstacles(int level) {
        String[] obstacles = readText(level + "/obstacles.txt", true).split("\\|");

        ArrayList<Obstacle> obstacleList = new ArrayList<>();

        for (String s : obstacles) {
            String[] obstacle = s.split(":");
            obstacleList.add(new Obstacle(Integer.parseInt(obstacle[2]), Integer.parseInt(obstacle[3]), obstacle[1]));
        }

        return obstacleList;
    }

    public Target getTarget(int level) {
        String[] target = readText(level + "/target.txt", true).split("\\|");
        return new Target(Integer.parseInt(target[0]), Integer.parseInt(target[1]), Integer.parseInt(target[2]), Integer.parseInt(target[3]));
    }

    public Player getPlayer() {
        String[] unlocks = readText("unlocks.txt", false).split("\\|");
        String[] stats = readText("stats.txt", false).split("\\|");

        String[] unlockedCarColorStringArray = unlocks[0].split("-");
        String[] unlockedCarWeaponsStringArray = unlocks[1].split("-");
        String[] unlockedCarTurningRadiusesStringArray = unlocks[2].split("-");

        int numberOfStars = Integer.parseInt(stats[0]);
        String currentCarColor = stats[1];
        String currentCarWeapon = stats[2];
        double currentCarTurningRadius = Double.parseDouble(stats[3]);

        return new Player(numberOfStars, currentCarColor, currentCarWeapon, currentCarTurningRadius, unlockedCarColorStringArray, unlockedCarWeaponsStringArray, unlockedCarTurningRadiusesStringArray);
    }
}