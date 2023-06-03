package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "PROGRAMAS")
public class ProgramaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="prg_id")
    private int id;

    @Column(name="prg_descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name="prg_observacao", length = 200)
    private String observacao;
    
    @ManyToOne
    @JoinColumn(name="prg_lic_id")
    private LicencaModel licenca;

    @Column(name="prg_ativo", nullable = false, length = 4)
    private String ativo;

    public ProgramaModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}
