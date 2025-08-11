package timinggame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class RecentScoreManager {

    private static final File FILE = new File("recent-scores.dat");

    // Save the score to file
    public static void saveRecentScores(ArrayList<Integer> recentScores) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(new RecentScores(recentScores));
        } catch (IOException e) {
        }
    }

    // Load the score from file
    public static ArrayList<Integer> loadRecentScores() {
        if (!FILE.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            RecentScores rs = (RecentScores) in.readObject();
            return rs.getRecentScores();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
