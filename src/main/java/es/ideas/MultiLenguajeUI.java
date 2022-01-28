package es.ideas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class MultiLenguajeUI extends Application {

    private static Scene scene;
    private static Stage stagePrimary;

    @Override
    public void start(Stage stage) throws IOException {
        this.stagePrimary = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("primary.fxml"));
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/idioma",
                Locale.getDefault()));
        Parent parent = loader.load();
        PrimaryController primaryController = loader.getController();
        primaryController.setMain(this);
        scene = new Scene(parent, 640, 480);
        stage.setScene(scene);
        stage.show();
    }


    public static Stage getStagePrimary(){
        return stagePrimary;
    }

    public static void main(String[] args) {
        launch();
    }

}