package com.example.coursach.dto;

public class TransactionDto {
    private String card_source;
    private String card_destination;
    private String card_source_exp_date;
    private int card_source_cvv;
    private String amount;

    public String getCard_source() {
        return card_source;
    }

    public TransactionDto setCard_source(String card_source) {
        this.card_source = card_source;
        return this;
    }

    public String getCard_destination() {
        return card_destination;
    }

    public TransactionDto setCard_destination(String card_destination) {
        this.card_destination = card_destination;
        return this;
    }

    public String getCard_source_exp_date() {
        return card_source_exp_date;
    }

    public TransactionDto setCard_source_exp_date(String card_source_exp_date) {
        this.card_source_exp_date = card_source_exp_date;
        return this;
    }

    public int getCard_source_cvv() {
        return card_source_cvv;
    }

    public TransactionDto setCard_source_cvv(int card_source_cvv) {
        this.card_source_cvv = card_source_cvv;
        return this;
    }

    public String getAmount() {
        return amount;
    }

    public TransactionDto setAmount(String amount) {
        this.amount = amount;
        return this;
    }
}
