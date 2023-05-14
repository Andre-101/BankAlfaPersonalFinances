package com.example.alfa;

import com.example.alfa.model.UserRegister;
import com.example.alfa.model.UserRegisterLists;
import com.jfoenix.controls.JFXToggleButton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableView<UserRegister> userRegisterTable;

    @FXML
    private TableColumn<UserRegister,String> typeTUR;

    @FXML
    private TableColumn<UserRegister,String> amountTUR;

    @FXML
    private TableColumn<UserRegister,String> descriptionTUR;

    @FXML
    private TableColumn<UserRegister,String> dateTUR;

    @FXML
    private Label balance;

    @FXML
    private JFXToggleButton income;

    @FXML
    private JFXToggleButton expenses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableLoad(UserRegisterLists.getInstance().getUserRegisters());
    }

    private void tableLoad(ObservableList<UserRegister> registers) {
        registers.sort(
                (a,b)-> Math.toIntExact(b.getDateSaved() - a.getDateSaved())
                );
        typeTUR.setCellValueFactory(new PropertyValueFactory<>("type"));
        amountTUR.setCellValueFactory(new PropertyValueFactory<>("amountFormat"));
        descriptionTUR.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateTUR.setCellValueFactory(new PropertyValueFactory<>("dateFormat"));
        userRegisterTable.setItems(registers);
        balance.setText(getBalance());
    }

    private String getBalance() {
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        double tempBalance = UserRegisterLists.getInstance().getBalance();
        if (tempBalance < 0) {
            tempBalance = Math.abs(tempBalance);
            balance.setTextFill(Color.RED);
            return  "-" + moneyFormat.format(tempBalance);
        }
        balance.setTextFill(Color.WHITE);
        return moneyFormat.format(tempBalance);
    }

    public void onRegisterButtonClicked(){
        HelloApplication.openWindow("form.fxml");
    }

    public void incomeToggleClicked(){
        if (!income.isSelected() && !expenses.isSelected())
            expenses.setSelected(true);
        showTable();
    }

    public void expensesToggleClicked(){
        if (!expenses.isSelected() && !income.isSelected())
            income.setSelected(true);
        showTable();
    }

    private void showTable() {
        if (income.isSelected() && expenses.isSelected())
            bothShowTable();
        if (income.isSelected() && !expenses.isSelected())
            incomeOnlyShowTable();
        if (!income.isSelected() && expenses.isSelected())
            expensesOnlyShowTable();
    }

    private void bothShowTable() {
        tableLoad(UserRegisterLists.getInstance().getUserRegisters());
    }

    private void incomeOnlyShowTable() {
        tableLoad(UserRegisterLists.getInstance().getURIncome());
    }

    private void expensesOnlyShowTable() {
        tableLoad(UserRegisterLists.getInstance().getURExpenses());
    }

    public void refreshView() {
        showTable();
    }
}