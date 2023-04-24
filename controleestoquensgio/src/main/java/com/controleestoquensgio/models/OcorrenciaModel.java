package com.controleestoquensgio.models;

import jakarta.persistence.*;

@Entity
@Table (name = "OCORRENCIAS")
public class OcorrenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="oco_id")
    private int id;

    @Column(name="oco_os", nullable = false, length = 20)
    private String os;
    
    @Column(name="oco_descricao", nullable = false, length = 150)
    private String descricao;
    
    @ManyToOne
    private EquipamentoModel equipamento;
    
    @ManyToOne
    private ColaboradorModel colaborador;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getOs() {
        return os;
    }
    public void setOs(String os) {
        this.os = os;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public EquipamentoModel getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(EquipamentoModel equipamento) {
        this.equipamento = equipamento;
    }
    public ColaboradorModel getColaborador() {
        return colaborador;
    }
    public void setColaborador(ColaboradorModel colaborador) {
        this.colaborador = colaborador;
    }
}
