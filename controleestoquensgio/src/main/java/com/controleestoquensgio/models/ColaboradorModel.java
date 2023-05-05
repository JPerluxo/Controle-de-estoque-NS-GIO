package com.controleestoquensgio.models;

import jakarta.persistence.*;

@Entity
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

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getRf() {
        return rf;
    }
    public void setRf(String rf) {
        this.rf = rf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public ImagemModel getImagem() {
        return imagem;
    }
    public void setImagem(ImagemModel imagem) {
        this.imagem = imagem;
    }
    public TipoAcessoModel getTipoAcesso() {
        return tipoAcesso;
    }
    public void setTipoAcesso(TipoAcessoModel tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
    public TipoColaboradorModel getTipoColaborador() {
        return tipoColaborador;
    }
    public void setTipoColaborador(TipoColaboradorModel tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }
    public RegimeTrabalhoModel getRegimeTrabalho() {
        return regimeTrabalho;
    }
    public void setRegimeTrabalho(RegimeTrabalhoModel regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }
}
