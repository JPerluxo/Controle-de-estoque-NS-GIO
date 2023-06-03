package com.controleestoquensgio.models;

import com.controleestoquensgio.dtos.tipoEquipamento.FiltrarTipoEquipamentoDto;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @Column(name="teq_ativo", nullable = false, length = 4)
    private String ativo;

    public TipoEquipamentoModel () {
        this.ativo = SimOuNao.SIM.name();
    }

    public TipoEquipamentoModel (FiltrarTipoEquipamentoDto filtrarTipoEquipamentoDto) {
        this.descricao = filtrarTipoEquipamentoDto.getDescricao();
        this.marca = filtrarTipoEquipamentoDto.getMarca();
        this.modelo = filtrarTipoEquipamentoDto.getModelo();
        this.fornecedor = filtrarTipoEquipamentoDto.getFornecedor();
        this.polegadas = filtrarTipoEquipamentoDto.getPolegadas();
        this.ativo = filtrarTipoEquipamentoDto.getAtivo();
    }
}
