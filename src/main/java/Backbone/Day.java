package Backbone;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Day {
    private final double MIN_TEMP;
    private final double MAX_TEMP;
    private long sunset;
    private long sunrise;
    private String day;
    private double feelsLike;
    private double currentTemp = -999;
    private double humidity;
    private String moonphase;
    private String narrative;
    private double rain;
    private String location;

    /**
     * Constructor for the weather forecast API
     *
     * @param min_temp  minimum temp as double
     * @param max_temp  maximum temp as double
     * @param narrative narrative as string
     * @param moonphase moonphase as string
     * @param sunrise   sunrise as utc long
     * @param sunset    sunset as utc long
     */
    public Day(String day, double min_temp, double max_temp, String narrative, String moonphase, long sunrise, long sunset,
               double rain,String location) {
        this.day = day;
        this.MIN_TEMP = min_temp;
        this.MAX_TEMP = max_temp;
        this.narrative = narrative;
        this.moonphase = moonphase;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.rain = rain;
        this.location = location;
    }


    /**
     * Constructor of the current weather api
     *
     * @param min_temp   minimum temp as a double
     * @param max_temp   maximum temp as a double
     * @param feels_like feels like temp as a double
     * @param temp       current temp as double
     * @param humidity   current humidity as double
     */
    public Day(double min_temp, double max_temp, double feels_like, double temp, double humidity, String location) {
        this.MIN_TEMP = min_temp;
        this.MAX_TEMP = max_temp;
        this.feelsLike = feels_like;
        this.currentTemp = temp;
        this.humidity = humidity;
        this.location = location;
    }

    /**
     * formats a utc long to a String with the format HH:mm:ss
     *
     * @param utc long
     * @return time with the format HH:mm:ss as string
     */
    public static String UTC_to_String(long utc) {
        ZonedDateTime zo = ZonedDateTime.ofInstant(Instant.ofEpochSecond(utc), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern("HH:mm:ss").format(zo);
    }

    public String getLocation() {
        return location;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getMin_temp() {
        return MIN_TEMP;
    }


    public double getMax_temp() {
        return MAX_TEMP;
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

    public long getLongSunset() {
        return sunset;
    }

    public long getLongSunrise() {
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
        return day + ":\nMinimale Temperatur: " + MIN_TEMP + unit +
                "\nMaximale Temperatur: " + MAX_TEMP + unit +
                "\nMondphase: " + moonphase +
                "\nRegenwahrscheinlichkeit: " + rain +
                "\nSonnenaufgang: " + getSunrise() +
                "\nSonnenuntergang: " + getSunset();
    }

    @Override
    public String toString() {
        return day + ":\nMinimale Temperatur: " + MIN_TEMP +
                "\nMaximale Temperatur: " + MAX_TEMP +
                "\nMondphase: " + moonphase +
                "\nRegenwahrscheinlichkeit: " + rain +
                "\nSonnenaufgang: " + getSunrise() +
                "\nSonnenuntergang: " + getSunset();
    }


}
