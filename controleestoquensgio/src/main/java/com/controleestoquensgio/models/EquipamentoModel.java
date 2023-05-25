package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "EQUIPAMENTOS")
public class EquipamentoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="eqp_id")
    private int id;

    @Column(name="eqp_serial", nullable = false, length = 50)
    private String serial;

    @Column(name="eqp_patrimonio", nullable = false, length = 50)
    private String numPatrimonio;

    @Column(name="eqp_observacao", nullable = false, length = 100)
    private String observacao;

    @ManyToOne
    private TipoEquipamentoModel tipoEquipamento; 
    
    @ManyToOne
    private NotaFiscalModel notaFiscal;

    @ManyToOne
    private LocalizacaoModel localizacao;

    @ManyToOne
    private ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiro;

    @Column(name="eqp_ativo", nullable = false, length = 4)
    private String ativo;

    public EquipamentoModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}
