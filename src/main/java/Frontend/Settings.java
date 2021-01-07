package Frontend;

import Backbone.WeatherGetter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    // true for Celsius and false for Fahrenheit
    protected static boolean declareUnit = true;
    protected static String plz = "1220";
    protected static String country = "AT";
    protected static MainWindow mainWindow;


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
        if (declareUnit)
            Temperature.setValue("Celsius");
        else
            Temperature.setValue("Fahrenheit");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUnit();
        Position.setPromptText(plz + "," + country);
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
        WeatherGetter.printWeatherToFile(Settings.plz, Settings.country, declareUnit);
    }

    public void enterAction(ActionEvent actionEvent) {
        positionChange();
    }

    public void mouseExit(MouseEvent mouseEvent) {
        positionChange();
    }

    private void positionChange() {
        String position = Position.getText();
        if (position.equals(""))
            return;
        if (position.contains(",")) {
            String newplz = position.split(",")[0].trim();
            String newcountry = position.split(",")[1].trim();
            if (!plz.equals(newplz) || !country.equals(newcountry)) { //Only refresh if different plz or country
                plz = newplz;
                country = newcountry;
                mainWindow.start();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Positions-Fehler");
            alert.setHeaderText("Ung\u00fcltige Position!");
            alert.setContentText("Wurde vielleicht der ',' vergessen?");
            alert.show();
        }
    }
}
