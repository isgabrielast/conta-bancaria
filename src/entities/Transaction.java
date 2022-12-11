package entities;

import entities.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Classe que simula uma transação
 * @author gabrielaxavier
 * @version 1.0
 */
public class Transaction {
    /**
     * valor do depósito
     */
    private BigDecimal depositValue;
    /**
     * valor de saque
     */
    private BigDecimal withdrawsValue;
    /**
     * data da transação
     */
    private LocalDateTime transactionDate;
    /**
     * @see TransactionType associação de classe enum
     */
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


    public BigDecimal getWithdrawsValue() {
        return withdrawsValue;
    }


    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    
    /**
     * Faz a verificação da opção escolhida como transferência com if
     * e adiciona um valor para cada opção escolhida
     */
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
