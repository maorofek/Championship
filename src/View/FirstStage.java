package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.List;

public class FirstStage extends Form {

    private Button nextStage;
    private TextField textField;
    private RadioButton basketBallRB, soccerRB, tennisRB;
    private ToggleGroup toggleGroup;

    public FirstStage() {
        super();
        nextStage = new Button("Start Championship");
        submitButton.setText("Add Player");
        VBox rightVBox = new VBox();
        VBox midVBox = new VBox();
        textField = new TextField();
        //    ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup = new ToggleGroup();
        HBox topHBox = new HBox();
        HBox bottomHBox = new HBox();
        HBox labelTopHBox = new HBox();

        Label label = new Label("Player name:");
        Label topLabel = new Label("Championship");
        topLabel.setFont(Font.font(28));

        labelTopHBox.getChildren().add(topLabel);
        labelTopHBox.setAlignment(Pos.CENTER);

        topHBox.setAlignment(Pos.CENTER);
        bottomHBox.setAlignment(Pos.CENTER);
        midVBox.setAlignment(Pos.CENTER);
        rightVBox.setAlignment(Pos.CENTER_LEFT);

        midVBox.setSpacing(15);
        topHBox.setSpacing(15);
        bottomHBox.setSpacing(15);

        topHBox.getChildren().addAll(label, textField);
        bottomHBox.getChildren().addAll(submitButton, nextStage);

        midVBox.getChildren().addAll(topHBox, bottomHBox);

        basketBallRB = new RadioButton("Basketball");
        tennisRB = new RadioButton("Tennis");
        soccerRB = new RadioButton("Soccer");

        basketBallRB.setSelected(true);
        basketBallRB.setToggleGroup(toggleGroup);
        soccerRB.setToggleGroup(toggleGroup);
        tennisRB.setToggleGroup(toggleGroup);


        borderPane.setCenter(midVBox);
        borderPane.setTop(labelTopHBox);
        borderPane.setRight(rightVBox);
        rightVBox.getChildren().addAll(basketBallRB, soccerRB, tennisRB);
    }


    public void updateBorderPane(List<String> list) {
        borderPane.setLeft(View.getVboxForPlayers(list, 8));
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void addEventHandlerToNextStage(EventHandler<ActionEvent> startEventHandler) {
        nextStage.setOnAction(startEventHandler);
    }

    public TextField getTextField() {
        return textField;
    }

    public String getChampionshipType() {
        if (basketBallRB.isSelected()) {
            return "BASKETBALL";
        }
        if (soccerRB.isSelected()) {
            return "SOCCER";
        }
        return "TENNIS";
    }
}
