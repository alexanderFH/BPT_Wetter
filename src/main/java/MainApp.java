import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

// MainApp durch den eigenen Dateinamen ersetzen
public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Wetterapp");
        Button btn = new Button();
        btn.setText("Set Location");
        btn.setOnAction( (event) -> select() );
        Pane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    void select(){
        System.out.println("HELLO");
    }
}