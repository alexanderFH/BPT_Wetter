package Backbone;

public class Day {
    private double minTemp;
    private double maxTemp;
    private double feelsLike;
    private double currentTemp = -999;
    private double humidity;
    private String moonphase;
    //sunset sunrise
    //windgesch / richtung
    private String narrative;
    private double rain;

    public Day(double minTemp, double maxTemp, double feelsLike, double currentTemp, double humidity) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.feelsLike = feelsLike;
        this.currentTemp = currentTemp;
        this.humidity = humidity;
    }

    public Day(double minTemp, double maxTemp, String narrative, String moonphase) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.narrative = narrative;
        this.moonphase = moonphase;
    }


    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
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

    @Override
    public String toString() {
        return "- MinTemp: " + minTemp + " - MaxTemp: " + maxTemp + "\n" +
                "Narrative: " + narrative;
    }
}
