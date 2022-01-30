package com.home.bankApplication.models;

import java.util.Objects;

public class BankAccount {

    private int id;
    private Integer currencyId;
    private Double amountOfMoney;
    private Integer bankId;
    private Integer clientId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(currencyId, that.currencyId) &&
                Objects.equals(amountOfMoney, that.amountOfMoney) &&
                Objects.equals(bankId, that.bankId) &&
                Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currencyId, amountOfMoney, bankId, clientId);
    }
}
