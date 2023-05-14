package com.example.alfa.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserRegisterLists {

    //Attributes
    private static UserRegisterLists instance = null;
    private ObservableList<UserRegister> userRegisters = FXCollections.observableArrayList();


    public static UserRegisterLists getInstance() {
        if (instance == null)
            instance = new UserRegisterLists();
        return instance;
    }

    private double calculateBalance(){
        if (userRegisters.size() == 0) return 0.0;
        double balance = 0.0;
        for (UserRegister userRegister : userRegisters) {
            if (userRegister.getType().equalsIgnoreCase("Gasto"))
                balance -= userRegister.getAmount();
            else balance += userRegister.getAmount();
        }
        return balance;
    }

    //Getters and setters

    public ObservableList<UserRegister> getUserRegisters() {
        return userRegisters;
    }

    public ObservableList<UserRegister> getURIncome() {
        ObservableList<UserRegister> incomeRegister = FXCollections.observableArrayList();
        for (UserRegister userRegister : userRegisters) {
            if (userRegister.getType().equalsIgnoreCase("Ingreso"))
                incomeRegister.add(userRegister);
        }
        return incomeRegister;
    }

    public ObservableList<UserRegister> getURExpenses() {
        ObservableList<UserRegister> expensesRegister = FXCollections.observableArrayList();
        for (UserRegister userRegister : userRegisters) {
            if (userRegister.getType().equalsIgnoreCase("Gasto"))
                expensesRegister.add(userRegister);
        }
        return expensesRegister;
    }

    public double getBalance(){
        return calculateBalance();
    }
}
