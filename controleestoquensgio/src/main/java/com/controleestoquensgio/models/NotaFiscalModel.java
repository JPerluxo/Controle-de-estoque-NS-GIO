package com.controleestoquensgio.models;

import java.time.LocalDate;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "NOTAS_FISCAIS")
public class NotaFiscalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="nof_id")
    private int id;

    @Column(name="nof_numero", nullable = false, length = 20)
    private String numero;
    
    @Column(name="nof_dt")
    private LocalDate data;

    @Column(name="nof_ativo", nullable = false, length = 4)
    private String ativo;

    public NotaFiscalModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}
