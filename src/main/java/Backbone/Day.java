package Backbone;

public class Day {
    private int minTemp;
    private int maxTemp;
    private int feelsLike;
    private int currentTemp = -999;
    private int humidity;
    private String moonphase;
    //sunset sunrise
    //windgesch / richtung
    private String narrative;

    public Day(int minTemp, int maxTemp, int feelsLike, int currentTemp, int humidity) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.feelsLike = feelsLike;
        this.currentTemp = currentTemp;
        this.humidity = humidity;
    }

    public Day(int minTemp, int maxTemp, String narrative, String moonphase) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.narrative = narrative;
        this.moonphase = moonphase;
    }


    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public int getFeelsLike() {
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

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "- MinTemp: " + minTemp + " - MaxTemp: " + maxTemp + "\n" +
                "Narrative: " + narrative;
    }
}
