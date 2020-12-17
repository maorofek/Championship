import Controller.Controller;
import Model.Championship;
import View.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Program extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Championship championship = new Championship();
        View view = new View(primaryStage, 1200, 800);
        Controller controller = new Controller(championship, view);
    }

}
