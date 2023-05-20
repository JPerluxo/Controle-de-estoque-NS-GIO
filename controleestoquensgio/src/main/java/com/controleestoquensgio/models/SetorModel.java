package com.controleestoquensgio.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SETORES")
public class SetorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="set_id")
    private int id;

    @Column(name="set_sigla", nullable = false, length = 50)
    private String sigla;

    @Column(name="set_descricao", nullable = false, length = 200)
    private String descricao;

    @ManyToOne
    private ColaboradorModel responsavel;

    @Column(name="set_nivel", nullable = false, length = 50)
    private String nivel;

}
