package ControllerFiles;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class ErrorController implements Initializable{
    @FXML
    private ImageView imageExit;
    @FXML
    public Label LabelTextError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageExit.setOnMouseClicked(
                event -> {
                    Stage stage = (Stage) imageExit.getScene().getWindow();
                    stage.hide();
                }
        );
        LabelTextError.setWrapText(true);
    }

    public void getTextError(String textError){
        LabelTextError.setText(textError);
    }
}
