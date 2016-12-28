package GameManagement;

import java.util.ArrayList;

/**
 * Created by celikkoseoglu on 28/12/2016.
 */
public class Unlockables {

    private ArrayList<String> unlockableCarColors;
    private ArrayList<String> unlockableCarWeapons;
    private ArrayList<Double> unlockableTurningRadiuses;

    public Unlockables(String[] unlockableCarColors, String[] unlockableCarWeapons, String[] unlockableCarTurningRadiuses) {
        this.unlockableCarColors = new ArrayList<>();
        this.unlockableCarWeapons = new ArrayList<>();
        this.unlockableTurningRadiuses = new ArrayList<>();

        for (String s : unlockableCarColors)
            this.unlockableCarColors.add(s);
        for (String s : unlockableCarWeapons)
            this.unlockableCarWeapons.add(s);
        for (String s : unlockableCarTurningRadiuses)
            this.unlockableTurningRadiuses.add(Double.parseDouble(s));
    }

    public ArrayList<String> getUnlockableCarColors() {
        return unlockableCarColors;
    }

    public ArrayList<String> getUnlockableCarWeapons() {
        return unlockableCarWeapons;
    }

    public ArrayList<Double> getUnlockableTurningRadiuses() {
        return unlockableTurningRadiuses;
    }
}
