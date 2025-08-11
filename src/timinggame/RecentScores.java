package timinggame;

import java.io.Serializable;
import java.util.ArrayList;

public class RecentScores implements Serializable {

    private static final long serialVersionUID = 2L;
    private final ArrayList<Integer> recentScores;

    public RecentScores(ArrayList<Integer> recentScores) {
        this.recentScores = recentScores;
    }

    public ArrayList<Integer> getRecentScores() {
        return recentScores;
    }   
}

