package Model;

import java.util.List;

public class TennisGame extends Game {
    private final int threeParts = 3;

    public static final String DRAW_MESSAGE = "Tennis draw, play more";

    public TennisGame(Player _player1, Player _player2) {
        super(5, _player1, _player2);
    }


    @Override
    public Player play(List<Integer> points1, List<Integer> points2) throws MyException {
        int winsForPlayer1 = 0;
        int winsForPlayer2 = 0;
        int draws = 0;

        basicGameCheck(points1, points2);
        if (points1.size() != parts && points1.size() != threeParts) {
            throw new MyException("invalid parts input");
        }
        for (int i = 0; i < points1.size(); i++) {
            if (points1.get(i) != points2.get(i)) {
                if (points1.get(i) > points2.get(i)) {
                    winsForPlayer1++;
                } else {
                    winsForPlayer2++;
                }
            } else {
                draws++;
            }
        }
        if (draws >= 3) {
            throw new MyException(DRAW_MESSAGE);
        }
        if ((winsForPlayer1 > winsForPlayer2) && winsForPlayer1 >= 3) {
            return player1;
        }
        if ((winsForPlayer2 > winsForPlayer1) && winsForPlayer2 >= 3) {
            return player2;
        }
        throw new MyException(DRAW_MESSAGE);
    }

}
