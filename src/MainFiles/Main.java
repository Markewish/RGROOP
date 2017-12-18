package MainFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../XMLfiles/MainWindow.fxml"));
        primaryStage.setTitle("RGR Program");
        primaryStage.getIcons().add(new Image("/Image/starSystem.png"));
        primaryStage.setMinWidth(720);
        primaryStage.setMinHeight(530);
        primaryStage.setMaxWidth(700);
        primaryStage.setMaxHeight(500);
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        //Инициализация любых данных, до включения основного потока Start в работу.
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //Здесь Вы можете прописать все действия при закрытии Вашего приложения.
    }

    public static void main(String[] args) {
        launch(args);
    }
}
