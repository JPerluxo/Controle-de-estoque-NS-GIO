package com.controleestoquensgio.models;

import java.util.List;

import javax.persistence.*;

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
    
    @OneToMany
    private List<OcorrenciaModel> ocorrencias;
    
    @ManyToOne
    private TipoAcessoModel tipoAcesso;
    
    @ManyToOne
    private TipoColaboradorModel tipoColaborador;
    
    @ManyToOne
    private RegimeTrabalhoModel regimeTrabalho;
    
    @ManyToOne
    private PresidenciaModel presidencia;
    
    @ManyToOne
    private DiretoriaModel diretoria;
    
    @ManyToOne
    private GerenciaModel gerencia;

    @ManyToOne
    private NucleoModel nucleo;

    @ManyToMany
    private List<EmprestimoModel> equipamentosDisponibilizados;
    
    @ManyToMany
    private List<ContratoComodatoModel> contratosComodato;

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
    public List<OcorrenciaModel> getOcorrencias() {
        return ocorrencias;
    }
    public void setOcorrencias(List<OcorrenciaModel> ocorrencias) {
        this.ocorrencias = ocorrencias;
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
    public List<EmprestimoModel> getEquipamentosDisponibilizados() {
        return equipamentosDisponibilizados;
    }
    public void setEquipamentosDisponibilizados(List<EmprestimoModel> equipamentosDisponibilizados) {
        this.equipamentosDisponibilizados = equipamentosDisponibilizados;
    }
    public List<ContratoComodatoModel> getContratosComodato() {
        return contratosComodato;
    }
    public void setContratosComodato(List<ContratoComodatoModel> contratosComodato) {
        this.contratosComodato = contratosComodato;
    }
    public NucleoModel getNucleo() {
        return nucleo;
    }
    public void setNucleo(NucleoModel nucleo) {
        this.nucleo = nucleo;
    }
    public GerenciaModel getGerencia() {
        return gerencia;
    }
    public void setGerencia(GerenciaModel gerencia) {
        this.gerencia = gerencia;
    }
    public DiretoriaModel getDiretoria() {
        return diretoria;
    }
    public void setDiretoria(DiretoriaModel diretoria) {
        this.diretoria = diretoria;
    }
    public PresidenciaModel getPresidencia() {
        return presidencia;
    }
    public void setPresidencia(PresidenciaModel presidencia) {
        this.presidencia = presidencia;
    }
}
