package Frontend;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable{
//public class Settings extends Application implements EventHandler {
// Button Export = new Button();
    @FXML
    private ChoiceBox Temperature;

    @FXML
    private TextField Position;

    @FXML
    private Button Export;

    @FXML
    private ColorPicker Colourpick;

    private String getUnit() {
        Temperature.getItems().add("Celsius");
        Temperature.getItems().add("Fahrenheit");
        return (String) Temperature.getValue();
    }

    //@Override
    //public void start(Stage primaryStage) throws Exception {
    //    Export.setOnAction(this);
    //    String unit = getUnit();
    //}

    //@Override
    //public void handle(ActionEvent event) {
    //    if (event.getSource() == Button) {
    //        //save the Information of the Weather
    //    }
    //}

    //@Override
    //public void handle(Event event) {
    //}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    String unit = getUnit();
    }
}
