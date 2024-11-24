package model;

import java.io.Serializable;

public class Produto implements Serializable {

    public int  imgProduto;
    public String nomeProduto;
    public String descricaoProduto;
    public String preco;

    public Produto (int imgProduto, String nomeProduto, String descricaoProduto, String preco){

        this.imgProduto = imgProduto;
        this.nomeProduto = nomeProduto;
        this.descricaoProduto = descricaoProduto;
        this.preco = preco;

    }

    public int getImgProduto() {
        return imgProduto;
    }

    public void setImgProduto(int imgProduto) {
        this.imgProduto = imgProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
