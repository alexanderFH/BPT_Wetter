package Frontend;

import Backbone.Day;
import Backbone.WeatherGetter;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    private static final DateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.yyyy");
    @FXML
    protected static String unit = " \u2103";
    Date currentDate = null;
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
    private Label daySix;
    @FXML
    private Label temp;
    @FXML
    private Label minTemp;
    @FXML
    private Label maxTemp;
    @FXML
    private Label rain;
    @FXML
    private Label humidity;
    @FXML
    private Label sunrise;
    @FXML
    private Label sunset;
    @FXML
    private Label moonphase;
    @FXML
    private Label feelsLike;
    @FXML
    private ImageView image;  // Displays "Main Image"
    @FXML
    private ImageView imageNextDay1;  // Icon Box 1
    @FXML
    private ImageView imageNextDay2;  // Icon Box 2
    @FXML
    private ImageView imageNextDay3;  // Icon Box 3
    @FXML
    private ImageView imageNextDay4;  // icon Box 4
    @FXML
    private ImageView imageNextDay5;  // Icon Box 5
    @FXML
    private ImageView imageNextDay6;  // Icon Box 5
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
    @FXML
    private Label nextDayTemp6;
    @FXML
    private Label location;
    private ArrayList<Day> days;
    private boolean bp1Pressed;
    private boolean bp2Pressed;
    private boolean bp3Pressed;
    private boolean bp4Pressed;
    private boolean bp5Pressed;
    private boolean bp6Pressed;
    @FXML
    private BorderPane mainBorderPane;
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
    @FXML
    private BorderPane bp6;
    @FXML
    private Pane weatherInfo;
    @FXML
    private HBox nextWeatherInfo;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu refreshMenu;

    private boolean labelVisible;


    /**
     * Initializes the controller Class for MainWindow
     *
     * @param location as URL
     * @param resources as ResourceBundle
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Settings.mainWindow = this;
        refresh(refreshMenu);
        start();
    }


    /**
     * Starts a method when needed
     */
    protected void start() {

        if (Settings.declareUnit) {
            unit = " \u2103";
            days = WeatherGetter.getWeatherJson(Settings.plz, Settings.country, true);
        } else {
            unit = " \u2109";
            days = WeatherGetter.getWeatherJson(Settings.plz, Settings.country, false);
        }

        labelVisible = true;

        currentDate = new Date();
        displayDate.setText(DATEFORMAT.format(currentDate));

        dayOne.setText(days.get(0).getDayName());
        dayOne2.setText(days.get(0).getDayName());
        dayTwo.setText(days.get(1).getDayName());
        dayThree.setText(days.get(2).getDayName());
        dayFour.setText(days.get(3).getDayName());
        dayFive.setText(days.get(4).getDayName());
        daySix.setText(days.get(5).getDayName());

        location.setPadding(new Insets(0, 0, 0, 15));
        location.setText(days.get(0).getLocation());
        setCurrentTempLabel();
        minTemp.setText("minimale Temperatur: " + days.get(0).getMin_temp() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(0).getMax_temp() + unit);
        setRainLabel(0);
        if(labelVisible) {
            humidity.setVisible(true);
            feelsLike.setVisible(true);
            humidity.setText("Luftfeuchtigkeit: " + days.get(0).getHumidity() + "%");
            feelsLike.setText("Temperatur f\u00fchlt sich\n an wie " + days.get(0).getFeelsLike() + unit);
        }

        sunrise.setText("Sonnenaufgang um:\n " + days.get(0).getSunrise() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(0).getSunset() + " Uhr");
        setMoonphaseLabel(0);


        if (Instant.now().getEpochSecond() > days.get(0).getLongSunrise() && Instant.now().getEpochSecond() < days.get(0).getLongSunset()) {
            changeImage(1);
            changeImage(2);
            changeImage(3);
            changeImage(4);
            changeImage(5);
            changeImage(0);
        } else {
            changeImageWhenNight(1, 1);
            changeImageWhenNight(2, 2);
            changeImageWhenNight(3, 3);
            changeImageWhenNight(4, 4);
            changeImageWhenNight(5, 5);
            changeImageWhenNight(0, 0);
        }


        bp1.getStyleClass().add("clickedOnPane");

        setFadeAnimation(weatherInfo, 1500);
        setFadeAnimation(nextWeatherInfo, 1500);


        bp2.getStyleClass().removeAll("clickedOnPane");
        bp3.getStyleClass().removeAll("clickedOnPane");
        bp4.getStyleClass().removeAll("clickedOnPane");
        bp5.getStyleClass().removeAll("clickedOnPane");
        bp6.getStyleClass().removeAll("clickedOnPane");
    }


    /**
     * next six methods change the weather informations
     * to a specific day
     *
     * @throws ParseException
     */
    @FXML
    private void changeToFirstDay() {

        displayDate.setText(DATEFORMAT.format(currentDate));

        dayOne.setText(dayOne2.getText());
        if (days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMin_temp();
            double maxT = days.get(0).getMax_temp();

            double roundedTemperature = Math.round((minT + maxT)/2 * 10) / 10.0;

            temp.setText(roundedTemperature + unit);
        } else {
            double roundedTemperature = Math.round(days.get(0).getCurrentTemp() * 10) / 10.0;
            temp.setText(roundedTemperature+ unit);
        }

        labelVisible = true;

        setRainLabel(0);
        minTemp.setText("minimale Temperatur: " + days.get(0).getMin_temp() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(0).getMax_temp() + unit);
        if(labelVisible) {
            humidity.setVisible(true);
            feelsLike.setVisible(true);
            humidity.setText("Luftfeuchtigkeit: " + days.get(0).getHumidity() + "%");
            feelsLike.setText("Temperatur f\u00fchlt sich\n an wie " + days.get(0).getFeelsLike() + unit);
        }
        sunrise.setText("Sonnenaufgang um:\n " + days.get(0).getSunrise() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(0).getSunset() + " Uhr");
        setMoonphaseLabel(0);
        //detail.setText(days.get(0).getNarrative());
        if (Instant.now().getEpochSecond() > days.get(0).getLongSunrise() && Instant.now().getEpochSecond() <
                days.get(0).getLongSunset())
            changeImage(0);
        else
            changeImageWhenNight(0, 0);

        setButtonHighlightFalse();
        bp1Pressed = true;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDayTwo() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);

        Date currentDate = c.getTime();

        displayDate.setText(DATEFORMAT.format(currentDate));

        dayOne.setText(dayTwo.getText());
        if (days.get(1).getCurrentTemp() == -999) {
            double minT = days.get(1).getMin_temp();
            double maxT = days.get(1).getMax_temp();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(1).getCurrentTemp() + unit);
        }

        labelVisible = false;
        setRainLabel(1);
        minTemp.setText("minimale Temperatur: " + days.get(1).getMin_temp() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(1).getMax_temp() + unit);
        if(!labelVisible) {
            humidity.setVisible(false);
            feelsLike.setVisible(false);
        }
        sunrise.setText("Sonnenaufgang um:\n " + days.get(1).getSunrise() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(1).getSunset() + " Uhr");
        setMoonphaseLabel(1);
        if (Instant.now().getEpochSecond() > days.get(0).getLongSunrise() && Instant.now().getEpochSecond() <
                days.get(0).getLongSunset())
            changeImage(1);
        else
            changeImageWhenNight(1, 1);

        setButtonHighlightFalse();
        bp2Pressed = true;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDayThree() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 2);

        Date currentDate = c.getTime();

        displayDate.setText(DATEFORMAT.format(currentDate));

        dayOne.setText(dayThree.getText());
        if (days.get(2).getCurrentTemp() == -999) {
            double minT = days.get(2).getMin_temp();
            double maxT = days.get(2).getMax_temp();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(2).getCurrentTemp() + unit);
        }

        setRainLabel(2);
        minTemp.setText("minimale Temperatur: " + days.get(2).getMin_temp() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(2).getMax_temp() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(2).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(2).getSunrise() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(2).getSunset() + " Uhr");
        setMoonphaseLabel(2);
        feelsLike.setText("Temperatur f\u00fchlt sich\n an wie " + days.get(2).getFeelsLike() + unit);
        if (Instant.now().getEpochSecond() > days.get(0).getLongSunrise() && Instant.now().getEpochSecond() <
                days.get(0).getLongSunset())
            changeImage(2);
        else
            changeImageWhenNight(2, 2);

        setButtonHighlightFalse();
        bp3Pressed = true;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDayFour() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 3);

        Date currentDate = c.getTime();

        displayDate.setText(DATEFORMAT.format(currentDate));

        dayOne.setText(dayFour.getText());
        if (days.get(3).getCurrentTemp() == -999) {
            double minT = days.get(3).getMin_temp();
            double maxT = days.get(3).getMax_temp();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(3).getCurrentTemp() + unit);
        }

        setRainLabel(3);
        minTemp.setText("minimale Temperatur: " + days.get(3).getMin_temp() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(3).getMax_temp() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(3).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(3).getSunrise() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(3).getSunset() + " Uhr");
        setMoonphaseLabel(3);
        feelsLike.setText("Temperatur f\u00fchlt sich\n an wie " + days.get(3).getFeelsLike() + unit);
        if (Instant.now().getEpochSecond() > days.get(0).getLongSunrise() && Instant.now().getEpochSecond() <
                days.get(0).getLongSunset())
            changeImage(3);
        else
            changeImageWhenNight(3, 3);

        setButtonHighlightFalse();
        bp4Pressed = true;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDayFive() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 4);

        Date currentDate = c.getTime();

        displayDate.setText(DATEFORMAT.format(currentDate));

        dayOne.setText(dayFive.getText());
        if (days.get(4).getCurrentTemp() == -999) {
            double minT = days.get(4).getMin_temp();
            double maxT = days.get(4).getMax_temp();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(4).getCurrentTemp() + unit);
        }

        setRainLabel(4);
        minTemp.setText("minimale Temperatur: " + days.get(4).getMin_temp() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(4).getMax_temp() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(4).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(4).getSunrise() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(4).getSunset() + " Uhr");
        setMoonphaseLabel(4);
        feelsLike.setText("Temperatur f\u00fchlt sich\n an wie " + days.get(4).getFeelsLike() + unit);
        if (Instant.now().getEpochSecond() > days.get(0).getLongSunrise() && Instant.now().getEpochSecond() <
                days.get(0).getLongSunset())
            changeImage(4);
        else
            changeImageWhenNight(4, 4);

        setButtonHighlightFalse();
        bp5Pressed = true;
        borderPanePressed();
        setFadeAnimation(weatherInfo, 1000);
    }



    @FXML
    private void changeDaySix() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 5);

        Date currentDate = c.getTime();

        displayDate.setText(DATEFORMAT.format(currentDate));

        dayOne.setText(daySix.getText());
        if (days.get(5).getCurrentTemp() == -999) {
            double minT = days.get(5).getMin_temp();
            double maxT = days.get(5).getMax_temp();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(5).getCurrentTemp() + unit);
        }

        setRainLabel(5);
        minTemp.setText("minimale Temperatur: " + days.get(5).getMin_temp() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(5).getMax_temp() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(5).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(5).getSunrise() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(5).getSunset() + " Uhr");
        setMoonphaseLabel(5);
        feelsLike.setText("Temperatur f\u00fchlt sich\n an wie " + days.get(5).getFeelsLike() + unit);
        if (Instant.now().getEpochSecond() > days.get(0).getLongSunrise() && Instant.now().getEpochSecond() <
                days.get(0).getLongSunset())
            changeImage(5);
        else
            changeImageWhenNight(5, 5);

        setButtonHighlightFalse();
        bp6Pressed = true;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    private void setButtonHighlightFalse() {
        bp1Pressed = false;
        bp2Pressed = false;
        bp3Pressed = false;
        bp4Pressed = false;
        bp5Pressed = false;
        bp6Pressed = false;
    }

    /**
     * Change image based on narrative
     *
     * @param currentDay currentDay as int to change the right image
     */
    @FXML
    private void changeImage(int currentDay) {


        // Declares Images for every Weather icon
        Image sunny = new Image("/img/sunny.png");
        Image cloudy = new Image("/img/clouds.png");
        Image cloudyRain = new Image("/img/cloudy_rain.png");
        Image thunderstorm = new Image("/img/thunderstorm.png");
        Image snowy = new Image("/img/snowy.png");

        // Checks which Image is currently displayed
        String checkImage = "";


        // Sets image based on current temp/narrative

        String nararative = days.get(currentDay).getNarrative().toLowerCase();
        if (nararative.contains("schauer".toLowerCase())) {
            image.setImage(cloudyRain);
            checkImage = "cloudyRain";
            mainBorderPane.getStyleClass().removeAll("myBorderPaneCloudy", "myBorderPaneCloudySunny",
                    "myBorderPaneSnowy", "myBorderPaneThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneCloudyRain");

        } else if (nararative.contains("klar".toLowerCase())) {
            image.setImage(sunny);
            checkImage = "sunny";
            mainBorderPane.getStyleClass().removeAll("myBorderPaneCloudy", "myBorderPaneCloudyRain",
                    "myBorderPaneSnowy", "myBorderPaneThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneSunny");


        } else if (nararative.contains("bedeckt".toLowerCase())) {
            image.setImage(cloudy);
            checkImage = "cloudy";
            mainBorderPane.getStyleClass().removeAll("myBorderPaneSunny", "myBorderPaneCloudyRain",
                    "myBorderPaneSnowy", "myBorderPaneThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneCloudy");


        } else if (nararative.contains("Schnee".toLowerCase())) {
            image.setImage(snowy);
            checkImage = "snowy";
            mainBorderPane.getStyleClass().removeAll("myBorderPaneCloudy", "myBorderPaneCloudyRain",
                    "myBorderPaneSunny", "myBorderPaneThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneSnowy");


        } else if (nararative.contains("gewitter".toLowerCase())) {
            image.setImage(thunderstorm);
            checkImage = "thunderstorm";

            mainBorderPane.getStyleClass().removeAll("myBorderPaneSunny", "myBorderPaneCloudyRain",
                    "myBorderPaneSnowy", "myBorderPaneCloudy");
            mainBorderPane.getStyleClass().add("myBorderPaneThunder");


        } else {
            image.setImage(cloudy);
            checkImage = "cloudy";
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
                    case 5 -> imageNextDay6.setImage(cloudyRain);
                }
                break;
            case "cloudy":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(cloudy);
                    case 1 -> imageNextDay2.setImage(cloudy);
                    case 2 -> imageNextDay3.setImage(cloudy);
                    case 3 -> imageNextDay4.setImage(cloudy);
                    case 4 -> imageNextDay5.setImage(cloudy);
                    case 5 -> imageNextDay6.setImage(cloudy);
                }
                break;
            case "sunny":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(sunny);
                    case 1 -> imageNextDay2.setImage(sunny);
                    case 2 -> imageNextDay3.setImage(sunny);
                    case 3 -> imageNextDay4.setImage(sunny);
                    case 4 -> imageNextDay5.setImage(sunny);
                    case 5 -> imageNextDay6.setImage(sunny);
                }
                break;
            case "thunderstorm":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(thunderstorm);
                    case 1 -> imageNextDay2.setImage(thunderstorm);
                    case 2 -> imageNextDay3.setImage(thunderstorm);
                    case 3 -> imageNextDay4.setImage(thunderstorm);
                    case 4 -> imageNextDay5.setImage(thunderstorm);
                    case 5 -> imageNextDay6.setImage(thunderstorm);
                }
                break;
            case "snowy":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(snowy);
                    case 1 -> imageNextDay2.setImage(snowy);
                    case 2 -> imageNextDay3.setImage(snowy);
                    case 3 -> imageNextDay4.setImage(snowy);
                    case 4 -> imageNextDay5.setImage(snowy);
                    case 5 -> imageNextDay6.setImage(snowy);
                }
        }

    }


    /**
     * when the current time reaches sunset the app
     * switches to night mode
     *
     * @param currentDay
     * @param index index of the day as int
     * @throws ParseException
     */
    public void changeImageWhenNight(int currentDay, int index) {
        String mondphase = "";

        Image vollmond = new Image("/img/moonphases/fullmoon.png");
        Image abHalbmond = new Image("/img/moonphases/abHalbmond.png");
        Image abSichelmond = new Image("/img/moonphases/abSichelmond.png");
        Image abDreiviertel = new Image("/img/moonphases/abDreiviertel-Mond.png");
        Image neumond = new Image("/img/moonphases/newmoon.png");
        Image zuDreiviertel = new Image("/img/moonphases/zuDreiviertel-Mond.png");
        Image zuHalbmond = new Image("/img/moonphases/zuHalbmond.png");
        Image zuSichelmond = new Image("/img/moonphases/zuSichelmond.png");

        //normally there is: no.isAfter(zo)
        // but for further implementation its set to no.isBefore(zo)

        String nararative = days.get(currentDay).getNarrative().toLowerCase();
        if (nararative.contains("schauer".toLowerCase())) {
            mainBorderPane.getStyleClass().removeAll("myBorderPaneNight", "myBorderPaneNightCloudy",
                    "myBorderPaneNightSnowy", "myBorderPaneNightThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneNightRain");
        } else if (nararative.contains("klar".toLowerCase())) {
            mainBorderPane.getStyleClass().removeAll("myBorderPaneNightRain", "myBorderPaneNightCloudy",
                    "myBorderPaneNightSnowy", "myBorderPaneNightThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneNight");
        } else if (nararative.contains("bedeckt".toLowerCase())) {
            mainBorderPane.getStyleClass().removeAll("myBorderPaneNight", "myBorderPaneNightRain",
                    "myBorderPaneNightSnowy", "myBorderPaneNightThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneNightCloudy");
        } else if (nararative.contains("Schnee".toLowerCase())) {
            mainBorderPane.getStyleClass().removeAll("myBorderPaneNight", "myBorderPaneNightCloudy",
                    "myBorderPaneNightRain", "myBorderPaneNightThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneNightSnowy");
        } else if (nararative.contains("gewitter".toLowerCase())) {
            mainBorderPane.getStyleClass().removeAll("myBorderPaneNight", "myBorderPaneNightCloudy",
                    "myBorderPaneNightSnowy", "myBorderPaneNightRain");
            mainBorderPane.getStyleClass().add("myBorderPaneNightThunder");
        }

        menuBar.getStyleClass().removeAll("menuBar");
        menuBar.getStyleClass().add("menuBarNight");

        switch (days.get(index).getMoonphase()) {
            case "LQ" -> {
                mondphase = "abHalbmond";
                image.setImage(abHalbmond);
            }
            case "F" -> {
                mondphase = "vollmond";
                image.setImage(vollmond);
            }
            case "WNG" -> {
                mondphase = "abDreiviertelmond";
                image.setImage(abDreiviertel);
            }
            case "WNC" -> {
                mondphase = "abSichelmond";
                image.setImage(abSichelmond);
            }
            case "WXC" -> {
                mondphase = "zuSichelmond";
                image.setImage(zuSichelmond);
            }
            case "FQ" -> {
                mondphase = "zuHalbmond";
                image.setImage(zuHalbmond);
            }
            case "WXG" -> {
                mondphase = "zuDreiviertelmond";
                image.setImage(zuDreiviertel);
            }
            case "N" -> {
                mondphase = "neumond";
                image.setImage(neumond);
            }
        }

        switch (mondphase) {
            case "abHalbmond":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(abHalbmond);
                    case 1 -> imageNextDay2.setImage(abHalbmond);
                    case 2 -> imageNextDay3.setImage(abHalbmond);
                    case 3 -> imageNextDay4.setImage(abHalbmond);
                    case 4 -> imageNextDay5.setImage(abHalbmond);
                    case 5 -> imageNextDay6.setImage(abHalbmond);
                }
                break;
            case "vollmond":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(vollmond);
                    case 1 -> imageNextDay2.setImage(vollmond);
                    case 2 -> imageNextDay3.setImage(vollmond);
                    case 3 -> imageNextDay4.setImage(vollmond);
                    case 4 -> imageNextDay5.setImage(vollmond);
                    case 5 -> imageNextDay6.setImage(vollmond);
                }
                break;
            case "abDreiviertelmond":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(abDreiviertel);
                    case 1 -> imageNextDay2.setImage(abDreiviertel);
                    case 2 -> imageNextDay3.setImage(abDreiviertel);
                    case 3 -> imageNextDay4.setImage(abDreiviertel);
                    case 4 -> imageNextDay5.setImage(abDreiviertel);
                    case 5 -> imageNextDay6.setImage(abDreiviertel);
                }
                break;
            case "abSichelmond":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(abSichelmond);
                    case 1 -> imageNextDay2.setImage(abSichelmond);
                    case 2 -> imageNextDay3.setImage(abSichelmond);
                    case 3 -> imageNextDay4.setImage(abSichelmond);
                    case 4 -> imageNextDay5.setImage(abSichelmond);
                    case 5 -> imageNextDay6.setImage(abSichelmond);
                }
                break;
            case "zuSichelmond":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(zuSichelmond);
                    case 1 -> imageNextDay2.setImage(zuSichelmond);
                    case 2 -> imageNextDay3.setImage(zuSichelmond);
                    case 3 -> imageNextDay4.setImage(zuSichelmond);
                    case 4 -> imageNextDay5.setImage(zuSichelmond);
                    case 5 -> imageNextDay6.setImage(zuSichelmond);
                }
                break;
            case "zuHalbmond":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(zuHalbmond);
                    case 1 -> imageNextDay2.setImage(zuHalbmond);
                    case 2 -> imageNextDay3.setImage(zuHalbmond);
                    case 3 -> imageNextDay4.setImage(zuHalbmond);
                    case 4 -> imageNextDay5.setImage(zuHalbmond);
                    case 5 -> imageNextDay6.setImage(zuHalbmond);
                }
                break;
            case "zuDreiviertelmond":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(zuDreiviertel);
                    case 1 -> imageNextDay2.setImage(zuDreiviertel);
                    case 2 -> imageNextDay3.setImage(zuDreiviertel);
                    case 3 -> imageNextDay4.setImage(zuDreiviertel);
                    case 4 -> imageNextDay5.setImage(zuDreiviertel);
                    case 5 -> imageNextDay6.setImage(zuDreiviertel);
                }
                break;
            case "neumond":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(neumond);
                    case 1 -> imageNextDay2.setImage(neumond);
                    case 2 -> imageNextDay3.setImage(neumond);
                    case 3 -> imageNextDay4.setImage(neumond);
                    case 4 -> imageNextDay5.setImage(neumond);
                    case 5 -> imageNextDay6.setImage(neumond);
                }
                break;
        }
    }


    /**
     * marks the the clicked day
     * by adding and removing css classes
     */
    public void borderPanePressed() {
        if (bp1Pressed) {
            bp1.getStyleClass().add("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
            bp6.getStyleClass().removeAll("clickedOnPane");
        }
        if (bp2Pressed) {
            bp2.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
            bp6.getStyleClass().removeAll("clickedOnPane");
        }
        if (bp3Pressed) {
            bp3.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
            bp6.getStyleClass().removeAll("clickedOnPane");
        }
        if (bp4Pressed) {
            bp4.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
            bp6.getStyleClass().removeAll("clickedOnPane");
        }
        if (bp5Pressed) {
            bp5.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
            bp6.getStyleClass().removeAll("clickedOnPane");
        }
        if (bp6Pressed) {
            bp6.getStyleClass().add("clickedOnPane");
            bp1.getStyleClass().removeAll("clickedOnPane");
            bp2.getStyleClass().removeAll("clickedOnPane");
            bp3.getStyleClass().removeAll("clickedOnPane");
            bp4.getStyleClass().removeAll("clickedOnPane");
            bp5.getStyleClass().removeAll("clickedOnPane");
        }
    }


    /**
     * gets the rain value from weather array
     * depending on the value the corresponding information will change
     *
     * @param index indicates which day should be set as int
     */
    public void setRainLabel(int index) {
        if (days.get(index).getRain() < 1) {
            rain.setText("Regen: leichter Regen moeglich");
        } else if (days.get(index).getRain() >= 1 && days.get(index).getRain() < 2) {
            rain.setText("Regen: Regen moeglich");
        } else if (days.get(index).getRain() >= 2 && days.get(index).getRain() < 3) {
            rain.setText("Regen: mittelstarker Regen moeglich");
        } else if (days.get(index).getRain() >= 3 && days.get(index).getRain() < 4) {
            rain.setText("Regen: starker Regen moeglich");
        } else if (days.get(index).getRain() >= 4) {
            rain.setText("Regen: starker Regen bis hinzu Hagel\n moeglich");
        }
    }


    /**
     * gets the current temperature from the weather array
     * checks if current temperature is a valid value
     * if not the average of min and max temperature is calculated
     */
    public void setCurrentTempLabel() {
        if (days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMin_temp();
            double maxT = days.get(0).getMax_temp();

            double roundedTemperature = Math.round((minT + maxT) / 2 * 10) / 10.0;

            temp.setText(roundedTemperature + unit);
            nextDayTemp1.setText(roundedTemperature + unit);

        } else {
            double roundedTemperature = Math.round(days.get(0).getCurrentTemp() * 10) / 10.0;

            temp.setText(roundedTemperature + unit);
            nextDayTemp1.setText(roundedTemperature + unit);
        }


        if (days.get(1).getCurrentTemp() == -999) {
            double minT = days.get(1).getMin_temp();
            double maxT = days.get(1).getMax_temp();
            nextDayTemp2.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp2.setText(days.get(1).getCurrentTemp() + unit);
        }

        if (days.get(2).getCurrentTemp() == -999) {
            double minT = days.get(2).getMin_temp();
            double maxT = days.get(2).getMax_temp();
            nextDayTemp3.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp3.setText(days.get(2).getCurrentTemp() + unit);
        }

        if (days.get(3).getCurrentTemp() == -999) {
            double minT = days.get(3).getMin_temp();
            double maxT = days.get(3).getMax_temp();
            nextDayTemp4.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp4.setText(days.get(3).getCurrentTemp() + unit);
        }

        if (days.get(4).getCurrentTemp() == -999) {
            double minT = days.get(4).getMin_temp();
            double maxT = days.get(4).getMax_temp();
            nextDayTemp5.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp5.setText(days.get(4).getCurrentTemp() + unit);
        }

        if (days.get(5).getCurrentTemp() == -999) {
            double minT = days.get(5).getMin_temp();
            double maxT = days.get(5).getMax_temp();
            nextDayTemp6.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp6.setText(days.get(5).getCurrentTemp() + unit);
        }
    }

    public void setMoonphaseLabel(int index) {
        switch (days.get(index).getMoonphase()) {
            case "WNG":
                moonphase.setText("Mondphase:\nabnehmender Dreiviertelmond");
                break;
            case "WXG":
                moonphase.setText("Mondphase:\nzunehmender Dreiviertelmond");
                break;
            case "F":
                moonphase.setText("Mondphase:\nVollmond");
                break;
            case "N":
                moonphase.setText("Mondphase:\nNeumond");
                break;
            case "WNC":
                moonphase.setText("Mondphase:\nabnehmender Sichelmond");
                break;
            case "WXC":
                moonphase.setText("Mondphase:\nzunehmender Sichelmond");
                break;
            case "LQ":
                moonphase.setText("Mondphase:\nabnehmender Halbmond");
                break;
            case "FQ":
                moonphase.setText("Mondphase:\nzunehmender Halbmond");
                break;
        }
    }


    /**
     * this method opens the settings window
     */
    public void openSettings() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Settings/settings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setScene(new Scene(root, 450, 300));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * closing the app by returning exit code 0
     */
    public void closeWindow() {
        System.exit(0);
    }


    /**
     * adds a fade transition when the app is starting
     * or when you switch days
     *
     * @param p on which pane the transition should be as pane
     * @param ms how long the transition should be as int
     */
    public void setFadeAnimation(Pane p, int ms) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(ms), p);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

    /**
     * Adds hidden menuitem to menu (javafx need atleast 1 menuitem to fire an event)
     * @param menu
     */
    @FXML
    public void refresh(Menu menu) {
        final MenuItem menuItem = new MenuItem();
        menu.getItems().add(menuItem);
        menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
        menu.addEventHandler(Menu.ON_SHOWING, event -> menu.fire());
    }

    /**
     * Event for the refresh Button
     * @param actionEvent
     */
    public void refresh(ActionEvent actionEvent) {
        start();
    }
}
