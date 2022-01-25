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

    private static Stage escena;

    @Override
    public void start(Stage stage) throws IOException {
        
        MultiLenguajeUI.escena = stage;

        FXMLLoader loader = new FXMLLoader();
        //Localización de la interfaz
        loader.setLocation(getClass().getResource("primary.fxml"));
        //Localización de los archivos de traducción
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/traduccion",
                Locale.getDefault()));
        Parent raiz = loader.load();
        
        Scene scene = new Scene(raiz);
        stage.setScene(scene);
        stage.show();
        
    }

    //Método getStage para poder utilizar el Stage en otras clases
    public static Stage getStage(){
        return escena;
    }

    public static void main(String[] args) {
        launch();
    }

}