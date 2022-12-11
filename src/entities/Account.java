package entities;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que simula uma conta bancária
 * @author gabrielaxavier
 * @version 1.0
 */
public class Account {
    /**
     * número da conta
     */
    private String numberAccount;
    /**
     * senha
     */
    private String passwordAccount;
    /**
     * saldo
     */
    private BigDecimal balanceAccount;
    /**
     * @see Client associação de cliente
     */
    private Client client;
    /**
     * formatação para usar em datas
     */
    public static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /**
     @see Transaction associação de Transaction
     */
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

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    /**
     * Adiciona um objeto transaction a lista de transações de account
     * @param transaction add o objeto de transactions
     */
    public void add(Transaction transaction){
        transactions.add(transaction);
    }
    /**
     * Adiciona um valor ao saldo
     *  @param depositValue valor que será add
     */
    public void deposit(BigDecimal depositValue){
         balanceAccount = balanceAccount.add(depositValue);
    }
    /**
     * Subtrai um valor do saldo
     * @param withdrawValue valor que será subtraido
     */
    public void withdraw(BigDecimal withdrawValue){
        balanceAccount = balanceAccount.subtract(withdrawValue);

    }
    /**
     * Faz a verificação se os dados de senha e conta digitados pelo usário estão corretos
     * @param numberAccount numero da conta
     * @param passwordAccount senha da conta
     * @return retorna um boolean se a condição é verdadeira ou falsa
     */
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
