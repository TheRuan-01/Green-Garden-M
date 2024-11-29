package model;


import java.io.Serializable;
import java.util.List;

public class Cliente implements Serializable {
    private int idCliente;
    private String nome;

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEnderecoId(int enderecoId) {
        this.enderecoId = enderecoId;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setCarrinhos(List<Carrinho> carrinhos) {
        this.carrinhos = carrinhos;
    }

    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private int enderecoId;
    private Endereco endereco;
    private List<Pedido> pedidos;
    private List<Carrinho> carrinhos;

    public Cliente() {
    }

    public Cliente(int idCliente, String nome, String cpf, String telefone, String email, String senha, int enderecoId, Endereco endereco, List<Pedido> pedidos, List<Carrinho> carrinhos) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.enderecoId = enderecoId;
        this.endereco = endereco;
        this.pedidos = pedidos;
        this.carrinhos = carrinhos;
    }

    public Cliente(int idCliente, int enderecoId, String senha, String email, String telefone, String cpf, String nome) {
        this.idCliente = idCliente;
        this.enderecoId = enderecoId;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.nome = nome;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getEnderecoId() {
        return enderecoId;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Carrinho> getCarrinhos() {
        return carrinhos;
    }
}