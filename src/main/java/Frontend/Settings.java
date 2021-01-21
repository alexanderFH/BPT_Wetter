package Frontend;

import Backbone.WeatherGetter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    public static boolean validAddress = true;
    protected static boolean declareUnit = true;
    protected static String plz = "1220";
    protected static String country = "AT";
    protected static MainWindow mainWindow;

    @FXML
    private AnchorPane settings;

    @FXML
    private ChoiceBox Temperature;
    @FXML
    private TextField Position;
    @FXML
    private Button Export;


    /**
     * Adjust Celsius and Fahrenheit
     */
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
        settings.getStylesheets().add("/css/styles.css");
        Position.setPromptText(plz + "," + country);
        // Gets current unit Celsius or Fahrenheit
        Temperature.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.toString().toLowerCase().equals("fahrenheit")) {
                declareUnit = false;
                MainWindow.stage.close();
            } else {
                declareUnit = true;
                MainWindow.stage.close();
            }
            mainWindow.start();
        });
        settings.getStylesheets().add("/css/styles.css");
    }

    /**
     * Adjust the position
     */
    public void export(ActionEvent actionEvent) {
        if (validAddress) {
            WeatherGetter.printWeatherToFile(Settings.plz, Settings.country, declareUnit);
        } else {
            WeatherGetter.printWeatherToFile("1220", "AT", declareUnit);
        }
        MainWindow.stage.close();

    }

    public void enterAction(ActionEvent actionEvent) {
        System.out.println("Action event");
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
                MainWindow.stage.close();
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
