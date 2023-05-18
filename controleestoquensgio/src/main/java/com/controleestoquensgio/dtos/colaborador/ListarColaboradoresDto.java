package com.controleestoquensgio.dtos.colaborador;

import com.controleestoquensgio.models.ColaboradorModel;

public class ListarColaboradoresDto {

    private int id;
    private String rf;

    private String nome;

    private String imagem;

    private String tipoAcesso;

    private String tipoColaborador;

    private String regimeTrabalho;

    public ListarColaboradoresDto(ColaboradorModel colaboradorModel) {
        this.id = colaboradorModel.getId();
        this.rf = colaboradorModel.getRf();
        this.nome = colaboradorModel.getNome();
        this.imagem = colaboradorModel.getImagem().getDescricao();
        this.tipoAcesso = colaboradorModel.getTipoAcesso().getDescricao();
        this.tipoColaborador = colaboradorModel.getTipoColaborador().getDescricao();
        this.regimeTrabalho = colaboradorModel.getRegimeTrabalho().getDescricao();
    }

    public ListarColaboradoresDto() {}

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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(String tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    public String getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(String tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public String getRegimeTrabalho() {
        return regimeTrabalho;
    }

    public void setRegimeTrabalho(String regimeTrabalho) {
        this.regimeTrabalho = regimeTrabalho;
    }
}
