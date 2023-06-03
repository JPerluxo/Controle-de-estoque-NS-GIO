package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "LOCALIZACOES")
public class LocalizacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="loc_id")
    private int id;

    @Column(name="loc_predio", nullable = false, length = 200)
    private String predio;
    
    @Column(name="loc_andar", nullable = false, length = 200)
    private String andar;
    
    @Column(name="loc_lado", nullable = false, length = 200)
    private String lado;
    
    @Column(name="loc_referencia", length = 200)
    private String referencia;

    @Column(name="loc_ativo", nullable = false, length = 4)
    private String ativo;

    public LocalizacaoModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}
