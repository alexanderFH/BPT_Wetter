package Backbone;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WeatherGetter {
    public static void main(String[] args) throws IOException {
        ArrayList<Day> days = getWeatherJson("1220", "AT");
        for (Day day : days) {
            System.out.println(day.getMoonphase());
            System.out.println(day.getSUNRISE());
        }
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

    public static ArrayList<Day> getWeatherJson(String plz, String country) {
        ArrayList<Day> back = new ArrayList<>();
        Day today = getCurrentWeather(plz, country);

        try (InputStream is = new URL("https://api.weather.com/v3/wx/forecast/daily/5day?postalKey=" + plz + ":" + country + "&format=json&units=m&language=de-DE&apiKey=1531e846099f413eb1e846099ff13ef6").openStream()) {
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
            today.setMoonphase(moon.getString(0));
            today.setNarrative(nar.getString(0));
            today.setRain(rain.getDouble(0));
            back.add(today);
            for (int i = 1; i < dayName.length(); i++) {
                back.add(new Day(tempMin.getDouble(i), tempMax.getDouble(i), nar.getString(i), moon.getString(i), sunrise.getLong(i), sunset.getLong(i), rain.getDouble(i)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return back;
    }

    protected static void getWeather(String plz, String country) {
        try (InputStream is = new URL("http://api.openweathermap.org/data/2.5/forecast?zip=" + plz + "," + country + "&appid=6b5717bc865ffcb87230cfbcf6263078&units=metric&lang=de").openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonString = readAll(rd);
            JSONObject weatherData = new JSONObject(jsonString);
            System.out.println("-");
            System.out.println(weatherData);
            System.out.println(".");
            JSONArray test = weatherData.getJSONArray("list");
            System.out.println(test.length());
            // JSONObject weather = weatherData.getJSONArray("weather").getJSONObject(0);
            // JSONObject temp = weatherData.getJSONObject("main");
        } catch (Exception e) {
            System.err.println("Da lief etwas schief!");
            e.printStackTrace();
        }
    }

    protected static Day getCurrentWeather(String plz, String country) {
        Day today = null;
        try (InputStream is = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" + plz + "," + country + "&appid=6b5717bc865ffcb87230cfbcf6263078&units=metric&lang=de").openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonString = rd.readLine();
            JSONObject weatherData = new JSONObject(jsonString);
            JSONObject temp = weatherData.getJSONObject("main");
            JSONObject sys = weatherData.getJSONObject("sys");
            today = new Day(temp.getDouble("temp_min"), temp.getDouble("temp_max"), temp.getDouble("feels_like"), temp.getDouble("temp"), temp.getDouble("humidity"), sys.getLong("sunrise"), sys.getLong("sunset"));
        } catch (Exception e) {
            System.err.println("Da lief etwas schief!");
            e.printStackTrace();
        }
        return today;
    }


}
