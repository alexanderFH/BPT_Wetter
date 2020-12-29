package Backbone;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Day {
    private final double MIN_TEMP;
    private final double MAX_TEMP;
    private final long SUNSET;
    private final long SUNRISE;
    private double feelsLike;
    private double currentTemp = -999;
    private double humidity;
    private String moonphase;
    private String narrative;
    private double rain;

    //windgesch / richtung

    /**
     * Constructor for the current condition API (Different API with access to different informations)
     * @param MIN_TEMP
     * @param MAX_TEMP
     * @param feelsLike
     * @param currentTemp
     * @param humidity
     * @param SUNRISE
     * @param SUNSET
     */
    public Day(double MIN_TEMP, double MAX_TEMP, double feelsLike, double currentTemp, double humidity, long SUNRISE, long SUNSET) {
        this.MIN_TEMP = MIN_TEMP;
        this.MAX_TEMP = MAX_TEMP;
        this.feelsLike = feelsLike;
        this.currentTemp = currentTemp;
        this.humidity = humidity;
        this.SUNRISE = SUNRISE;
        this.SUNSET = SUNSET;
    }

    /**
     * Constructor for the weather forecast API
     * @param MIN_TEMP
     * @param MAX_TEMP
     * @param narrative
     * @param moonphase
     * @param SUNRISE
     * @param SUNSET
     */
    public Day(double MIN_TEMP, double MAX_TEMP, String narrative, String moonphase, long SUNRISE, long SUNSET, double rain) {
        this.MIN_TEMP = MIN_TEMP;
        this.MAX_TEMP = MAX_TEMP;
        this.narrative = narrative;
        this.moonphase = moonphase;
        this.SUNRISE = SUNRISE;
        this.SUNSET = SUNSET;
        this.rain = rain;
    }


    public double getMIN_TEMP() {
        return MIN_TEMP;
    }

    public double getMAX_TEMP() {
        return MAX_TEMP;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(int feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getMoonphase() {
        return moonphase;
    }

    public void setMoonphase(String moonphase) {
        this.moonphase = moonphase;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getSUNRISE(){
        return UTC_to_String(SUNRISE);
    }

    public String getSUNSET(){
        return UTC_to_String(SUNSET);
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    private String UTC_to_String(long utc){
        ZonedDateTime zo = ZonedDateTime.ofInstant(Instant.ofEpochSecond(utc), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(zo);
    }

    @Override
    public String toString() {
        return "- MinTemp: " + MIN_TEMP + " - MaxTemp: " + MAX_TEMP + "\n" +
                "Narrative: " + narrative;
    }
}
