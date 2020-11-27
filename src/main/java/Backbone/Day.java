package Backbone;

public class Day {
    private String name;
    private int minTemp;
    private int maxTemp;
//sunset sunrise
    //windgesch / richtung
    private String narrative;

    public Day(String name, int minTemp, int maxTemp, String narrative) {
        this.name = name;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.narrative = narrative;
    }

    public String getName() {
        return name;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public String getNarrative() {
        return narrative;
    }

    @Override
    public String toString() {
        return name + ": - MinTemp: " + minTemp + " - MaxTemp: " + maxTemp + "\n" +
                "Narrative: " + narrative;
    }
}
