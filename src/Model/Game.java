package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    protected int parts;
    protected Player player1;
    protected Player player2;
    public static final String DRAW_MESSAGE = "draw, play more";
    protected List<Integer> points1;
    protected List<Integer> points2;

    public Game(int _parts, Player _player1, Player _player2) {
        parts = _parts;
        player2 = _player2;
        player1 = _player1;
        points2 = new ArrayList<>();
        points1 = new ArrayList<>();
    }

    public Player play(List<Integer> points1, List<Integer> points2) throws MyException {
        basicGameCheck(points1, points2);

        if (points1.size() != parts) {
            throw new MyException("invalid parts input");
        }
        player1.sumPoints(points1); //need to init
        player2.sumPoints(points2); //need to init
        if (player1.getScore() == player2.getScore()) throw new MyException(DRAW_MESSAGE);
        if (player1.getScore() > player2.getScore()) {
            return player1;
        } else {
            return player2;
        }
    }

    protected void basicGameCheck(List<Integer> points1, List<Integer> points2) throws MyException {
        if (points1.size() != points2.size()) {
            throw new MyException("invalid parts input");
        }
    }
}
