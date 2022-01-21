module es.ideas {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.ideas to javafx.fxml;
    exports es.ideas;
}
