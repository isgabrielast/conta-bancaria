package entities;

import entities.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private BigDecimal depositValue;
    private BigDecimal withdrawsValue;
    private LocalDateTime transactionDate;

    private TransactionType transactionType;

    public Transaction() {
    }

    public Transaction(BigDecimal depositValue, LocalDateTime transactionDate) {
        this.depositValue = depositValue;
        this.transactionDate = transactionDate;
    }
    public Transaction(LocalDateTime transactionDate, BigDecimal withdrawsValue){
        this.transactionDate = transactionDate;
        this.withdrawsValue = withdrawsValue;
    }
    public Transaction(BigDecimal withdrawsValue, LocalDateTime transactionDate, TransactionType transactionType) {
        this.withdrawsValue = withdrawsValue;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

    public BigDecimal getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(BigDecimal depositValue) {
        this.depositValue = depositValue;
    }

    public BigDecimal getWithdrawsValue() {
        return withdrawsValue;
    }

    public void setWithdrawsValue(BigDecimal withdrawsValue) {
        this.withdrawsValue = withdrawsValue;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    public void withdrawsValue(){
        if (transactionType == TransactionType.PIX){
            withdrawsValue = withdrawsValue.add(BigDecimal.valueOf(5.45));
        }
        else if(transactionType == TransactionType.TED_COMUM){
            withdrawsValue = withdrawsValue.add(BigDecimal.valueOf(10.45));
        }
        else if(transactionType == TransactionType.TED_PRO){
            withdrawsValue = withdrawsValue.add(BigDecimal.valueOf(20.45));
        }
    }
}
