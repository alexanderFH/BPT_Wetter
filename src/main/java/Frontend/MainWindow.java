package Frontend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    @FXML
    private Label dayOne;
    @FXML
    private Label dayTwo;
    @FXML
    private Label dayThree;
    @FXML
    private Label dayFour;
    @FXML
    private Label dayFive;


    // Sets the days based on int values on to tge GUI
    // dayfx = Label of the day
    // controllNumber = used to change from dayOn to dayTwo and so on...
    @FXML
    private void setDays(Label dayfx, int controllNumber) {

        // Get current day
        String day = null;
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);

        // Loop to catch numbers >= 7 -> resets on sundays
        for (int i = 1; i <= controllNumber; i++) {
            if (currentDay >= 7) {
                currentDay = 0;
            }

            currentDay += 1;
        }

        // Set days based on int value
        switch (currentDay) {
            case Calendar.MONDAY -> day = "Montag";         // Monday = 2
            case Calendar.TUESDAY -> day = "Dienstag";      // Tuesday = 3
            case Calendar.WEDNESDAY -> day = "Mittwoch";    // Wednesday = 4
            case Calendar.THURSDAY -> day = "Donnerstag";   // Thursday = 5
            case Calendar.FRIDAY -> day = "Freitag";        // Friday = 6
            case Calendar.SATURDAY -> day = "Samstag";      // Saturday = 7
            case Calendar.SUNDAY -> day = "Sonntag";        // Sunday = 1
            default -> System.out.println("Invalid Day");
        }

       dayfx.setText(day);

    }

    // "Main" method of the controller
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDays(dayOne, 0);
        setDays(dayTwo, 1);
        setDays(dayThree, 2);
        setDays(dayFour, 3);
        setDays(dayFive, 4);
    }
}
