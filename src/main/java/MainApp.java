import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.stage.*;

// MainApp durch den eigenen Dateinamen ersetzen
public class MainApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow/mainWindow.fxml"));
        primaryStage.setTitle("BPT-Wetter");
        Scene scene = new Scene(root,600,400);
        scene.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResource("img/sun.png").toExternalForm()));

    }


}