package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    @JoinColumn(name="oco_eqp_id")
    private EquipamentoModel equipamento;
    
    @ManyToOne
    @JoinColumn(name="oco_col_id")
    private ColaboradorModel colaborador;

    @Column(name="oco_ativo", nullable = false, length = 4)
    private String ativo;

    public OcorrenciaModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}
