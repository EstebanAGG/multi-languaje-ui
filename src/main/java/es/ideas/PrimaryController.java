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

public class PrimaryController implements Initializable {
    @FXML
    private ChoiceBox<String> cbSemana;
    @FXML
    private ToggleButton tbtnES, tbtnIT, tbtnIn, tbtnFr;
    private MultiLenguajeUI main;

    public void setMain(MultiLenguajeUI main) {this.main = main;}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        * Inicialización del ComboBox con los días de la semana
        * mediane el metodo getString de la clase abstracta ResourceBundle
        * que devuelve un string e introduce la key que se le pasa como parámetro
        * construyo el array de string que representa cada uno de losa elementos
        * del ChoiceBox*/
        String dias_semana[] = {rb.getString("l"),
                rb.getString("m"),
                rb.getString("x"),
                rb.getString("j"),
                rb.getString("v"),
                rb.getString("s"),
                rb.getString("d")};
        cbSemana.setItems(FXCollections.observableArrayList(dias_semana));
        /*
        * Se inicializa el ToggleGroup y se le añaden los botones encargados de seleccionar el idioma
        * y el listener */
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(tbtnES, tbtnIT, tbtnIn,tbtnFr);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            /*
            * Se modifica el valor de Locale en función del texto que contenga el botón pulsado*/
            if (newValue != null) {
                ToggleButton toggleButton = (ToggleButton) newValue.getToggleGroup().getSelectedToggle();
                switch (toggleButton.getText()) {
                    case "Italiano":Locale.setDefault(Locale.ITALIAN);
                        break;
                    case "Inglés":Locale.setDefault(Locale.US);
                        break;
                    case "Frances":Locale.setDefault(Locale.FRENCH);
                        break;
                    default:Locale.setDefault(new Locale("es"));
                }
                /* Se carga de nuevo el parent mediante el método getLoader
                   que modifica el valor de Locale con el nuevo valor que ha recibido
                *  tras su modificación en el switch anterior*/
                try {
                    Parent parent = getLoader().load();
                    MultiLenguajeUI.getStagePrimary().getScene().setRoot(parent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MultiLenguajeUI.getStagePrimary().show();}});
}
/*Método que devuelve un objeto de la clase FXMLLoader
modificado con los nuevos valores por defecto de la clase Locale
recibidos al pulsar un botón de seleccion de idioma
* */
    private FXMLLoader getLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/idioma",Locale.getDefault()));
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }
}
