package com.controleestoquensgio.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class ColaboradorDto{

    @NotBlank
    private String rf;
    
    @NotBlank
    private String nome;

    private ImagemDto imagem;
    private List <OcorrenciaDto> ocorrencias;
    private TipoAcessoDto tipoAcesso;
    private TipoColaboradorDto tipoColaborador;
    private RegimeTrabalhoDto regimeTrabalho;
    private PresidenciaDto presidencia;
    private DiretoriaDto diretoria;
    private GerenciaDto gerencia;
    private NucleoDto nucleo;
    private List<EmprestimoDto> equipamentosDisponibilizados;
    private List<ContratoComodatoDto> contratosComodato;


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

    public ImagemDto getImagem() {
        return imagem;
    }

    public void setImagem(ImagemDto imagem) {
        this.imagem = imagem;
    }

    public List<OcorrenciaDto> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<OcorrenciaDto> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public TipoAcessoDto getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(TipoAcessoDto tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public TipoColaboradorDto getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(TipoColaboradorDto tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public RegimeTrabalhoDto getRegimeTrabalho() {
        return regimeTrabalho;
    }

    public void setRegimeTrabalho(RegimeTrabalhoDto regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }

    public List<EmprestimoDto> getEquipamentosDisponibilizados() {
        return equipamentosDisponibilizados;
    }

    public void setEquipamentosDisponibilizados(List<EmprestimoDto> equipamentosDisponibilizados) {
        this.equipamentosDisponibilizados = equipamentosDisponibilizados;
    }

    public List<ContratoComodatoDto> getContratosComodato() {
        return contratosComodato;
    }

    public void setContratosComodato(List<ContratoComodatoDto> contratosComodato) {
        this.contratosComodato = contratosComodato;
    }
    public NucleoDto getNucleo() {
        return nucleo;
    }

    public void setNucleo(NucleoDto nucleo) {
        this.nucleo = nucleo;
    }

    public GerenciaDto getGerencia() {
        return gerencia;
    }

    public void setGerencia(GerenciaDto gerencia) {
        this.gerencia = gerencia;
    }

    public DiretoriaDto getDiretoria() {
        return diretoria;
    }

    public void setDiretoria(DiretoriaDto diretoria) {
        this.diretoria = diretoria;
    }

    public PresidenciaDto getPresidencia() {
        return presidencia;
    }

    public void setPresidencia(PresidenciaDto presidencia) {
        this.presidencia = presidencia;
    }
}
