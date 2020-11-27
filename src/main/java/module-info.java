module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;

    opens com.app to javafx.fxml;
    exports com.app;
}