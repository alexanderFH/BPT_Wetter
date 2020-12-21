package Frontend;

import Backbone.Day;
import Backbone.WeatherGetter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

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
    private ImageView image;
    @FXML
    private Image img;

    private ArrayList<Day> days;

    // Create Day object to work with Temp
    //Day day;

    // Sets the days based on int values on to tge GUI
    // dayfx = Label of the day
    // controllNumber = used to change from dayOn to dayTwo and so on...
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
        dayOne.setText(dayOne2.getText());
        if(days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMinTemp();
            double maxT = days.get(0).getMaxTemp();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(0).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + Integer.toString( days.get(0).getMinTemp()) + " \u2103");
        maxTemp.setText("maximale Temperatur: " + Integer.toString(days.get(0).getMaxTemp()) + " \u2103");
        //detail.setText(days.get(0).getNarrative());
        changeImage(0);

    }

    @FXML
    private void changeDayTwo() {
        dayOne.setText(dayTwo.getText());
        if(days.get(1).getCurrentTemp() == -999) {
            double minT = days.get(1).getMinTemp();
            double maxT = days.get(1).getMaxTemp();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(1).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + Integer.toString( days.get(1).getMinTemp()) + " \u2103");
        maxTemp.setText("maximale Temperatur: " + Integer.toString(days.get(1).getMaxTemp()) + " \u2103");
        changeImage(1);

    }

    @FXML
    private void changeDayThree() {
        dayOne.setText(dayThree.getText());
        if(days.get(2).getCurrentTemp() == -999) {
            double minT = days.get(2).getMinTemp();
            double maxT = days.get(2).getMaxTemp();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(2).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + Integer.toString( days.get(2).getMinTemp()) + " \u2103");
        maxTemp.setText("maximale Temperatur: " + Integer.toString(days.get(2).getMaxTemp()) + " \u2103");
        changeImage(2);

    }

    @FXML
    private void changeDayFour() {
        dayOne.setText(dayFour.getText());
        if(days.get(3).getCurrentTemp() == -999) {
            double minT = days.get(3).getMinTemp();
            double maxT = days.get(3).getMaxTemp();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(3).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + Integer.toString( days.get(3).getMinTemp()) + " \u2103");
        maxTemp.setText("maximale Temperatur: " + Integer.toString(days.get(3).getMaxTemp()) + " \u2103");
        changeImage(3);

    }

    @FXML
    private void changeDayFive() {
        dayOne.setText(dayFive.getText());
        if(days.get(4).getCurrentTemp() == -999) {
            double minT = days.get(4).getMinTemp();
            double maxT = days.get(4).getMaxTemp();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(4).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + Integer.toString( days.get(4).getMinTemp()) + " \u2103");
        maxTemp.setText("maximale Temperatur: " + Integer.toString(days.get(4).getMaxTemp()) + " \u2103");
        changeImage(4);


    }


    // Change image based on currentTemp/narrative
    @FXML
    private void changeImage(int currentDay) {
        // Für später --> String narative = days.get(0).getNarrative();
        int temp = days.get(currentDay).getCurrentTemp();

        // Declares Images for every Weather icon
        Image sunny = new Image("/img/sunny.png");
        Image cloudy = new Image("/img/clouds.png");
        Image cloudyRain = new Image("/img/cloudy_rain.png");
        Image thunderstorm = new Image("/img/thunderstorm.png");


        // Sets image based on current temp/narrative
        if (0 < temp && temp < 9) {
            image.setImage(cloudyRain);
        } else if (temp >= 9 && temp <= 20 ) {
            image.setImage(cloudy);
        } else if (temp > 20) {
            image.setImage(sunny);
        } else {
            image.setImage(thunderstorm);
        }

    }


    // "Main" method of the controller
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDays(dayOne, 0);
        setDays(dayOne2, 0);
        setDays(dayTwo, 1);
        setDays(dayThree, 2);
        setDays(dayFour, 3);
        setDays(dayFive, 4);

        days = WeatherGetter.getWeatherJson("1220", "AT");

        if(days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMinTemp();
            double maxT = days.get(0).getMaxTemp();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(0).getCurrentTemp() + " \u2103");
        }

        minTemp.setText("minimale Temperatur: " + Integer.toString( days.get(0).getMinTemp()) + " \u2103");
        maxTemp.setText("maximale Temperatur: " + Integer.toString(days.get(0).getMaxTemp()) + " \u2103");
        //detail.setText(days.get(0).getNarrative());

        changeImage(0);
    }
}
