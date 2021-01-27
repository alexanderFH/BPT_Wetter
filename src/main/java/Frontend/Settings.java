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
/*
Standart Adresse wird festgelegt (1220 AT)
 */
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


    /*
    Dieser Befehl wird untenabgerufen
     */
    private void getUnit() {
        Temperature.getItems().add("Celsius");
        Temperature.getItems().add("Fahrenheit");
        if (declareUnit)
            Temperature.setValue("Celsius");
        else
            Temperature.setValue("Fahrenheit");
    }

    /*
    unit Charcode AR stellt
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getUnit();
        settings.getStylesheets().add("/css/styles.css");
        Position.setPromptText(plz + "," + country); //helle graue Text der als Beispiel dient
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
    }


    /*
    Wenn neuer Ort angegeben --> wird die bei exportieren der Wetterinformationen auf dem Gerät die Wetterdaten vom
        neuen Ort angebeben
    Wenn neuer Ort nicht vorhanden --> werden die Wetterinformationen von der Standartadresse 1220 AT abgespeichert
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
        positionChange();
    }

   /*
   Positionstext wird eingelesen
   Wenn Text leer --> Abbruch
   Wenn Text eingegeben --> Text wird durch Beistrich geteilt
   Vor dem Beistrich --> Postleitzahl
   Nach dem Beistrich --> Ländercode
   Wenn die Adresse sich nicht geändert hat --> passiert nichts
   Wenn sich die Adresse geändert hat --> wird die neue Adresse bei MainWindow übernommen Settings schließt sich
    */
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
        } else { //Wenn kein Beistrich eingeeben wurde --> Fehlermeldung
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Positions-Fehler");
            alert.setHeaderText("Ung\u00fcltige Position!");
            alert.setContentText("Wurde vielleicht der ',' vergessen?");
            alert.show();
        }
    }
}
