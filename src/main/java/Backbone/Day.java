package Backbone;

public class Day {
    private int minTemp;
    private int maxTemp;
    private int feelsLike;
    private int currentTemp = -999;
    private int humidity;
    //sunset sunrise
    //windgesch / richtung
    private String narrative;

    public Day( int minTemp, int maxTemp, String narrative) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.narrative = narrative;
    }


    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setCurrentTemp(int currentTemp) {
        this.currentTemp = currentTemp;
    }

    public int getCurrentTemp() {
        return currentTemp;
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

    @Override
    public String toString() {
        return "- MinTemp: " + minTemp + " - MaxTemp: " + maxTemp + "\n" +
                "Narrative: " + narrative;
    }
}
