package Frontend;

import Backbone.WeatherGetter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    // true for Celsius and false for Fahrenheit
    protected static boolean declareUnit = true;
    protected static MainWindow mainWindow;
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

    private void getUnit() {
        Temperature.getItems().add("Celsius");
        Temperature.getItems().add("Fahrenheit");
        if(declareUnit)
            Temperature.setValue("Celsius");
        else
            Temperature.setValue("Fahrenheit");
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
                System.out.println("Fahrenheit");
            } else {
                declareUnit = true;
                System.out.println("Celsius");
            }
            mainWindow.start();
        });
    }

    public void export(ActionEvent actionEvent) {
        System.out.println(actionEvent.getSource().toString());
        WeatherGetter.printWeatherToFile("1220", "AT", declareUnit);
    }
}
