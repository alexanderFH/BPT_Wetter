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

    protected static ArrayList<Day> getWeatherJson(String plz) throws IOException {
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
}
