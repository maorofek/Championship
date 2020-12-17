package Controller;

import Model.*;
import View.View;
import View.FirstStage;
import View.SecondStage;
import View.ScoreStage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    private Championship championship;
    private View view;
    private ScoreStage scoreStage;

    public Controller(Championship _championship, View _view) {
        view = _view;
        championship = _championship;
        FirstStage firstStage = new FirstStage();
        SecondStage secondStage = new SecondStage();
        firstStage.updateBorderPane(championship.getPlayersNames());
        view.setBorderPane(firstStage.getBorderPane());
        firstStage.addEventHandlerToNextStage(event -> {
            try {
                championship.checkIfAllReady();
                championship.setChampionshipType(firstStage.getChampionshipType());
                secondStage.initFirstVBox(View.getVboxForPlayers(championship.getPlayersNames(), 8));
                view.setBorderPane(secondStage.getBorderPane());
            } catch (MyException e) {
                alertForException(e, view);
            }
        });
        firstStage.addEventHandlerToSubmitButton(event -> {
            try {
                championship.addPlayer(firstStage.getTextField().getText());
                firstStage.getTextField().setText("");
                firstStage.updateBorderPane(championship.getPlayersNames());
                view.setBorderPane(firstStage.getBorderPane());
            } catch (Exception exception) {
                alertForException(exception, view);
            }
        });
        secondStage.addEventHandlerToPlayGame(event -> {
            try {
                Player p1, p2;
                int buttonId = Integer.parseInt(((Button) (event.getSource())).getId());
                View playGameView = new View(new Stage(), 500, 370);
                if (buttonId <= 3) {
                    p1 = championship.getPlayers().get(2 * buttonId);
                    p2 = championship.getPlayers().get(2 * buttonId + 1);
                } else if (buttonId == 4) {
                    p1 = championship.getSemiFinal()[0];
                    p2 = championship.getSemiFinal()[1];
                } else if (buttonId == 5) {
                    p1 = championship.getSemiFinal()[2];
                    p2 = championship.getSemiFinal()[3];
                } else {
                    //need to fix
                    p1 = championship.getFinals()[0];
                    p2 = championship.getFinals()[1];
                }
                scoreStage = new ScoreStage(firstStage.getChampionshipType(), p1.getName(), p2.getName());
                playGameView.setBorderPane(scoreStage.getBorderPane());

                scoreStage.addEventHandlerToSubmitButton(event1 -> {
                    try {
                        int index = getIndexByButtonID(buttonId);
                        Player winner = championship.playGame(
                                buttonId,
                                scoreStage.getScores(1),
                                scoreStage.getScores(2),
                                (index),
                                (index + 1),
                                scoreStage.isOverTime()
                        );
                        int nextBtn = secondStage.getNextButton(buttonId);
                        int nextIndex = getIndexByButtonID(nextBtn);
                        secondStage.updateResults(winner.getName(), buttonId, championship.checkGameReadyByBtnId(nextBtn, nextIndex, nextIndex + 1));
                        if (buttonId == 6) {
                            playGameView.showAlert(Alert.AlertType.INFORMATION, "The new champ is: " + winner.getName());
                        }
                        playGameView.closeStage();
                    } catch (Exception e) {
                        alertForException(e, playGameView);
                        if (e.getMessage().equals(Game.DRAW_MESSAGE)) {
                            scoreStage.initTextFields("OVERTIME");
                            playGameView.setBorderPane(scoreStage.getBorderPane());
                        } else if (e.getMessage().equals(TennisGame.DRAW_MESSAGE)) {
                            scoreStage.clearTextFields();
                        }
                    }
                });
            } catch (Exception exception) {
                alertForException(exception, view);
            }
        });
    }

    private int getIndexByButtonID(int buttonId) {
        int offset;
        if (buttonId < 4) {
            offset = buttonId;
        } else if (buttonId == 4) {
            offset = 0;
        } else if (buttonId == 5) {
            offset = 1;
        } else {
            offset = 0;
        }
        return (2 * offset);
    }

    private void alertForException(Exception exception, View view) {
        String message;
        if (exception instanceof MyException)
            message = exception.getMessage();
        else
            message = "Error! " + exception.getClass().getSimpleName();
        view.showAlert(Alert.AlertType.ERROR, message);
    }
}
