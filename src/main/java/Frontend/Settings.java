package Frontend;

import Backbone.WeatherGetter;
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

    MainWindow mainWindow = new MainWindow();

    // true for Celsius and false for Fahrenheit
    protected boolean declareUnit;

    private void getUnit() {
        Temperature.getItems().add("Celsius");
        Temperature.getItems().add("Fahrenheit");
    }
    @FXML
    protected void setUnit() {
        if (!this.declareUnit)
            mainWindow.unit = " \u2109";
        if (this.declareUnit)
            mainWindow.unit = " \u2013";
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
        getUnit();

        // Gets current unit Celsius or Fahrenheit
        Temperature.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.toString().toLowerCase().equals("fahrenheit")) {
                declareUnit = false;
                setUnit();
                System.out.println("Fahrenheit");
            }
            if (newValue.toString().toLowerCase().equals("celsius")) {
                declareUnit = true;
                setUnit();
                System.out.println("Celsius");
            }

        });
    }

    public void export(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource().toString());
        WeatherGetter.printWeatherToFile("1220","AT",true);
    }
}
