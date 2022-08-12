package com.controleestoquensgio.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.controleestoquensgio.models.*;

public class ColaboradorDto{

    @NotBlank(message = "Insira o RF!")
    private String rf;
    
    @NotBlank(message = "Insira um nome!")
    private String nome;

    @NotBlank(message = "Insira uma imagem!")
    private ImagemModel imagem;

    private List <OcorrenciaModel> ocorrencias;
    
    @NotBlank(message = "Insira uma imagem!")
    private TipoAcessoModel tipoAcesso;

    @NotBlank(message = "Insira um tipo de colaborador!")
    private TipoColaboradorModel tipoColaborador;

    @NotBlank(message = "Insira um regime de trabalho!")
    private RegimeTrabalhoModel regimeTrabalho;

    private PresidenciaModel presidencia;
    private DiretoriaModel diretoria;
    private GerenciaModel gerencia;
    private NucleoModel nucleo;
    private List<EmprestimoModel> equipamentosDisponibilizados;
    private List<ContratoComodatoModel> contratosComodato;


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
