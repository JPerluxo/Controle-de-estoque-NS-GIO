package com.controleestoquensgio.models;

import jakarta.persistence.*;

@Entity
@Table (name = "TIPOS_EQUIPAMENTO")
public class TipoEquipamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="teq_id")
    private int id;

    @Column(name="teq_descricao", nullable = false, length = 200)
    private String descricao;
    
    @Column(name="teq_marca", nullable = false, length = 200)
    private String marca;

    @Column(name="teq_modelo", nullable = false, length = 200)
    private String modelo;
  
    @Column(name="teq_fornecedor", nullable = false, length = 200)
    private String fornecedor;
    
    @Column(name="teq_polegadas", nullable = false, length = 4)
    private String polegadas;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
