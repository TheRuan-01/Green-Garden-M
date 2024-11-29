package model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Endereco implements Serializable {
    private int idEndereco;
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;

    public Endereco() {
    }

    public Endereco(int idEndereco, String rua, String numero, String complemento, String cep, String cidade, String estado) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s, %s - %s %s", getRua(), getNumero(), getCidade(), getEstado());
    }
}
