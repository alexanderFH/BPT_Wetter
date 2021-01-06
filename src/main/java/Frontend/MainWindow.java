package Frontend;

import Backbone.Day;
import Backbone.WeatherGetter;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @FXML
    protected static String unit = " \u2103";
    Date currentDate = null;
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
    private Label daySix;
    @FXML
    private Label temp;
    @FXML
    private Label minTemp;
    @FXML
    private Label maxTemp;
    @FXML
    private Label detail;
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
    private ImageView imageNextDay6;  // Icon Box 5
    @FXML
    private Image imgNextDay6;
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
    private Pane moonIcon;
    @FXML
    private HBox nextWeatherInfo;
    @FXML
    private VBox leftInfos;
    @FXML
    private VBox centerInfos;
    @FXML
    private VBox rightInfos;
    @FXML
    private MenuBar menuBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Settings.mainWindow = this;
        start();
    }

    protected void start() {
        if (Settings.declareUnit) {
            unit = " \u2103";
            days = WeatherGetter.getWeatherJson("1220", "AT", true);
        } else {
            unit = " \u2109";
            days = WeatherGetter.getWeatherJson("1220", "AT", false);
        }

        currentDate = new Date();
        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(days.get(0).getDayName());
        dayOne2.setText(days.get(0).getDayName());
        dayTwo.setText(days.get(1).getDayName());
        dayThree.setText(days.get(2).getDayName());
        dayFour.setText(days.get(3).getDayName());
        dayFive.setText(days.get(4).getDayName());
        daySix.setText(days.get(5).getDayName());

        setCurrentTempLabel();
        minTemp.setText("minimale Temperatur: " + days.get(0).getMIN_TEMP() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(0).getMAX_TEMP() + unit);
        setRainLabel(0);
        humidity.setText("Luftfeuchtigkeit: " + days.get(0).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(0).getSUNRISE() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(0).getSUNSET() + " Uhr");
        moonphase.setText("Mondphase:\n " + days.get(0).getMoonphase());
        feelsLike.setText("Temperatur fuehlt sich\n an wie " + days.get(0).getFeelsLike() + unit);

        changeImage(1, days.get(1).getCurrentTemp());
        changeImage(2, days.get(2).getCurrentTemp());
        changeImage(3, days.get(3).getCurrentTemp());
        changeImage(4, days.get(4).getCurrentTemp());
        changeImage(5, days.get(5).getCurrentTemp());
        changeImage(0, days.get(0).getCurrentTemp());

        bp1.getStyleClass().add("clickedOnPane");

        setFadeAnimation(weatherInfo, 1500);
        setFadeAnimation(nextWeatherInfo, 1500);

        try {
            changeIconWhenNight(0, 0);
            changeIconWhenNight(1, 1);
            changeIconWhenNight(2, 2);
            changeIconWhenNight(3, 3);
            changeIconWhenNight(4, 4);
            changeIconWhenNight(5, 5);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void changeToFirstDay() throws ParseException {

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayOne2.getText());
        if (days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMIN_TEMP();
            double maxT = days.get(0).getMAX_TEMP();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(0).getCurrentTemp() + unit);
        }
        setRainLabel(0);
        minTemp.setText("minimale Temperatur: " + days.get(0).getMIN_TEMP() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(0).getMAX_TEMP() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(0).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(0).getSUNRISE() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(0).getSUNSET() + " Uhr");
        moonphase.setText("Mondphase:\n " + days.get(0).getMoonphase());
        feelsLike.setText("Temperatur fuehlt sich\n an wie " + days.get(0).getFeelsLike() + unit);
        //detail.setText(days.get(0).getNarrative());
        changeImage(0, days.get(0).getCurrentTemp());
        changeIconWhenNight(0, 0);

        bp1Pressed = true;
        bp2Pressed = false;
        bp3Pressed = false;
        bp4Pressed = false;
        bp5Pressed = false;
        bp6Pressed = false;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDayTwo() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 1);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayTwo.getText());
        if (days.get(1).getCurrentTemp() == -999) {
            double minT = days.get(1).getMIN_TEMP();
            double maxT = days.get(1).getMAX_TEMP();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(1).getCurrentTemp() + unit);
        }

        setRainLabel(1);
        minTemp.setText("minimale Temperatur: " + days.get(1).getMIN_TEMP() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(1).getMAX_TEMP() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(1).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(1).getSUNRISE() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(1).getSUNSET() + " Uhr");
        moonphase.setText("Mondphase:\n " + days.get(1).getMoonphase());
        feelsLike.setText("Temperatur fuehlt sich\n an wie " + days.get(1).getFeelsLike() + unit);
        changeImage(1, days.get(1).getCurrentTemp());
        changeIconWhenNight(1, 1);

        bp2Pressed = true;
        bp1Pressed = false;
        bp4Pressed = false;
        bp3Pressed = false;
        bp5Pressed = false;
        bp6Pressed = false;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDayThree() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 2);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayThree.getText());
        if (days.get(2).getCurrentTemp() == -999) {
            double minT = days.get(2).getMIN_TEMP();
            double maxT = days.get(2).getMAX_TEMP();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(2).getCurrentTemp() + unit);
        }

        setRainLabel(2);
        minTemp.setText("minimale Temperatur: " + days.get(2).getMIN_TEMP() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(2).getMAX_TEMP() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(2).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(2).getSUNRISE() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(2).getSUNSET() + " Uhr");
        moonphase.setText("Mondphase:\n " + days.get(2).getMoonphase());
        feelsLike.setText("Temperatur fuehlt sich\n an wie " + days.get(2).getFeelsLike() + unit);
        changeImage(2, days.get(2).getCurrentTemp());
        changeIconWhenNight(2, 2);

        bp3Pressed = true;
        bp1Pressed = false;
        bp2Pressed = false;
        bp4Pressed = false;
        bp5Pressed = false;
        bp6Pressed = false;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDayFour() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 3);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayFour.getText());
        if (days.get(3).getCurrentTemp() == -999) {
            double minT = days.get(3).getMIN_TEMP();
            double maxT = days.get(3).getMAX_TEMP();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(3).getCurrentTemp() + unit);
        }

        setRainLabel(3);
        minTemp.setText("minimale Temperatur: " + days.get(3).getMIN_TEMP() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(3).getMAX_TEMP() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(3).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(3).getSUNRISE() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(3).getSUNSET() + " Uhr");
        moonphase.setText("Mondphase:\n " + days.get(3).getMoonphase());
        feelsLike.setText("Temperatur fuehlt sich\n an wie " + days.get(3).getFeelsLike() + unit);
        changeImage(3, days.get(3).getCurrentTemp());
        changeIconWhenNight(3, 3);

        bp4Pressed = true;
        bp1Pressed = false;
        bp2Pressed = false;
        bp3Pressed = false;
        bp5Pressed = false;
        bp6Pressed = false;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDayFive() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 4);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(dayFive.getText());
        if (days.get(4).getCurrentTemp() == -999) {
            double minT = days.get(4).getMIN_TEMP();
            double maxT = days.get(4).getMAX_TEMP();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(4).getCurrentTemp() + unit);
        }

        setRainLabel(4);
        minTemp.setText("minimale Temperatur: " + days.get(4).getMIN_TEMP() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(4).getMAX_TEMP() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(4).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(4).getSUNRISE() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(4).getSUNSET() + " Uhr");
        moonphase.setText("Mondphase:\n " + days.get(4).getMoonphase());
        feelsLike.setText("Temperatur fuehlt sich\n an wie " + days.get(4).getFeelsLike() + unit);
        changeImage(4, days.get(4).getCurrentTemp());
        changeIconWhenNight(4, 4);

        bp5Pressed = true;
        bp1Pressed = false;
        bp2Pressed = false;
        bp3Pressed = false;
        bp4Pressed = false;
        bp6Pressed = false;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
    }

    @FXML
    private void changeDaySix() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, 5);

        Date currentDate = c.getTime();

        displayDate.setText(dateFormat.format(currentDate));

        dayOne.setText(daySix.getText());
        if (days.get(5).getCurrentTemp() == -999) {
            double minT = days.get(5).getMIN_TEMP();
            double maxT = days.get(5).getMAX_TEMP();
            temp.setText((minT + maxT) / 2 + unit);
        } else {
            temp.setText(days.get(5).getCurrentTemp() + unit);
        }

        setRainLabel(5);
        minTemp.setText("minimale Temperatur: " + days.get(5).getMIN_TEMP() + unit);
        maxTemp.setText("maximale Temperatur: " + days.get(5).getMAX_TEMP() + unit);
        humidity.setText("Luftfeuchtigkeit: " + days.get(5).getHumidity() + "%");
        sunrise.setText("Sonnenaufgang um:\n " + days.get(5).getSUNRISE() + " Uhr");
        sunset.setText("Sonnenuntergang um:\n " + days.get(5).getSUNSET() + " Uhr");
        moonphase.setText("Mondphase:\n " + days.get(5).getMoonphase());
        feelsLike.setText("Temperatur fuehlt sich\n an wie " + days.get(5).getFeelsLike() + unit);
        changeImage(5, days.get(5).getCurrentTemp());
        changeIconWhenNight(5, 5);

        bp6Pressed = true;
        bp1Pressed = false;
        bp2Pressed = false;
        bp3Pressed = false;
        bp4Pressed = false;
        bp5Pressed = false;
        borderPanePressed();

        setFadeAnimation(weatherInfo, 1000);
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
        Image snowy = new Image("/img/snowy.png");

        // Checks which Image is currently displayed
        String checkImage = "";


        // Sets image based on current temp/narrative
        if (days.get(currentDay).getNarrative().toLowerCase().contains("schauer".toLowerCase())) {
            image.setImage(cloudyRain);
            checkImage = "cloudyRain";

            mainBorderPane.getStyleClass().removeAll("myBorderPaneCloudy", "myBorderPaneCloudySunny", "myBorderPaneSnowy", "myBorderPaneThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneCloudyRain");

        } else if (days.get(currentDay).getNarrative().toLowerCase().contains("klar".toLowerCase())) {
            image.setImage(sunny);
            checkImage = "sunny";
            mainBorderPane.getStyleClass().removeAll("myBorderPaneCloudy", "myBorderPaneCloudyRain", "myBorderPaneSnowy", "myBorderPaneThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneSunny");

        } else if (days.get(currentDay).getNarrative().toLowerCase().contains("bedeckt".toLowerCase())) {
            image.setImage(cloudy);
            checkImage = "cloudy";
            mainBorderPane.getStyleClass().removeAll("myBorderPaneSunny", "myBorderPaneCloudyRain", "myBorderPaneSnowy", "myBorderPaneThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneCloudy");


        } else if (days.get(currentDay).getNarrative().toLowerCase().contains("Schnee".toLowerCase())) {
            image.setImage(snowy);
            checkImage = "snowy";
            mainBorderPane.getStyleClass().removeAll("myBorderPaneCloudy", "myBorderPaneCloudyRain", "myBorderPaneSunny", "myBorderPaneThunder");
            mainBorderPane.getStyleClass().add("myBorderPaneSnowy");


        } else if (days.get(currentDay).getNarrative().toLowerCase().contains("gewitter".toLowerCase())) {
            image.setImage(thunderstorm);
            checkImage = "thunderstorm";

            mainBorderPane.getStyleClass().removeAll("myBorderPaneSunny", "myBorderPaneCloudyRain", "myBorderPaneSnowy", "myBorderPaneCloudy");
            mainBorderPane.getStyleClass().add("myBorderPaneThunder");
        } else {
            image.setImage(cloudy);
            checkImage = "cloudy";
        }
        /*if (0 < currentTemp && currentTemp < 9) {
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
        } */

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

    public void changeIconWhenNight(int currentDay, int index) throws ParseException {

        Date date1 = new SimpleDateFormat("HH:mm:ss").parse(days.get(index).getSUNSET());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        long seconds = calendar.get(Calendar.SECOND);

        ZonedDateTime zo = ZonedDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneId.systemDefault());
        ZonedDateTime no = ZonedDateTime.now();
        String mondphase = "";

        Image vollmond = new Image("/img/moonphases/vollmond.png");
        Image abHalbmond = new Image("/img/moonphases/abHalbmond.png");
        Image abSichelmond = new Image("/img/moonphases/abSichelmond.png");
        Image abDreiviertel = new Image("/img/moonphases/abDreiViertelmond.png");
        Image neumond = new Image("/img/moonphases/neumond.png");
        Image zuDreiviertel = new Image("/img/moonphases/zuDreiViertelmond.png");
        Image zuHalbmond = new Image("/img/moonphases/zuHalbmond.png");
        Image zuSichelmond = new Image("/img/moonphases/zuSichelmond.png");

        //normally there is: no.isAfter(zo)
        // but for further implementation its set to no.isBefore(zo)
        if (no.isBefore(zo)) {
            mainBorderPane.getStyleClass().removeAll("myBorderPane");
            mainBorderPane.getStyleClass().add("myBorderPaneNight");

            menuBar.getStyleClass().removeAll("menuBar");
            menuBar.getStyleClass().add("menuBarNight");

            System.out.println("TimeSpan1 value is greater");
            if (days.get(index).getMoonphase().equals("abnehmender Halbmond")) {
                mondphase = "abHalbmond";
                image.setImage(abHalbmond);
            } else if (days.get(index).getMoonphase().equals("abnehmender Sichelmond")) {
                mondphase = "abSichelmond";
                image.setImage(abSichelmond);
            } else if (days.get(index).getMoonphase().equals("abnehmender Dreiviertelmond")) {
                mondphase = "abDreiviertelmond";
                image.setImage(abDreiviertel);
            } else if (days.get(index).getMoonphase().equals("letztes Viertel")) {
                mondphase = "letztesViertel";
                image.setImage(vollmond);
            } else if (days.get(index).getMoonphase().equals("zunehmender Sichelmond")) {
                mondphase = "zuSichelmond";
                image.setImage(zuSichelmond);
            } else if (days.get(index).getMoonphase().equals("zunehmender Halbmond")) {
                mondphase = "zuHalbmond";
                image.setImage(zuHalbmond);
            } else if (days.get(index).getMoonphase().equals("zunehmender Dreiviertelmond")) {
                mondphase = "zuDreiviertelmond";
                image.setImage(zuDreiviertel);
            } else if (days.get(index).getMoonphase().equals("neumond")) {
                mondphase = "neumond";
                image.setImage(neumond);
            }
        } else {
            System.out.println("TimeSpan2 value is greater");
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
            case "letztesViertel":
                switch (currentDay) {
                    case 0 -> imageNextDay1.setImage(vollmond);
                    case 1 -> imageNextDay2.setImage(vollmond);
                    case 2 -> imageNextDay3.setImage(vollmond);
                    case 3 -> imageNextDay4.setImage(vollmond);
                    case 4 -> imageNextDay5.setImage(vollmond);
                    case 5 -> imageNextDay6.setImage(vollmond);
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

    public void setCurrentTempLabel() {
        if (days.get(0).getCurrentTemp() == -999) {
            double minT = days.get(0).getMIN_TEMP();
            double maxT = days.get(0).getMAX_TEMP();
            temp.setText((minT + maxT) / 2 + unit);
            nextDayTemp1.setText((minT + maxT) / 2 + unit);

        } else {
            temp.setText(days.get(0).getCurrentTemp() + unit);
            nextDayTemp1.setText(days.get(0).getCurrentTemp() + unit);
        }

        if (days.get(1).getCurrentTemp() == -999) {
            double minT = days.get(1).getMIN_TEMP();
            double maxT = days.get(1).getMAX_TEMP();
            nextDayTemp2.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp2.setText(days.get(1).getCurrentTemp() + unit);
        }

        if (days.get(2).getCurrentTemp() == -999) {
            double minT = days.get(2).getMIN_TEMP();
            double maxT = days.get(2).getMAX_TEMP();
            nextDayTemp3.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp3.setText(days.get(2).getCurrentTemp() + unit);
        }

        if (days.get(3).getCurrentTemp() == -999) {
            double minT = days.get(3).getMIN_TEMP();
            double maxT = days.get(3).getMAX_TEMP();
            nextDayTemp4.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp4.setText(days.get(3).getCurrentTemp() + unit);
        }

        if (days.get(4).getCurrentTemp() == -999) {
            double minT = days.get(4).getMIN_TEMP();
            double maxT = days.get(4).getMAX_TEMP();
            nextDayTemp5.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp5.setText(days.get(4).getCurrentTemp() + unit);
        }

        if (days.get(5).getCurrentTemp() == -999) {
            double minT = days.get(5).getMIN_TEMP();
            double maxT = days.get(5).getMAX_TEMP();
            nextDayTemp6.setText((minT + maxT) / 2 + unit);
        } else {
            nextDayTemp6.setText(days.get(5).getCurrentTemp() + unit);
        }
    }

    public void openSettings() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Settings/settings.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Settings");
            stage.setScene(new Scene(root, 450, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWindow() {
        System.exit(0);
    }

    public void setFadeAnimation(Pane p, int ms) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(ms), p);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }

}
