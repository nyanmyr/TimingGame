package timinggame;

import java.io.Serializable;

public class Highscore implements Serializable {

    private static final long serialVersionUID = 1L;
    private final int score;

    public Highscore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }   
}
