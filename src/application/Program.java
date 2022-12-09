package application;

import entities.Account;
import entities.Client;
import entities.Transaction;
import entities.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Program {
    public static final Random random = new Random();
    public static final char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] numbers = "0123456789".toCharArray();
    public static final char[] caracteres = "*&%#@/-+".toCharArray();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);

        System.out.println("Olá!");
        System.out.println("Vamos criar uma conta digital no BankPro?");
        System.out.println("Digite seus dados abaixo e faça seu primeiro depósito.");
        System.out.println("Então você já terá acesso a sua conta!");
        System.out.println();

        System.out.print("Nome completo: ");
        String name = input.nextLine();

        System.out.print("CPF: ");
        String cpf = input.nextLine();

        while (cpf.length() != 11){
            System.out.print("CPF inválido! Digite novamente: ");
            cpf = input.nextLine();
        }

        Client client = new Client(name, cpf);

        int accNumber = (int) (Math.random() * 10000 + 1000);

        String accPassword = passwordGenerator(5);

        System.out.println();
        System.out.print("Qual o valor do primeiro depósito? ");
        BigDecimal firstDeposit = BigDecimal.valueOf(input.nextDouble());

        Account account = new Account(String.valueOf(accNumber), accPassword, firstDeposit, client);
        System.out.println();
        System.out.println("Sua conta foi criada!");
        System.out.println("Anote os dados de acesso: ");
        System.out.print("Nº de conta: ");
        System.out.println(accNumber);
        System.out.print("Senha: ");
        System.out.println(accPassword);
        System.out.println();

        System.out.println("Agora acesse novamente sua conta digitando o seu Nº de conta e sua Senha!");
        System.out.print("Nº de conta: ");
        input.nextLine();
        String accNum = input.nextLine();
        System.out.print("Senha: ");
        String accPass = input.nextLine();

        while (!account.passwordVerification(accNum, accPass)){
            System.out.println("Dados errados! Digite novamente: ");
            accNum = input.nextLine();
            accPass = input.nextLine();
        }

        System.out.println();

        int c;
        do {
        System.out.println("O que você deseja fazer?");
        System.out.print("SAQUE(1) - DEPOSITO(2) - TRANSFERÊNCIA(3) ");
        int option1 = input.nextInt();
        System.out.println();

        if (option1 == 1 || option1 == 2){
            if (option1 == 1){
                System.out.print("Quantos saques você deseja fazer?");
                int amountWithdraw = input.nextInt();
                for (int i = 0; i < amountWithdraw; i++){
                    System.out.print("Digite o valor: ");
                    BigDecimal transactionValue = BigDecimal.valueOf(input.nextDouble());
                    while (account.getBalanceAccount().compareTo(transactionValue) < 0){
                        System.out.println("Saldo insuficiente!");
                        System.out.printf("Seu saldo atual é: R$ %.2f%n",  account.getBalanceAccount());
                        System.out.print("Digite novamente o valor do saque: ");
                        transactionValue = BigDecimal.valueOf(input.nextDouble());
                    }
                    account.withdraw(transactionValue);
                    Transaction transaction = new Transaction(LocalDateTime.now(), transactionValue);
                    account.add(transaction);
                }
            }
            else {
                System.out.println("Quantos depósitos você deseja fazer?");
                int amountDeposit = input.nextInt();
                for (int i = 0; i < amountDeposit; i++){
                    System.out.print("Digite o valor: ");
                    BigDecimal transactionValue = BigDecimal.valueOf(input.nextDouble());
                    account.deposit(transactionValue);
                    Transaction transaction = new Transaction(transactionValue, LocalDateTime.now());
                    account.add(transaction);
                }
            }
        }
        else {
            System.out.print("Qual o tipo de transferência? PIX (1) : TED_COMUM (2) : TED_PRO (3) ");
            int option2 = input.nextInt();
            if (option2 == 1) {
                System.out.print("Quantos PIX você deseja fazer? ");
                int amountPix = input.nextInt();
                for (int i =0; i < amountPix; i++){
                    System.out.print("Digite o valor do seu PIX: ");
                    BigDecimal transactionValue = BigDecimal.valueOf(input.nextDouble());
                    while (account.getBalanceAccount().compareTo(transactionValue) < 0){
                        System.out.println("Saldo insuficiente!");
                        System.out.printf("Seu saldo atual é: R$ %.2f %n", account.getBalanceAccount());
                        System.out.print("Digite novamente o valor do PIX: ");
                        transactionValue = BigDecimal.valueOf(input.nextDouble());
                    }
                    Transaction transaction = new Transaction(transactionValue, LocalDateTime.now(),
                            TransactionType.PIX);
                    transaction.withdrawsValue();
                    account.add(transaction);
                    account.withdraw(transaction.getWithdrawsValue());

                }

            }
            else if (option2 == 2) {
                System.out.print("Quantos TED_COMUM você deseja fazer? ");
                int amountTedC = input.nextInt();
                for (int i =0; i < amountTedC; i++){
                    System.out.print("Digite o valor do seu TED_COMUM: ");
                    BigDecimal transactionValue = BigDecimal.valueOf(input.nextDouble());
                    while (account.getBalanceAccount().compareTo(transactionValue) < 0){
                        System.out.println("Saldo insuficiente!");
                        System.out.printf("Seu saldo atual é: R$ %.2f%n", account.getBalanceAccount());
                        System.out.print("Digite novamente o valor do TED_COMUM: ");
                        transactionValue = BigDecimal.valueOf(input.nextDouble());
                    }
                    Transaction transaction = new Transaction(transactionValue, LocalDateTime.now(),
                            TransactionType.TED_COMUM);
                    transaction.withdrawsValue();
                    account.add(transaction);
                    account.withdraw(transaction.getWithdrawsValue());
                }
            }
            else {
                System.out.print("Quantos TED_PRO você deseja fazer? ");
                int amountTedP = input.nextInt();
                for (int i =0; i < amountTedP; i++){
                    System.out.print("Digite o valor do seu TED_PRO: ");
                    BigDecimal transactionValue = BigDecimal.valueOf(input.nextDouble());
                    while (account.getBalanceAccount().compareTo(transactionValue) < 0){
                        System.out.println("Saldo insuficiente!");
                        System.out.printf("Seu saldo atual é: R$ %.2f%n", account.getBalanceAccount());
                        System.out.print("Digite novamente o valor do TED_PRO: ");
                        transactionValue = BigDecimal.valueOf(input.nextDouble());
                    }
                    Transaction transaction = new Transaction(transactionValue, LocalDateTime.now(),
                            TransactionType.TED_PRO);
                    transaction.withdrawsValue();
                    account.add(transaction);
                    account.withdraw(transaction.getWithdrawsValue());
                }
            }
        }
            System.out.println("Deseja fazer mais uma transação? SIM (1) : NÃO (2)");
            c = input.nextInt();
        }while (c != 2);

        System.out.println();
        System.out.print("Você deseja visualizar seu extrato? SIM (1) : NÃO (2) ");
        int option3 = input.nextInt();
        System.out.println();

        if (option3 == 1){
            System.out.println("Data Extrato: " + LocalDateTime.now().format(Account.FMT));
            System.out.println(account);
        }
        
        input.close();
    }
    public static String passwordGenerator(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< n; i++) {

            int x = random.nextInt(letters.length);
            int y = random.nextInt(numbers.length);
            int z = random.nextInt(caracteres.length);

            if (i % 2 == 0) {
                sb.append(String.format("%s", letters[x]).toUpperCase());
                sb.append(numbers[y]);
            }
            else {
                sb.append(String.format("%s", letters[x]).toLowerCase());
                sb.append(caracteres[z]);
            }

        }
        return sb.toString();
    }
}
