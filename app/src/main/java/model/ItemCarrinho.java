package model;


import java.io.Serializable;

public class ItemCarrinho implements Serializable {
    private int idItemCarrinho;
    private int quantidade;
    private double precoUnitario;
    private double precoTotal;
    private String status;
    private int produtoId;
    private Produto produto;
    private int carrinhoId;

    public ItemCarrinho(int quantidade, double precoUnitario, double precoTotal, String status, int produtoId, Produto produto, int carrinhoId, String carrinho) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoTotal;
        this.status = status;
        this.produtoId = produtoId;
        this.produto = produto;
        this.carrinhoId = carrinhoId;
        this.carrinho = carrinho;
    }

    public ItemCarrinho() {
    }

    private String carrinho;

    // Getters e Setters
    public int getIdItemCarrinho() {
        return idItemCarrinho;
    }

    public void setIdItemCarrinho(int idItemCarrinho) {
        this.idItemCarrinho = idItemCarrinho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getPrecoTotal() {
        return precoUnitario * quantidade;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(int carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

    public String getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(String carrinho) {
        this.carrinho = carrinho;
    }

}