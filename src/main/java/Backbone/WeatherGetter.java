package Backbone;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WeatherGetter {
    private static final String weatherAPIKey = "d3d0b6bdc95a468f90b6bdc95a468f52";
    private static final String openWeatherAPIKey = "6b5717bc865ffcb87230cfbcf6263078";

    public static void main(String[] args) throws IOException {
    //    ArrayList<Day> days = getWeatherJson("2325", "AT", false);
      //  System.out.println(days.size());
        printWeatherToFile("2325","AT",false);
     //   for (Day day : days)
       //     System.out.println(day);
        // printWeatherToFile("1220","AT",true);
        // ArrayList<Backbone.Day> days = getWeatherJson("33.74,-84.39");
        //getWeatherJson("1220", "AT");
        //WeatherGetter w = new WeatherGetter();
        //System.out.println(w.UTC_to_String(1609224276));
        // System.out.println(getCurrentWeather("1220", "AT").getNarrative());
        // ArrayList<Day> days = getWeatherJson("1220", "AT");
        // System.out.println(days.get(0));
       /* Day day = new Day("sonntat", 5, 10, "toll");//test
        System.out.println(day.getCurrentTemp());
        ArrayList<Day> days = getWeatherJson("1220:AT");
        for (Day d : days)
            System.out.println(d);
        */
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static ArrayList<Day> getWeatherJson(String plz, String country, boolean celsius) {
        ArrayList<Day> back = new ArrayList<>();
        String unit = "m";
        if (!celsius)
            unit = "e";
        try (InputStream is = new URL("https://api.weather.com/v3/wx/forecast/daily/5day?postalKey=" + plz + ":" + country + "&format=json&units=" + unit + "&language=de-DE&apiKey="+weatherAPIKey).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonString = readAll(rd);
            JSONObject weatherData = new JSONObject(jsonString);
            JSONArray dayName = weatherData.getJSONArray("dayOfWeek");
            JSONArray tempMin = weatherData.getJSONArray("temperatureMin");
            JSONArray tempMax = weatherData.getJSONArray("temperatureMax");
            JSONArray nar = weatherData.getJSONArray("narrative");
            JSONArray moon = weatherData.getJSONArray("moonPhase");
            JSONArray sunrise = weatherData.getJSONArray("sunriseTimeUtc");
            JSONArray sunset = weatherData.getJSONArray("sunsetTimeUtc");
            JSONArray rain = weatherData.getJSONArray("qpf");
            for (int i = 0; i < dayName.length(); i++) {
                if (tempMax.get(i).equals(null))
                    back.add(new Day(dayName.getString(i), 0, 0, nar.getString(i), moon.getString(i), sunrise.getLong(i), sunset.getLong(i), rain.getDouble(i)));
                else
                    back.add(new Day(dayName.getString(i), tempMin.getDouble(i), tempMax.getDouble(i), nar.getString(i), moon.getString(i), sunrise.getLong(i), sunset.getLong(i), rain.getDouble(i)));

            }
            getCurrentWeather(plz, country, celsius, back.get(0));
        } catch (Exception e) {
            System.out.println(e);
        }
        return back;
    }

    protected static void getCurrentWeather(String plz, String country, boolean celsius, Day day) {
        String unit = "metric";
        if (!celsius)
            unit = "imperial";
        try (InputStream is = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" + plz + "," + country + "&appid="+openWeatherAPIKey+"&units=" + unit + "&lang=de").openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonString = rd.readLine();
            JSONObject weatherData = new JSONObject(jsonString);
            JSONObject temp = weatherData.getJSONObject("main");
            day.setFeelsLike(temp.getDouble("feels_like"));
            day.setCurrentTemp(temp.getDouble("temp"));
            day.setHumidity(temp.getDouble("humidity"));
            day.setMIN_TEMP(temp.getDouble("temp_min"));
            day.setMAX_TEMP(temp.getDouble("temp_max"));
        } catch (Exception e) {
            System.err.println("Da lief etwas schief!");
            e.printStackTrace();
        }
    }

    public static void printWeatherToFile(String plz, String country, boolean celsius) {
        ArrayList<Day> forecast = getWeatherJson(plz, country, celsius);
        String unit = " \u2103";
        if (!celsius)
            unit = " Fahrenheit"; //TODO Richtiges Zeichen suchen!
        try {
            FileChooser fx = new FileChooser();
            fx.setTitle("Bitte wählen Sie einen Speicherort");
            fx.setInitialFileName("Wetter.txt");
            File file = fx.showSaveDialog(null);
            if (file == null) {
                JOptionPane.showMessageDialog(null, "Kein gültiger Speicherort ausgewählt!", "Achtung", JOptionPane.ERROR_MESSAGE);
            } else {
                PrintWriter writer = new PrintWriter(file, "UTF-8");
                for (Day day : forecast) {
                    writer.println(day.toStringWithUnit(unit) + "\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Fehler beim File writen");
            e.printStackTrace();
        }
    }


}
