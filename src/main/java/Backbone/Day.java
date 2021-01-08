package Backbone;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Day {
    private double min_temp;
    private double max_temp;
    private long sunset;
    private long sunrise;
    private String day;
    private double feelsLike;
    private double currentTemp = -999;
    private double humidity;
    private String moonphase;
    private String narrative;
    private double rain;
    /**
     * Constructor for the weather forecast API
     *
     * @param min_temp
     * @param max_temp
     * @param narrative
     * @param moonphase
     * @param sunrise
     * @param sunset
     */
    public Day(String day, double min_temp, double max_temp, String narrative, String moonphase, long sunrise, long sunset, double rain) {
        this.day = day;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.narrative = narrative;
        this.moonphase = moonphase;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.rain = rain;
    }


    /**
     * @param min_temp
     * @param max_temp
     * @param feels_like
     * @param temp
     * @param humidity
     */
    public Day(double min_temp, double max_temp, int feels_like, int temp, int humidity) {
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.feelsLike = feels_like;
        this.currentTemp = temp;
        this.humidity = humidity;
    }

    public static String UTC_to_String(long utc) {
        ZonedDateTime zo = ZonedDateTime.ofInstant(Instant.ofEpochSecond(utc), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(zo);
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public double getFeelsLike() {
        return feelsLike;
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

    public String getSunrise() {
        return UTC_to_String(sunrise);
    }

    public String getSunset() {
        return UTC_to_String(sunset);
    }

    public long getLongSunset(){
        return sunset;
    }

    public long getLongSunrise(){
        return sunrise;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public String getDayName() {
        return day;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public String toStringWithUnit(String unit) {
        return day + ":\nMinimale Temperatur: " + min_temp + unit +
                "\nMaximale Temperatur: " + max_temp + unit +
                "\nMondphase: " + moonphase +
                "\nRegenwahrscheinlichkeit: " + rain +
                "\nSonnenaufgang: " + getSunrise() +
                "\nSonnenuntergang: " + getSunset();
    }

    @Override
    public String toString() {
        return day + ":\nMinimale Temperatur: " + min_temp +
                "\nMaximale Temperatur: " + max_temp +
                "\nMondphase: " + moonphase +
                "\nRegenwahrscheinlichkeit: " + rain +
                "\nSonnenaufgang: " + getSunrise() +
                "\nSonnenuntergang: " + getSunset();
    }


}
