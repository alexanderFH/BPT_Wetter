package Backbone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WeatherGetter {
    public static void main(String[] args) throws IOException {
        // ArrayList<Backbone.Day> days = getWeatherJson("33.74,-84.39");
        System.out.println(getCurrentWeather("1220", "AT").getCurrentTemp());
        ArrayList<Day> days = getWeatherJson("1220", "AT");
        System.out.println(days.get(0));
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

    public static ArrayList<Day> getWeatherJson(String plz, String country) throws IOException {
        ArrayList<Day> back = new ArrayList<>();
        // InputStream is = new URL("https://api.weather.com/v3/wx/forecast/daily/5day?geocode=" + coordinates + "&format=json&units=m&language=de-DE&apiKey=1531e846099f413eb1e846099ff13ef6").openStream();
        InputStream is = new URL("https://api.weather.com/v3/wx/forecast/daily/5day?postalKey=" + plz + ":" + country + "&format=json&units=m&language=de-DE&apiKey=1531e846099f413eb1e846099ff13ef6").openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonString = readAll(rd);
            System.out.println(jsonString);
            //String jsonString = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))); ; //assign your JSON String here
            JSONObject obj = new JSONObject(jsonString);
            System.out.println(obj);
            //String pageName = obj.getJSONObject("pageInfo").getString("pageName");
            back.add(getCurrentWeather(plz, country));
            JSONArray dayName = obj.getJSONArray("dayOfWeek");
            JSONArray tempMin = obj.getJSONArray("temperatureMin");
            JSONArray tempMax = obj.getJSONArray("temperatureMax");
            JSONArray nar = obj.getJSONArray("narrative");
            for (int i = 1; i < dayName.length(); i++) {
                if (tempMax.get(i).equals(null))
                    back.add(new Day(tempMin.getInt(i), 20, nar.getString(i)));
                else
                    back.add(new Day(tempMin.getInt(i), tempMax.getInt(i), nar.getString(i)));
            }
           /* JSONObject day = (JSONObject) obj.getJSONArray("daypart").get(0);
            System.out.println(day.length());
            System.out.println(day);
            JSONArray arr1 = day.getJSONArray("daypartName");
            JSONArray nar1 = day.getJSONArray("narrative");
            for (int i = 0; i < day.length(); i++) {
                System.out.println(arr1.get(i) + " - " + nar1.get(i));
            }

            */
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            is.close();
        }
        return back;
    }

    protected static Day getCurrentWeather(String plz, String country) throws IOException {
        Day back = null;
        InputStream is = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" + plz + "," + country + "&appid=6b5717bc865ffcb87230cfbcf6263078&units=metric&lang=de").openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonString = readAll(rd);
            JSONObject weatherData = new JSONObject(jsonString);
            JSONObject weather = weatherData.getJSONArray("weather").getJSONObject(0);
            JSONObject temp = weatherData.getJSONObject("main");
            ZonedDateTime zo = ZonedDateTime.ofInstant(Instant.ofEpochSecond(weatherData.getInt("dt")), ZoneId.systemDefault());
            String day_name = zo.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.GERMAN);
            back = new Day(temp.getInt("temp_min"), temp.getInt("temp_max"), weather.getString("description"));
            back.setFeelsLike(temp.getInt("feels_like"));
            back.setCurrentTemp(temp.getInt("temp"));
        } catch (Exception e) {
            System.err.println("Da lief etwas schief!");
            e.printStackTrace();
        } finally {
            is.close();
        }
        return back;
    }
}
