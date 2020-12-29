package Frontend;

import Backbone.Day;
import Backbone.WeatherGetter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    Date currentDate = null;

    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @FXML
    private MenuItem closeMenu;
    @FXML
    private Label displayDate;
    @FXML
    private Label dayOne;
    @FXML
    private Label dayOne2;
    @FXML
    private Label dayTwo;
    @FXML
    private Label dayThree;
    @FXML
    private Label dayFour;
    @FXML
    private Label dayFive;
    @FXML
    private Label temp;
    @FXML
    private Label minTemp;
    @FXML
    private Label maxTemp;
    @FXML
    private Label detail;
    @FXML
    private ImageView image;  // Displays "Main Image"
    @FXML
    private Image img;
    @FXML
    private ImageView imageNextDay1;  // Icon Box 1
    @FXML
    private Image imgNextDay1;
    @FXML
    private ImageView imageNextDay2;  // Icon Box 2
    @FXML
    private Image imgNextDay2;
    @FXML
    private ImageView imageNextDay3;  // Icon Box 3
    @FXML
    private Image imgNextDay3;
    @FXML
    private ImageView imageNextDay4;  // icon Box 4
    @FXML
    private Image imgNextDay4;
    @FXML
    private ImageView imageNextDay5;  // Icon Box 5
    @FXML
    private Image imgNextDay5;

    @FXML
    private Label nextDayTemp1;
    @FXML
    private Label nextDayTemp2;
    @FXML
    private Label nextDayTemp3;
    @FXML
    private Label nextDayTemp4;
    @FXML
    private Label nextDayTemp5;

    private ArrayList<Day> days;

    private boolean bp1Pressed;
    private boolean bp2Pressed;
    private boolean bp3Pressed;
    private boolean bp4Pressed;
    private boolean bp5Pressed;

    @FXML
    private BorderPane bp1;
    @FXML
    private BorderPane bp2;
    @FXML
    private BorderPane bp3;
    @FXML
    private BorderPane bp4;
    @FXML
    private BorderPane bp5;

    // Sets the days based on int values on to tge GUI
    // dayfx = Label of the day
    // controllNumber = used to change from dayOne to dayTwo and so on...
    @FXML
    private void setDays(Label dayfx, int controllNumber) {

        // Get current day
        String day = null;
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        // Loop to catch numbers >= 7 -> resets on sundays
        for (int i = 1; i <= controllNumber; i++) {
            if (currentDay >= 7) {
                currentDay = 0;
            }

            currentDay += 1;
        }

        // Set days based on int value
        switch (currentDay) {
            case Calendar.MONDAY -> day = "Montag";         // Monday = 2
            case Calendar.TUESDAY -> day = "Dienstag";      // Tuesday = 3
            case Calendar.WEDNESDAY -> day = "Mittwoch";    // Wednesday = 4
            case Calendar.THURSDAY -> day = "Donnerstag";   // Thursday = 5
            case Calendar.FRIDAY -> day = "Freitag";        // Friday = 6
            case Calendar.SATURDAY -> day = "Samstag";      // Saturday = 7
            case Calendar.SUNDAY -> day = "Sonntag";        // Sunday = 1
            default -> System.out.println("Invalid Day");
        }

       dayfx.setText(day);

    }

    @FXML
    private void changeToFirstDay() {
        currentDate = new Date();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayOne2.getText());
        if(days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMIN_TEMP();
            double maxT = days.get(0).getMAX_TEMP();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(0).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + days.get(0).getMIN_TEMP() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(0).getMAX_TEMP() + " \u2103");
        //detail.setText(days.get(0).getNarrative());
        changeImage(0, days.get(0).getCurrentTemp());

        bp1Pressed = true;
        bp2Pressed = false;
        bp3Pressed = false;
        bp4Pressed = false;
        bp5Pressed = false;
        borderPanePressed();
    }

    @FXML
    private void changeDayTwo() {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayTwo.getText());
        if(days.get(1).getCurrentTemp() == -999) {
            double minT = days.get(1).getMIN_TEMP();
            double maxT = days.get(1).getMAX_TEMP();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(1).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + days.get(1).getMIN_TEMP() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(1).getMAX_TEMP() + " \u2103");
        changeImage(1, days.get(1).getCurrentTemp());

        bp2Pressed = true;
        bp1Pressed = false;
        bp4Pressed = false;
        bp3Pressed = false;
        bp5Pressed = false;
        borderPanePressed();
    }

    @FXML
    private void changeDayThree() {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 2);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayThree.getText());
        if(days.get(2).getCurrentTemp() == -999) {
            double minT = days.get(2).getMIN_TEMP();
            double maxT = days.get(2).getMAX_TEMP();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(2).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + days.get(2).getMIN_TEMP() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(2).getMAX_TEMP() + " \u2103");
        changeImage(2, days.get(2).getCurrentTemp());

        bp3Pressed = true;
        bp1Pressed = false;
        bp2Pressed = false;
        bp4Pressed = false;
        bp5Pressed = false;
        borderPanePressed();
    }

    @FXML
    private void changeDayFour() {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 3);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayFour.getText());
        if(days.get(3).getCurrentTemp() == -999) {
            double minT = days.get(3).getMIN_TEMP();
            double maxT = days.get(3).getMAX_TEMP();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(3).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + days.get(3).getMIN_TEMP() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(3).getMAX_TEMP() + " \u2103");
        changeImage(3, days.get(3).getCurrentTemp());

        bp4Pressed = true;
        bp1Pressed = false;
        bp2Pressed = false;
        bp3Pressed = false;
        bp5Pressed = false;
        borderPanePressed();
    }

    @FXML
    private void changeDayFive() {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 4);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayFive.getText());
        if(days.get(4).getCurrentTemp() == -999) {
            double minT = days.get(4).getMIN_TEMP();
            double maxT = days.get(4).getMAX_TEMP();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(4).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + days.get(4).getMIN_TEMP() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(4).getMAX_TEMP() + " \u2103");
        changeImage(4, days.get(4).getCurrentTemp());

        bp5Pressed = true;
        bp1Pressed = false;
        bp2Pressed = false;
        bp3Pressed = false;
        bp4Pressed = false;
        borderPanePressed();
    }


    // Change image based on currentTemp/narrative
    @FXML
    private void changeImage(int currentDay, double currentTemp) {
        // Für später --> String narative = days.get(0).getNarrative();
        //int temp = days.get(currentDay).getCurrentTemp();

        // Declares Images for every Weather icon
        Image sunny = new Image("/img/sunny.png");
        Image cloudy = new Image("/img/clouds.png");
        Image cloudyRain = new Image("/img/cloudy_rain.png");
        Image thunderstorm = new Image("/img/thunderstorm.png");
        //Image snowy = new Image("/img/snowy.png");

        // Checks which Image is currently displayed
        String checkImage = "";


        // Sets image based on current temp/narrative
        if (0 < currentTemp && currentTemp < 9) {
            image.setImage(cloudyRain);
            checkImage = "cloudyRain";
        } else if (currentTemp >= 9 && currentTemp <= 20 ) {
            image.setImage(cloudy);
            checkImage = "cloudy";
        } else if (currentTemp > 20) {
            image.setImage(sunny);
            checkImage = "sunny";
        } else {
            image.setImage(thunderstorm);
            checkImage = "thunderstorm";
        }

        // Sets images for icon boxes
        switch (checkImage) {
            case "cloudyRain":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(cloudyRain);
                    case 1 -> imageNextDay2.setImage(cloudyRain);
                    case 2 -> imageNextDay3.setImage(cloudyRain);
                    case 3 -> imageNextDay4.setImage(cloudyRain);
                    case 4 -> imageNextDay5.setImage(cloudyRain);
                }
                break;
            case "cloudy":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(cloudy);
                    case 1 -> imageNextDay2.setImage(cloudy);
                    case 2 -> imageNextDay3.setImage(cloudy);
                    case 3 -> imageNextDay4.setImage(cloudy);
                    case 4 -> imageNextDay5.setImage(cloudy);
                }
                break;
            case "sunny":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(sunny);
                    case 1 -> imageNextDay2.setImage(sunny);
                    case 2 -> imageNextDay3.setImage(sunny);
                    case 3 -> imageNextDay4.setImage(sunny);
                    case 4 -> imageNextDay5.setImage(sunny);
                }
                break;
            default:
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(thunderstorm);
                    case 1 -> imageNextDay2.setImage(thunderstorm);
                    case 2 -> imageNextDay3.setImage(thunderstorm);
                    case 3 -> imageNextDay4.setImage(thunderstorm);
                    case 4 -> imageNextDay5.setImage(thunderstorm);
                }
                break;
        }

    }

    // "Main" method of the controller
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentDate = new Date();

        displayDate.setText(dateFormat.format(currentDate));

        setDays(dayOne, 0);
        setDays(dayOne2, 0);
        setDays(dayTwo, 1);
        setDays(dayThree, 2);
        setDays(dayFour, 3);
        setDays(dayFive, 4);

        bp1.getStyleClass().add("clickedOnPane");

        days = WeatherGetter.getWeatherJson("1220", "AT");

        if(days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMIN_TEMP();
            double maxT = days.get(0).getMAX_TEMP();
            temp.setText((minT+maxT)/2 + " \u2103");
            nextDayTemp1.setText((minT+maxT)/2 + " \u2103");

        } else {
            temp.setText(days.get(0).getCurrentTemp() + " \u2103");
            nextDayTemp1.setText(days.get(0).getCurrentTemp() + " \u2103");
        }

        /**folgendes wird eventuell durch eine schleife/switch o.Ä. ersetzt...*/

        if(days.get(1).getCurrentTemp() == -999) {
            double minT = days.get(1).getMIN_TEMP();
            double maxT = days.get(1).getMAX_TEMP();
            nextDayTemp2.setText((minT+maxT)/2 + " \u2103");
        } else {
            nextDayTemp2.setText(days.get(1).getCurrentTemp() + " \u2103");
        }

        if(days.get(2).getCurrentTemp() == -999) {
            double minT = days.get(2).getMIN_TEMP();
            double maxT = days.get(2).getMAX_TEMP();
            nextDayTemp3.setText((minT+maxT)/2 + " \u2103");
        } else {
            nextDayTemp3.setText(days.get(2).getCurrentTemp() + " \u2103");
        }

        if(days.get(3).getCurrentTemp() == -999) {
            double minT = days.get(3).getMIN_TEMP();
            double maxT = days.get(3).getMAX_TEMP();
            nextDayTemp4.setText((minT+maxT)/2 + " \u2103");
        } else {
            nextDayTemp4.setText(days.get(3).getCurrentTemp() + " \u2103");
        }

        if(days.get(4).getCurrentTemp() == -999) {
            double minT = days.get(4).getMIN_TEMP();
            double maxT = days.get(4).getMAX_TEMP();
            nextDayTemp5.setText((minT+maxT)/2 + " \u2103");
        } else {
            nextDayTemp5.setText(days.get(4).getCurrentTemp() + " \u2103");
        }

        minTemp.setText("minimale Temperatur: " + days.get(0).getMIN_TEMP() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(0).getMAX_TEMP() + " \u2103");
        //detail.setText(days.get(0).getNarrative());


        changeImage(1, days.get(1).getCurrentTemp());
        changeImage(2, days.get(2).getCurrentTemp());
        changeImage(3, days.get(3).getCurrentTemp());
        changeImage(4, days.get(4).getCurrentTemp());
        changeImage(0, days.get(0).getCurrentTemp());


    }

    public void borderPanePressed() {
        if(bp1Pressed) {
            bp1.getStyleClass().add("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
        }
        if(bp2Pressed) {
            bp2.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
        }
        if(bp3Pressed) {
            bp3.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
        }
        if(bp4Pressed) {
            bp4.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
        }
        if(bp5Pressed) {
            bp5.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
        }
    }

    public void openSettings() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Settings/settings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 400));
            stage.show();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow() {
        System.exit(0);
    }
}
