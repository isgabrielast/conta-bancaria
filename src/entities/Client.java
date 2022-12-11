package entities;
/**
 * Classe que cria um cliente
 * @author gabrielaxavier
 * @version 1.0
 */
public class Client {
    /**
     * nome do cliente
     */
    private String name;
    /**
     * cpf do cliente
     */
    private String cpf;

    public Client() {
    }

    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
