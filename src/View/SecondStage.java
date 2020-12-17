package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SecondStage {
    private BorderPane borderPane;

    private Button quarterButton1, quarterButton2, quarterButton3, quarterButton4;
    private Button semiButton1, semiButton2;
    private Button finalButton;
    private HBox hbox;
    private VBox vBox1;
    private VBox vBox2;
    private VBox vBox3;
    private VBox vBox4;
    private VBox vBox5;
    private VBox vBox6;
    private VBox vBox7;

    public SecondStage() {
        borderPane = new BorderPane();
        hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);

        vBox1 = new VBox();
        vBox2 = new VBox();
        vBox3 = new VBox();
        vBox4 = new VBox();
        vBox5 = new VBox();
        vBox6 = new VBox();
        vBox7 = new VBox();

        quarterButton1 = new Button("play game");
        quarterButton2 = new Button("play game");
        quarterButton3 = new Button("play game");
        quarterButton4 = new Button("play game");
        semiButton1 = new Button("play game");
        semiButton2 = new Button("play game");
        finalButton = new Button("play game");

        quarterButton1.setId("0");
        quarterButton2.setId("1");
        quarterButton3.setId("2");
        quarterButton4.setId("3");
        semiButton1.setId("4");
        semiButton2.setId("5");
        finalButton.setId("6");

        semiButton1.setDisable(true);
        semiButton2.setDisable(true);
        finalButton.setDisable(true);

        vBox1.setSpacing(30);
        vBox2.setSpacing(85);
        vBox3.setSpacing(85);
        vBox4.setSpacing(190);
        vBox5.setSpacing(190);
        vBox6.setSpacing(60);
        vBox7.setSpacing(60);

        Arrays.asList(vBox1, vBox2, vBox3, vBox4, vBox5, vBox6, vBox7).forEach(vBox -> vBox.setAlignment(Pos.CENTER));
        hbox.getChildren().addAll(vBox1, vBox2, vBox3, vBox4, vBox5, vBox6, vBox7);
        startShowing();
    }

    public int getNextButton(int btnId) {
        if (btnId < 2) {
            return 4;
        } else if (btnId < 4) {
            return 5;
        } else {
            return 6;
        }
    }

    private void toggleButton(int btnId, boolean disable) {
        Arrays.asList(quarterButton1, quarterButton2, quarterButton3, quarterButton4, semiButton1, semiButton2, finalButton)
                .stream()
                .filter(button -> Integer.parseInt(button.getId()) == btnId)
                .collect(Collectors.toList())
                .get(0)
                .setDisable(disable);
    }

    public void updateResults(String name, int btnId, boolean nextDisable) {
        VBox temp;
        if (btnId < 4) {
            temp = vBox3;
        } else if (btnId < 6) {
            temp = vBox5;
        } else {
            temp = vBox7;
        }
        ((TextField) temp.getChildren()
                .stream()
                .filter(tf -> Integer.parseInt(tf.getId()) == (btnId))
                .collect(Collectors.toList())
                .get(0))
                .setText(name);
        toggleButton(btnId, true);
        if (btnId != 6) {
            toggleButton(getNextButton(btnId), !nextDisable);
        }
    }

    private void startShowing() {
        //init Vboxes :

        for (int i = 0; i < 8; i++) {
            vBox1.getChildren().addAll(View.buildTextField(null));
            vBox1.getChildren().get(i).setId(String.valueOf(i));
        }

        vBox2.getChildren().addAll(quarterButton1, quarterButton2, quarterButton3, quarterButton4);

        for (int i = 0; i < 4; i++) {
            vBox3.getChildren().addAll(View.buildTextField(null));
            vBox3.getChildren().get(i).setId(String.valueOf(i));
        }

        vBox4.getChildren().addAll(semiButton1, semiButton2);

        for (int i = 0; i < 2; i++) {
            vBox5.getChildren().addAll(View.buildTextField(null));
            vBox5.getChildren().get(i).setId(String.valueOf(i + 4));

        }
        vBox6.getChildren().addAll(finalButton);
        vBox7.getChildren().add(View.buildTextField(null));
        vBox7.getChildren().get(0).setId(String.valueOf(6));
    }

    public void initFirstVBox(VBox _vBox) {
        vBox1 = _vBox;
        hbox.getChildren().set(0, vBox1);
    }

    public BorderPane getBorderPane() {
        borderPane.setCenter(hbox);
        return borderPane;
    }

    public void addEventHandlerToPlayGame(EventHandler<ActionEvent> startEventHandler) {
        quarterButton1.setOnAction(startEventHandler);
        quarterButton2.setOnAction(startEventHandler);
        quarterButton3.setOnAction(startEventHandler);
        quarterButton4.setOnAction(startEventHandler);
        semiButton1.setOnAction(startEventHandler);
        semiButton2.setOnAction(startEventHandler);
        finalButton.setOnAction(startEventHandler);
    }
}
