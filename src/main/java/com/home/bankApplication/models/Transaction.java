package com.home.bankApplication.models;

import java.sql.Date;
import java.util.Objects;

public class Transaction {

    private Integer id;
    private Integer clientId;
    private Integer senderBankAccountId;
    private Integer recipientBankAccountId;
    private Integer currencyId;
    private Double amountOfMoney;
    private Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getSenderBankAccountId() {
        return senderBankAccountId;
    }

    public void setSenderBankAccountId(Integer senderBankAccountId) {
        this.senderBankAccountId = senderBankAccountId;
    }

    public Integer getRecipientBankAccountId() {
        return recipientBankAccountId;
    }

    public void setRecipientBankAccountId(Integer recipientBankAccountId) {
        this.recipientBankAccountId = recipientBankAccountId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(clientId, that.clientId) &&
                Objects.equals(senderBankAccountId, that.senderBankAccountId) &&
                Objects.equals(recipientBankAccountId, that.recipientBankAccountId) &&
                Objects.equals(currencyId, that.currencyId) &&
                Objects.equals(amountOfMoney, that.amountOfMoney) &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, senderBankAccountId, recipientBankAccountId, currencyId, amountOfMoney, creationDate);
    }
}
