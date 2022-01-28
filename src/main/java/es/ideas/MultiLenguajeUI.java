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
 * JavaFX App, método principal
 */
public class MultiLenguajeUI extends Application {
    private static Scene scene;
    /*Declaro un objeto de la clase Stage*/
    private static Stage stagePrimary;
    @Override
    public void start(Stage stage) throws IOException {
        /*Instancio el objeto de la clase Stage previamente creado con valor de esta clase
        * que recibe el método start como parámetro */
        this.stagePrimary = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("primary.fxml"));
        /*
        * Cargo el directo de recursos que contiene los archivos properties
        * y el Locale por defecto del sistema*/
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/idioma", Locale.getDefault()));
        Parent parent = loader.load();
        PrimaryController primaryController = loader.getController();
        primaryController.setMain(this);
        scene = new Scene(parent, 640, 480);
        stage.setScene(scene);
        stage.show();
    }
    /*Método getter de la instancia del objeto del tipo Stage
    * que utilizare desde el controlador*/
    public static Stage getStagePrimary(){
        return stagePrimary;
    }
    public static void main(String[] args) {
        launch();
    }
}