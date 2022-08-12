package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

public class TipoEquipamentoDto {
    
    @NotBlank(message = "Insira uma descrição!")
    private String descricao;
    
    @NotBlank(message = "Insira uma marca!")
    private String marca;

    @NotBlank(message = "Insira uma modelo!")
    private String modelo;
    
    @NotBlank
    private String fornecedor;
    
    private String polegadas;

    public String getDescricao() {
        return descricao;
    }
    public String getPolegadas() {
        return polegadas;
    }
    public void setPolegadas(String polegadas) {
        this.polegadas = polegadas;
    }
    public String getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
