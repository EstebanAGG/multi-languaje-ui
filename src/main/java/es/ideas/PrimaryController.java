package es.ideas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class PrimaryController implements Initializable {

    @FXML
    private ChoiceBox<String> cbSemana;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Inicialización del ComboBox con los días de la semana
        //También tiene que traducirse
        String dias_semana[] = {"lunes", "martes", "miércoles", "jueves",
            "viernes", "sábado", "domingo"};
        cbSemana.setItems(FXCollections.observableArrayList(dias_semana));        
    }
}
