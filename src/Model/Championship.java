package Model;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Championship {
    public enum ChampionshipTypes {TENNIS, BASKETBALL, SOCCER}

    private ChampionshipTypes championshipType;
    private List<Player> players;
    private Player[] quarterFinalGame;
    private Player[] semiFinal;
    private Player[] finals;

    public Championship() {
        players = new ArrayList();
        quarterFinalGame = new Player[8];
        semiFinal = new Player[4];
        finals = new Player[2];
    }

    public Player[] getSemiFinal() {
        return semiFinal;
    }

    public Player[] getPlayersByButtonId(int btnId) {
        Player[] temp;
        if (btnId < 4) {
            temp = quarterFinalGame;
        } else if (btnId < 6) {
            temp = semiFinal;
        } else {
            temp = finals;
        }
        return temp;
    }

    public boolean checkGameReadyByBtnId(int btnId, int index1, int index2) {
        Player[] temp = getPlayersByButtonId(btnId);
        return temp[index1] != null && temp[index2] != null;
    }

    public Player playGame(int buttonId, List<Integer> points1, List<Integer> points2, int index1, int index2, boolean overTime) throws Exception {

        Game game = chooseGame(buttonId, index1, index2, overTime);
        Player winner = game.play(points1, points2);
        if (buttonId < 4) {
            updateSemiFinalPlayers(buttonId, winner);
        } else if (buttonId < 6) {
            updateFinals(buttonId, winner);
        }
        return winner;
    }

    private Game chooseGame(int buttonId, int index1, int index2, boolean overTime) throws UnexpectedException {
        Player[] temp = getPlayersByButtonId(buttonId);
        if (overTime) {
            return new Game(1, temp[index1], temp[index2]);
        }
        switch (championshipType) {
            case TENNIS:
                return new TennisGame(temp[index1], temp[index2]);
            case BASKETBALL:
                return new Game(4, temp[index1], temp[index2]);
            case SOCCER:
                return new Game(2, temp[index1], temp[index2]);
            default:
                throw new UnexpectedException(championshipType.name());
        }
    }

    public void addPlayer(String playerName) throws MyException {
        if (playerName.trim().isEmpty()) {
            throw new MyException("dont play games, please add a real name :)");
        }
        if (players.size() >= 8) {
            throw new MyException("cannot add another player");
        }
        Player p1 = new Player(playerName);
        quarterFinalGame[players.size()] = p1;
        players.add(p1);
    }

    public void setChampionshipType(String championshipType) {
        this.championshipType = ChampionshipTypes.valueOf(championshipType);
    }

    public List<String> getPlayersNames() {
        return players.stream().map(Player::getName).collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void checkIfAllReady() throws MyException {
        if (players.size() < 8) {
            throw new MyException("Players list isn't ready");
        }
    }

    private void updateSemiFinalPlayers(int btnId, Player winner) {
        semiFinal[btnId] = winner;
    }

    private void updateFinals(int btnId, Player winner) {
        finals[btnId - 4] = winner;
    }

    public Player[] getFinals() {
        return finals;
    }
}
