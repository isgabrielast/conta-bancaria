package entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String numberAccount;
    private String passwordAccount;
    private BigDecimal balanceAccount;

    private Client client;
    public static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    List <Transaction> transactions = new ArrayList<>();

    public Account() {
    }

    public Account(String numberAccount, String passwordAccount,
                   BigDecimal balanceAccount, Client client) {
        this.numberAccount = numberAccount;
        this.passwordAccount = passwordAccount;
        this.balanceAccount = balanceAccount;
        this.client = client;
    }

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public BigDecimal getBalanceAccount() {
        return balanceAccount;
    }

    public void setBalanceAccount(BigDecimal balanceAccount) {
        this.balanceAccount = balanceAccount;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void add(Transaction transaction){
        transactions.add(transaction);
    }
    public void deposit(BigDecimal depositValue){
         balanceAccount = balanceAccount.add(depositValue);
    }
    public void withdraw(BigDecimal withdrawValue){
        balanceAccount = balanceAccount.subtract(withdrawValue);

    }
    public boolean passwordVerification(String numberAccount, String passwordAccount){
        return this.numberAccount.equals(numberAccount) && this.passwordAccount.equals(passwordAccount);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(client.getName()).append(" - ").append(client.getCpf()).append("\n");

        for (Transaction list : transactions){
            if (list.getDepositValue() != null){
                sb.append("Entradas: ").append("\n");
                sb.append(String.format("R$ %.2f", list.getDepositValue())).append(" - ");
                sb.append(list.getTransactionDate().format(FMT)).append("\n");

            }
            if (list.getWithdrawsValue() != null){
                sb.append("Saídas: ").append("\n");
                sb.append(String.format("R$ %.2f", list.getWithdrawsValue())).append(" - ");
                sb.append(list.getTransactionDate().format(FMT)).append("\n");
            }
        }
        sb.append(String.format("Saldo da conta: R$ %.2f ", balanceAccount));
        return sb.toString();
    }
}
