package com.example.alfa;

import com.example.alfa.model.UserRegister;
import com.example.alfa.model.UserRegisterLists;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    @FXML
    private JFXComboBox<String> comboTypeRegister;

    @FXML
    private JFXTextField amountTF;

    @FXML
    private JFXTextField descriptionTF;

    @FXML
    private DatePicker dateDP;

    @FXML
    private Button save;

    ObservableList<String> comboTypeContent =
            FXCollections.observableArrayList(
                    "INGRESO",
                    "GASTO"
            );


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboTypeRegister.setItems(comboTypeContent);

        amountTF.addEventFilter(KeyEvent.ANY, handlerNumbers);

    }

    EventHandler<KeyEvent> handlerNumbers = new EventHandler<>() {
        private boolean willConsume = false;
        @Override
        public void handle(KeyEvent keyEvent) {
            if (willConsume) keyEvent.consume();
            boolean hasDot = amountTF.getText().contains(".");
            boolean isDot = keyEvent.getText().contains(".");
            if ((!keyEvent.getText().matches("[0-9.]") && keyEvent.getCode() != KeyCode.BACK_SPACE) || (hasDot && isDot)) {
                if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
                    willConsume = true;
                } else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
                    willConsume = false;
                }
            } else {
                willConsume = false;
            }
        }
    };

    public void saveButtonClicked() {
        if (comboTypeRegister.getValue() == null
        || amountTF.getText().isEmpty()
        || descriptionTF.getText().isEmpty()
        || dateDP.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardar Informacion");
            alert.setContentText("Existen campos vacios");
            alert.showAndWait();
            return;
        }
        String type = comboTypeRegister.getValue();
        double amount = Double.parseDouble(amountTF.getText());
        String description = descriptionTF.getText();
        LocalDate datePicker = dateDP.getValue();
        LocalDateTime ldt = datePicker.atStartOfDay();
        long date = ldt.toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
        UserRegister user = new UserRegister(type, amount, description, date);
        UserRegisterLists.getInstance().getUserRegisters().add(user);

        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }
}
