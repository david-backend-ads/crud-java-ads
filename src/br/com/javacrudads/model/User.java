package br.com.javacrudads.model;

public class User {

    // Private mantém o dado em encapsulamento
    private long id;
    private String nome;
    private String cpf;


    // Getters, para devolver o valor de um atributo
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;  // "this.nome" é o atributo do objeto; "nome" é o valor que você passou ao chamar o método
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;    // mesmo conceito: atribui o valor passado ao atributo do objeto
    }

}
