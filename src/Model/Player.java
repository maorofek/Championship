package Model;

import java.util.List;

public class Player {

    private String name;
    private int score;

    public Player(String playerName) {
        setName(playerName);
    }

    private void setName(String name) {
        this.name = name;
    }

    public void sumPoints(List<Integer> points) {
        score = points.stream().mapToInt(Integer::intValue).sum();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

}
