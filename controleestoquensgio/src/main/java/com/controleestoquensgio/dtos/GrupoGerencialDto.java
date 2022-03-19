package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

public abstract class GrupoGerencialDto{
    
    @NotBlank
    protected String descricao;
    
    @NotBlank
    protected String sigla;
    
    @NotBlank
    protected ColaboradorDto responsavel;

    public String getDescricao() {
        return descricao;
    }
    public ColaboradorDto getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(ColaboradorDto responsavel) {
        this.responsavel = responsavel;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
}