import java.util.*;

public class HSLComp implements Comparator<HighListItem> {
    /**
     * Comparator for high score records.
     * Score stands for time consumed to finish the game
     * therefore lower ranked higher.
     */
    public int compare(HighListItem i1, HighListItem i2) {
        return i1.getScore() - i2.getScore();
    }
}
