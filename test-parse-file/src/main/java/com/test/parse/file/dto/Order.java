package com.test.parse.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

import java.util.StringJoiner;

public class Order {

    @CsvBindByName(column = "orderId")
    @JsonProperty("orderId")
    private long id;

    @CsvBindByName(column = "amount")
    private String amount;

    @CsvBindByName(column = "currency")
    private String currency;

    @CsvBindByName(column = "comment")
    private String comment;

    public Order() {
    }

    public Order(long id, String amount, String currency, String comment) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("amount='" + amount + "'")
                .add("currency='" + currency + "'")
                .add("comment='" + comment + "'")
                .toString();
    }
}