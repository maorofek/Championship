package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreStage extends Form {
    private List<TextField> textFields1;
    private List<TextField> textFields2;
    private Label name1;
    private Label name2;
    private VBox vbox;
    private int rounds;

    public ScoreStage(String gameType, String _name1, String _name2) {
        super();
        textFields1 = new ArrayList<>();
        textFields2 = new ArrayList<>();
        name1 = new Label(_name1);
        name2 = new Label(_name2);

        vbox = new VBox();
        initTextFields(gameType);
        submitButton.setText("done");
        vbox.getChildren().addAll(
                addTextFieldToHBox(textFields1, name1),
                addTextFieldToHBox(textFields2, name2),
                submitButton
        );
        borderPane.setCenter(vbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);
    }

    public boolean isOverTime() {
        return (rounds == 1);
    }

    public void clearTextFields() {
        textFields1.forEach(textField -> textField.setText(""));
        textFields2.forEach(textField -> textField.setText(""));
    }

    public void initTextFields(String gameType) {
        rounds = 0;
        switch (gameType) {
            case "BASKETBALL":
                rounds = 4;
                break;
            case "SOCCER":
                rounds = 2;
                break;
            case "TENNIS":
                rounds = 5;
                break;
            case "OVERTIME":
                rounds = 1;
                textFields1.clear();
                textFields2.clear();
                break;
            default:
                break;
        }
        for (int i = 0; i < (rounds); i++) {
            textFields1.add(new TextField());
            textFields2.add(new TextField());
        }
        if (rounds == 1) {
            vbox.getChildren().set(0, addTextFieldToHBox(textFields1, name1));
            vbox.getChildren().set(1, addTextFieldToHBox(textFields2, name2));
            borderPane.setCenter(vbox);
        }
    }

    private HBox addTextFieldToHBox(List<TextField> list, Label label) {
        HBox hbox = new HBox();
        hbox.getChildren().add(label);
        for (int i = 0; i < list.size(); i++) {
            TextField tf = list.get(i);
            tf.setMaxWidth(50);
            hbox.getChildren().add(tf);
            tf.setId(String.valueOf(i));
        }
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(15);
        return hbox;
    }

    public List<Integer> getScores(int x) throws UnexpectedException {
        List<TextField> list;
        switch (x) {
            case 1:
                list = textFields1;
                break;
            case 2:
                list = textFields2;
                break;
            default:
                throw new UnexpectedException("unexpected " + x);
        }
        return list
                .stream()
                .filter(textField -> !textField.getText().trim().isEmpty())
                .map(textField -> Integer.parseInt(textField.getText()))
                .collect(Collectors.toList());
    }
}
