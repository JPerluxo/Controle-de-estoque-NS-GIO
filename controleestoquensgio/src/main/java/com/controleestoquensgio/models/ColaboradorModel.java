package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
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
    @JoinColumn(name="col_img_id")
    private ImagemModel imagem;
    
    @ManyToOne
    @JoinColumn(name="col_tac_id")
    private TipoAcessoModel tipoAcesso;
    
    @ManyToOne
    @JoinColumn(name="col_tco_id")
    private TipoColaboradorModel tipoColaborador;
    
    @ManyToOne
    @JoinColumn(name="col_rgt_id")
    private RegimeTrabalhoModel regimeTrabalho;

    @OneToOne
    @JoinColumn(name="col_pre_set_id")
    private SetorModel presidencia;

    @OneToOne
    @JoinColumn(name="col_dir_set_id")
    private SetorModel diretoria;

    @OneToOne
    @JoinColumn(name="col_ger_set_id")
    private SetorModel gerencia;

    @OneToOne
    @JoinColumn(name="col_nuc_set_id")
    private SetorModel nucleo;

    @Column(name="col_ativo", nullable = false, length = 4)
    private String ativo;

    public ColaboradorModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}
