package Backbone;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Day {
    private double min_temp;
    private double max_temp;
    private final long SUNSET;
    private final long SUNRISE;
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
     * @param SUNRISE
     * @param SUNSET
     */
    public Day(String day, double min_temp, double max_temp, String narrative, String moonphase, long SUNRISE, long SUNSET, double rain) {
        this.day = day;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.narrative = narrative;
        this.moonphase = moonphase;
        this.SUNRISE = SUNRISE;
        this.SUNSET = SUNSET;
        this.rain = rain;
    }


    public double getMin_temp() {
        return min_temp;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(double feelsLike) {
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

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getSUNRISE() {
        return UTC_to_String(SUNRISE);
    }

    public String getSUNSET() {
        return UTC_to_String(SUNSET);
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

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }

    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }

    public static String UTC_to_String(long utc) {
        ZonedDateTime zo = ZonedDateTime.ofInstant(Instant.ofEpochSecond(utc), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(zo);
    }

    public String toStringWithUnit(String unit) {
        return day + ":\nMinimale Temperatur: " + min_temp + unit +
                "\nMaximale Temperatur: " + max_temp + unit +
                "\nMondphase: " + moonphase +
                "\nRegenwahrscheinlichkeit: " + rain +
                "\nSonnenaufgang: " + getSUNRISE() +
                "\nSonnenuntergang: " + getSUNSET();
    }

    @Override
    public String toString() {
        return day + ":\nMinimale Temperatur: " + min_temp +
                "\nMaximale Temperatur: " + max_temp +
                "\nMondphase: " + moonphase +
                "\nRegenwahrscheinlichkeit: " + rain +
                "\nSonnenaufgang: " + getSUNRISE() +
                "\nSonnenuntergang: " + getSUNSET();
    }


}
