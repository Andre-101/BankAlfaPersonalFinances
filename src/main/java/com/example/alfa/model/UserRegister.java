package com.example.alfa.model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserRegister{

    //Attributes
    private String type;
    private double amount;
    private String amountFormat;
    private String description;
    private Long date;
    private String dateFormat;
    private Long dateSaved;

    //Builder
    public UserRegister(String type, double amount, String description, long date) {
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        this.type = type;
        this.amount = amount;
        amountFormat = moneyFormat.format(amount);
        if (type.equalsIgnoreCase("GASTO"))
            amountFormat = "-" + amountFormat;
        this.description = description;
        this.date = date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat = sdf.format(new Date(date));
        dateSaved = System.currentTimeMillis();
    }



    //Getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getAmountFormat() {
        return amountFormat;
    }

    public void setAmountFormat(String amountFormat) {
        this.amountFormat = amountFormat;
    }

    public Long getDateSaved() {
        return dateSaved;
    }
}
