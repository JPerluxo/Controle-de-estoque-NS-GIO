package com.controleestoquensgio.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "COLABORADORES")
public class ColaboradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="col_id")
    private int id;
    
    @Column(name="col_rf", nullable = false, length = 30)
    private String rf;
    
    @Column(name="col_nome", nullable = false, length = 200)
    private String nome;

    @ManyToOne
    private ImagemModel imagem;
    
    @ManyToOne
    private TipoAcessoModel tipoAcesso;
    
    @ManyToOne
    private TipoColaboradorModel tipoColaborador;
    
    @ManyToOne
    private RegimeTrabalhoModel regimeTrabalho;

    @OneToOne
    private SetorModel presidencia;

    @OneToOne
    private SetorModel diretoria;

    @OneToOne
    private SetorModel gerencia;

    @OneToOne
    private SetorModel nucleo;

}
