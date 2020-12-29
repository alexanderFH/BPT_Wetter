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
        dayOne.setText(dayOne2.getText());
        if(days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMinTemp();
            double maxT = days.get(0).getMaxTemp();
            temp.setText((minT+maxT)/2 + " \u2103");
        } else {
            temp.setText(days.get(0).getCurrentTemp() + " \u2103");
        }
        minTemp.setText("minimale Temperatur: " + days.get(0).getMinTemp() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(0).getMaxTemp() + " \u2103");
        //detail.setText(days.get(0).getNarrative());
        changeImage(0, days.get(0).getCurrentTemp());

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
        minTemp.setText("minimale Temperatur: " + days.get(1).getMinTemp() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(1).getMaxTemp() + " \u2103");
        changeImage(1, days.get(1).getCurrentTemp());

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
        minTemp.setText("minimale Temperatur: " + days.get(2).getMinTemp() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(2).getMaxTemp() + " \u2103");
        changeImage(2, days.get(2).getCurrentTemp());
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
        minTemp.setText("minimale Temperatur: " + days.get(3).getMinTemp() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(3).getMaxTemp() + " \u2103");
        changeImage(3, days.get(3).getCurrentTemp());
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
        minTemp.setText("minimale Temperatur: " + days.get(4).getMinTemp() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(4).getMaxTemp() + " \u2103");
        changeImage(4, days.get(4).getCurrentTemp());
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
            nextDayTemp1.setText((minT+maxT)/2 + " \u2103");

        } else {
            temp.setText(days.get(0).getCurrentTemp() + " \u2103");
            nextDayTemp1.setText(days.get(0).getCurrentTemp() + " \u2103");
        }

        /**folgendes wird eventuell durch eine schleife/switch o.Ä. ersetzt...*/

        if(days.get(1).getCurrentTemp() == -999) {
            double minT = days.get(1).getMinTemp();
            double maxT = days.get(1).getMaxTemp();
            nextDayTemp2.setText((minT+maxT)/2 + " \u2103");
        } else {
            nextDayTemp2.setText(days.get(1).getCurrentTemp() + " \u2103");
        }

        if(days.get(2).getCurrentTemp() == -999) {
            double minT = days.get(2).getMinTemp();
            double maxT = days.get(2).getMaxTemp();
            nextDayTemp3.setText((minT+maxT)/2 + " \u2103");
        } else {
            nextDayTemp3.setText(days.get(2).getCurrentTemp() + " \u2103");
        }

        if(days.get(3).getCurrentTemp() == -999) {
            double minT = days.get(3).getMinTemp();
            double maxT = days.get(3).getMaxTemp();
            nextDayTemp4.setText((minT+maxT)/2 + " \u2103");
        } else {
            nextDayTemp4.setText(days.get(3).getCurrentTemp() + " \u2103");
        }

        if(days.get(4).getCurrentTemp() == -999) {
            double minT = days.get(4).getMinTemp();
            double maxT = days.get(4).getMaxTemp();
            nextDayTemp5.setText((minT+maxT)/2 + " \u2103");
        } else {
            nextDayTemp5.setText(days.get(4).getCurrentTemp() + " \u2103");
        }

        minTemp.setText("minimale Temperatur: " + days.get(0).getMinTemp() + " \u2103");
        maxTemp.setText("maximale Temperatur: " + days.get(0).getMaxTemp() + " \u2103");
        //detail.setText(days.get(0).getNarrative());


        changeImage(1, days.get(1).getCurrentTemp());
        changeImage(2, days.get(2).getCurrentTemp());
        changeImage(3, days.get(3).getCurrentTemp());
        changeImage(4, days.get(4).getCurrentTemp());
        changeImage(0, days.get(0).getCurrentTemp());
    }
}
