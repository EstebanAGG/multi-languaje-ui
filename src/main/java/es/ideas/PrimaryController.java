package es.ideas;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

public class PrimaryController implements Initializable {

    @FXML
    private ChoiceBox<String> cbSemana;
    @FXML
    private ToggleButton tbtnES, tbtnIT, tbtnIn, tbtnFr;
    private MultiLenguajeUI main;

    public void setMain(MultiLenguajeUI main) {
        this.main = main;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Inicialización del ComboBox con los días de la semana
        //También tiene que traducirse
        String dias_semana[] = {"lunes", "martes", "miércoles", "jueves",
                "viernes", "sábado", "domingo"};
        cbSemana.setItems(FXCollections.observableArrayList(dias_semana));


        /*Inicialización del toggleGroup
         * */
        ToggleGroup toggleGroup = new ToggleGroup();

        toggleGroup.getToggles().addAll(tbtnES, tbtnIT, tbtnIn,tbtnFr);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ToggleButton toggleButton = (ToggleButton) newValue
                        .getToggleGroup()
                        .getSelectedToggle();

                switch (toggleButton.getText()) {

                    case "Italiano":
                        Locale.setDefault(Locale.ITALIAN);
                        break;
                    case "Inglés":
                        Locale.setDefault(Locale.US);
                        break;
                    case "Frances":
                        Locale.setDefault(Locale.FRENCH);
                        break;
                    default:
                        Locale.setDefault(new Locale("es"));
                }

                try {
                    Parent parent = getLoader().load();
                    MultiLenguajeUI.getStagePrimary().getScene().setRoot(parent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MultiLenguajeUI.getStagePrimary().show();


            }
        });


    }

    private FXMLLoader getLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/idioma",
                Locale.getDefault()));
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }
}
