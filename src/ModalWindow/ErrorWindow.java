package ModalWindow;
import ControllerFiles.ErrorController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ErrorWindow {

    public ErrorWindow(String errorString) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../XMLfiles/ErrorWindow.fxml"));
        Parent root = fxmlLoader.load();
        ErrorController setController = fxmlLoader.getController();
        setController.getTextError(errorString);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMinWidth(400);
        stage.setMinHeight(200);
        stage.setScene(new Scene(root, 400, 200));
        stage.showAndWait();
    }
}
