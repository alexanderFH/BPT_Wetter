package Backbone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class WeatherGetter {
    public static void main(String[] args) throws IOException {
        // ArrayList<Backbone.Day> days = getWeatherJson("33.74,-84.39");
        Day day = new Day("sonntat", 5, 10, "toll");//test
        System.out.println(day.getCurrentTemp());
        ArrayList<Day> days = getWeatherJson("1220:AT");
        for (Day d : days)
            System.out.println(d);
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static ArrayList<Day> getWeatherJson(String plz) throws IOException {
        ArrayList<Day> back = new ArrayList<>();
        // InputStream is = new URL("https://api.weather.com/v3/wx/forecast/daily/5day?geocode=" + coordinates + "&format=json&units=m&language=de-DE&apiKey=1531e846099f413eb1e846099ff13ef6").openStream();
        InputStream is = new URL("https://api.weather.com/v3/wx/forecast/daily/5day?postalKey=" + plz + "&format=json&units=m&language=de-DE&apiKey=1531e846099f413eb1e846099ff13ef6").openStream();

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonString = readAll(rd);
            System.out.println(jsonString);
            //String jsonString = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8"))); ; //assign your JSON String here
            JSONObject obj = new JSONObject(jsonString);
            System.out.println(obj);
            //String pageName = obj.getJSONObject("pageInfo").getString("pageName");
            JSONArray dayName = obj.getJSONArray("dayOfWeek");
            JSONArray tempMin = obj.getJSONArray("temperatureMin");
            JSONArray tempMax = obj.getJSONArray("temperatureMax");
            JSONArray nar = obj.getJSONArray("narrative");
            for (int i = 0; i < dayName.length(); i++) {
                if (tempMax.get(i).equals(null))
                    back.add(new Day(dayName.getString(i), tempMin.getInt(i), 20, nar.getString(i)));
                else
                    back.add(new Day(dayName.getString(i), tempMin.getInt(i), tempMax.getInt(i), nar.getString(i)));
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

    protected static void getCurrentWeather(String plz) throws IOException {
        InputStream is = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" + plz + ",AT&appid=6b5717bc865ffcb87230cfbcf6263078&units=metric").openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonString = readAll(rd);
            //String jsonString = "{\"coord\":{\"lon\":16.5,\"lat\":48.22},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":2.97,\"feels_like\":0.59,\"temp_min\":1.67,\"temp_max\":3.89,\"pressure\":1026,\"humidity\":93},\"visibility\":10000,\"wind\":{\"speed\":1,\"deg\":160},\"clouds\":{\"all\":75},\"dt\":1608222958,\"sys\":{\"type\":1,\"id\":6878,\"country\":\"AT\",\"sunrise\":1608187190,\"sunset\":1608217246},\"timezone\":3600,\"id\":0,\"name\":\"Wien, Donaustadt\",\"cod\":200}\n"
            System.out.println(jsonString);
            JSONObject obj = new JSONObject(jsonString);
            JSONObject weather = obj.getJSONArray("weather").getJSONObject(0);
            JSONObject temp = obj.getJSONObject("main");
            System.out.println(temp);
            System.out.println(weather.get("main")); // Jetziges Wetter Sonne/Wolke/whatever
            //System.out.println(weather.get(0).);
        } catch (Exception e) {

        } finally {
            is.close();
        }
    }
}
