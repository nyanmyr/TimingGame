package timinggame;

import java.io.Serializable;

public class HardcoreHighscore implements Serializable {

    private static final long serialVersionUID = 3L;
    private final int hardcoreScore;

    public HardcoreHighscore(int score) {
        this.hardcoreScore = score;
    }

    public int getScore() {
        return hardcoreScore;
    }   
}
