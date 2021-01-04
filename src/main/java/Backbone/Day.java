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
     * @param MIN_TEMP
     * @param MAX_TEMP
     * @param narrative
     * @param moonphase
     * @param SUNRISE
     * @param SUNSET
     */
    public Day(String day, double MIN_TEMP, double MAX_TEMP, String narrative, String moonphase, long SUNRISE, long SUNSET, double rain) {
        this.day = day;
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

    private String UTC_to_String(long utc) {
        ZonedDateTime zo = ZonedDateTime.ofInstant(Instant.ofEpochSecond(utc), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(zo);
    }

    public String toStringWithUnit(String unit) {
        return day + ":\nMinimale Temperatur: " + MIN_TEMP + unit +
                "\nMaximale Temperatur: " + MAX_TEMP + unit +
                "\nMondphase: " + moonphase +
                "\nRegenwahrscheinlichkeit: " + rain +
                "\nSonnenaufgang: " + getSUNRISE() +
                "\nSonnenuntergang: " + getSUNSET();
    }

    @Override
    public String toString() {
        return day + ":\nMinimale Temperatur: " + MIN_TEMP +
                "\nMaximale Temperatur: " + MAX_TEMP +
                "\nMondphase: " + moonphase +
                "\nRegenwahrscheinlichkeit: " + rain +
                "\nSonnenaufgang: " + getSUNRISE() +
                "\nSonnenuntergang: " + getSUNSET();
    }


}
