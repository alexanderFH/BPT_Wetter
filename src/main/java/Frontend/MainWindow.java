package Frontend;

import Backbone.Day;
import Backbone.WeatherGetter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    @FXML
    private Label dayOne;
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
    private void changeDayThree() {
        dayOne.setText(dayThree.getText());
        try {
            ArrayList<Day> days = WeatherGetter.getWeatherJson("1220:AT");
            double minT = days.get(2).getMinTemp();
            double maxT = days.get(2).getMaxTemp();

            temp.setText((minT+maxT)/2 + " \u2103");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeDayFive() {
        dayOne.setText(dayFive.getText());
        try {
            ArrayList<Day> days = WeatherGetter.getWeatherJson("1220:AT");
            double minT = days.get(4).getMinTemp();
            double maxT = days.get(4).getMaxTemp();

            temp.setText((minT+maxT)/2 + " \u2103");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeDayTwo() {
        dayOne.setText(dayTwo.getText());
        try {
            ArrayList<Day> days = WeatherGetter.getWeatherJson("1220:AT");
            double minT = days.get(1).getMinTemp();
            double maxT = days.get(1).getMaxTemp();

            temp.setText((minT+maxT)/2 + " \u2103");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeDayFour() {
        dayOne.setText(dayFour.getText());
        try {
            ArrayList<Day> days = WeatherGetter.getWeatherJson("1220:AT");
            double minT = days.get(3).getMinTemp();
            double maxT = days.get(3).getMaxTemp();

            temp.setText((minT+maxT)/2 + " \u2103");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // "Main" method of the controller
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDays(dayOne, 0);
        setDays(dayTwo, 1);
        setDays(dayThree, 2);
        setDays(dayFour, 3);
        setDays(dayFive, 4);

        try {
            ArrayList<Day> days = WeatherGetter.getWeatherJson("1220:AT");
            double minT = days.get(0).getMinTemp();
            double maxT = days.get(0).getMaxTemp();

            temp.setText((minT+maxT)/2 + " \u2103");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
