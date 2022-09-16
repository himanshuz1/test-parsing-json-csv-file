package com.test.parse.file.dto;

import com.opencsv.bean.CsvBindByName;

import java.util.StringJoiner;

public class OrderDetails {

    private long id;

    private long orderId;

    private String amount;

    private String currency;

    private String comment;

    private String filename;

    private String result;

    public OrderDetails() {
    }

    public OrderDetails(long id, long orderId, String amount, String currency, String comment, String filename, String result) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.filename = filename;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderDetails.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("orderId=" + orderId)
                .add("amount='" + amount + "'")
                .add("currency='" + currency + "'")
                .add("comment='" + comment + "'")
                .add("filename='" + filename + "'")
                .add("result='" + result + "'")
                .toString();
    }
}
