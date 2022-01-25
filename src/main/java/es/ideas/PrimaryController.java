package es.ideas;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable {

    @FXML
    private ChoiceBox<String> cbSemana;
    @FXML
    private ToggleGroup grupo;
    @FXML
    private ImageView bItalia;
    @FXML
    private ImageView bUsa;
    @FXML
    private ImageView bEspaña;
    @FXML
    private ImageView bFrancia;
    @FXML
    private ImageView bIng;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Obtengo del ResourceBundle la traducción de los días de la semana mediante getString
        //Cada vez que se cambie de idioma, el método initialize se vuelve a ejecutar, y selecciona la traducción que le interesa
        //Lo que se le pasa al método getString, es el "key" de cada traducción
        String dias_semana[] = {rb.getString("lunes"), rb.getString("martes"), rb.getString("miercoles"), rb.getString("jueves"),
            rb.getString("viernes"), rb.getString("sabado"), rb.getString("domingo")};
        
        //Se añaden los items del array, previamente obtenidos de los archivos de traducción al ChoiceBox.
        cbSemana.setItems(FXCollections.observableArrayList(dias_semana));

        //Listener del grupo de botones para cambiar el idioma
        grupo.selectedToggleProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                ToggleButton tb = (ToggleButton) newValue.getToggleGroup()
                        .getSelectedToggle();
                //Se comprueba el valor del Texto del ToggleButton        
                switch (tb.getText()) {
                    case "Italiano":
                        Locale.setDefault(Locale.ITALIAN);
                        break;
                    case "Francés":
                        Locale.setDefault(Locale.FRENCH);
                        break;
                    case "InglesUSA":
                        Locale.setDefault(Locale.US);
                        break;
                    case "Ingles":
                        Locale.setDefault(Locale.UK);
                        break;
                    default:
                        Locale.setDefault(new Locale("es"));
                }
                try {
                    //Se crea un nuevo FXMLLoader llamando al método getFXMLLoader.
                    //Pero esta vez ha cambiado el idioma por defecto al que hayamos seleccionado en el programa.
                    Parent pane = getFXMLLoader().load();
                    //Se obtiene la escena de el Main y se le aplica el nuevo parent.
                    MultiLenguajeUI.getStage().getScene().setRoot(pane);
                } catch (IOException ieo) {}
                
                //Se muestra la nueva escena
                MultiLenguajeUI.getStage().show();
            }

        });
    }

    private FXMLLoader getFXMLLoader() {
        //Crea un nuevo FXMLLoader. Se le pasa el nuevo Locale cada vez que se cambie de idioma.
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/traduccion",
                Locale.getDefault()));
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }
}
