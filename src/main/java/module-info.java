module com.example.alfa {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens com.example.alfa to javafx.fxml;
    exports com.example.alfa;

    opens com.example.alfa.model to javafx.fxml;
    exports com.example.alfa.model;
}