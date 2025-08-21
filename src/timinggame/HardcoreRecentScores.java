package timinggame;

import java.io.Serializable;
import java.util.ArrayList;

public class HardcoreRecentScores implements Serializable {

    private static final long serialVersionUID = 4L;
    private final ArrayList<Integer> harcoreRecentScores;

    public HardcoreRecentScores(ArrayList<Integer> recentScores) {
        this.harcoreRecentScores = recentScores;
    }

    public ArrayList<Integer> getRecentScores() {
        return harcoreRecentScores;
    }   
}

