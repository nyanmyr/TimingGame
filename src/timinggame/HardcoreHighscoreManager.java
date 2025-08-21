package timinggame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HardcoreHighscoreManager {

    private static final File FILE = new File("hardcore-highscore.dat");

    // Save the score to file
    public static void saveHighScore(int score) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(new HardcoreHighscore(score));
        } catch (IOException e) {
        }
    }

    // Load the score from file
    public static int loadHighScore() {
        if (!FILE.exists()) {
            return 0;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            HardcoreHighscore hs = (HardcoreHighscore) in.readObject();
            return hs.getScore();
        } catch (IOException | ClassNotFoundException e) {
            return 0;
        }
    }
}
