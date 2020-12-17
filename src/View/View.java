package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class View {
    private BorderPane borderPane;
    private Scene scene;
    private Alert alert;
    private Stage primaryStage;

    public View(Stage _primaryStage, int width, int height) {
        borderPane = new BorderPane();
        scene = new Scene(borderPane, width, height);
        alert = new Alert(Alert.AlertType.ERROR);
        primaryStage = _primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
        scene.setRoot(borderPane);
    }


    public static TextField buildTextField(String name) {
        TextField textField = new TextField();
        textField.setEditable(false);
        textField.setText(name);
        return textField;
    }

    public static VBox getVboxForPlayers(List<String> namesOfPlayers, int numberOfTextField) {
        VBox vBox = new VBox();
        int temp = namesOfPlayers.size();
        if (numberOfTextField != temp) {
            for (int i = 0; i < (numberOfTextField - temp); i++) {
                namesOfPlayers.add(null);
            }
        }
        namesOfPlayers.forEach(s -> vBox.getChildren().add(buildTextField(s)));

        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        vBox.setPadding(new Insets(0, 0, 0, 30));

        return vBox;
    }

    public void showAlert(Alert.AlertType type, String message) {
        alert.setAlertType(type);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.show();
    }

    public void closeStage() {
        primaryStage.close();
    }
}
