package com.controleestoquensgio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ColaboradorDto{

    @NotBlank(message = "Insira o RF!")
    private String rf;
    
    @NotBlank(message = "Insira um nome!")
    private String nome;

    @Positive(message = "Insira uma imagem!")
    private int imagemId;

    @Positive(message = "Insira um tipo de acesso!")
    private int tipoAcessoId;

    @Positive(message = "Insira um tipo de colaborador!")
    private int tipoColaboradorId;

    @Positive(message = "Insira um regime de trabalho!")
    private int regimeTrabalhoId;

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

    public int getImagemId() {
        return imagemId;
    }

    public void setImagemId(int imagemId) {
        this.imagemId = imagemId;
    }

    public int getTipoAcessoId() {
        return tipoAcessoId;
    }

    public void setTipoAcessoId(int tipoAcessoId) {
        this.tipoAcessoId = tipoAcessoId;
    }

    public int getTipoColaboradorId() {
        return tipoColaboradorId;
    }

    public void setTipoColaboradorId(int tipoColaboradorId) {
        this.tipoColaboradorId = tipoColaboradorId;
    }

    public int getRegimeTrabalhoId() {
        return regimeTrabalhoId;
    }

    public void setRegimeTrabalhoId(int regimeTrabalhoId) {
        this.regimeTrabalhoId = regimeTrabalhoId;
    }
}
