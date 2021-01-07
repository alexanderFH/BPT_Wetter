package Backbone;

import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WeatherGetter {
    private static final String weatherAPIKey = "d3d0b6bdc95a468f90b6bdc95a468f52";
    private static final String openWeatherAPIKey = "6b5717bc865ffcb87230cfbcf6263078";

    //TODO Fehlermeldung falls falscher Standort

    public static void main(String[] args) throws IOException {
        //    ArrayList<Day> days = getWeatherJson("2325", "AT", false);
        //  System.out.println(days.size());
        printWeatherToFile("2325", "AT", false);
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

    /**
     * @param rd
     * @return
     * @throws IOException
     */
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * @param plz
     * @param country
     * @param celsius
     * @return
     */
    public static ArrayList<Day> getWeatherJson(String plz, String country, boolean celsius) {
        ArrayList<Day> back = new ArrayList<>();
        String unit = "m";
        if (!celsius)
            unit = "e";
        try (InputStream is = new URL("https://api.weather.com/v3/wx/forecast/daily/5day?postalKey=" + plz + ":" + country + "&format=json&units=" + unit + "&language=de-DE&apiKey=" + weatherAPIKey).openStream()) {
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

            Day today = getCurrentWeather(plz, country, celsius);
            today.setNarrative(nar.getString(0));
            today.setRain(rain.getDouble(0));
            today.setMoonphase(moon.getString(0));
            today.setDay(dayName.getString(0));
            today.setSunrise(sunrise.getLong(0));
            today.setSunset(sunset.getLong(0));
            back.add(today);
            for (int i = 1; i < dayName.length(); i++) {
                back.add(new Day(dayName.getString(i), tempMin.getDouble(i), tempMax.getDouble(i), nar.getString(i), moon.getString(i), sunrise.getLong(i), sunset.getLong(i), rain.getDouble(i)));

            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.err.println("Invalid postal key / country combination!");
            alertWindow("Ung\u00fcltige Adresse!", "Leider wurde die angegebene Adresse nicht gefunden!", "Es werden nun die Wetterdaten der Standard-Adresse 1220,AT angezeigt.");
            return getWeatherJson("1220", "AT", true);
        } catch (Exception e) {
            System.err.println();
        }
        return back;
    }

    /**
     * @param plz
     * @param country
     * @param celsius
     */
    protected static Day getCurrentWeather(String plz, String country, boolean celsius) {
        Day today = null;
        String unit = "metric";
        if (!celsius)
            unit = "imperial";
        try (InputStream is = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" + plz + "," + country + "&appid=" + openWeatherAPIKey + "&units=" + unit + "&lang=de").openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonString = rd.readLine();
            JSONObject weatherData = new JSONObject(jsonString);
            JSONObject temp = weatherData.getJSONObject("main");
            today = new Day(temp.getDouble("temp_min"), temp.getDouble("temp_max"), temp.getInt("feels_like"), temp.getInt("temp"), temp.getInt("humidity"));
        } catch (Exception e) {
            System.err.println("Da lief etwas schief!");
            e.printStackTrace();
        }
        return today;
    }

    /**
     * @param plz
     * @param country
     * @param celsius
     */
    public static void printWeatherToFile(String plz, String country, boolean celsius) {
        ArrayList<Day> forecast = getWeatherJson(plz, country, celsius);
        String unit = " \u2103";
        if (!celsius)
            unit = " \u2109";
        try {
            FileChooser fx = new FileChooser();
            fx.setTitle("Bitte wählen Sie einen Speicherort");
            fx.setInitialFileName("Wetter.txt");
            File file = fx.showSaveDialog(null);
            if (file == null) {
                alertWindow("Export-Fehler", "Es wurde kein g\u00fcltiger Speicherort ausgew\u00e4hlt!", null);
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

    private static void alertWindow(String title, String header, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        if (text != null)
            alert.setContentText(text);
        alert.show();
    }


}
