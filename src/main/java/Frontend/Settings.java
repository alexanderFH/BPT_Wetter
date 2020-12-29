package Frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {

        @FXML
        private MenuButton Temperature;

        @FXML
        private TextField Position;

        @FXML
        private Button Export;

        @FXML
        private ColorPicker Colourpick;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
