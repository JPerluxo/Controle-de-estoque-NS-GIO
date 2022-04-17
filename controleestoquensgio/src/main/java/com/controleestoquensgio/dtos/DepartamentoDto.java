package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

import com.controleestoquensgio.models.ColaboradorModel;

public abstract class DepartamentoDto{
    
    @NotBlank
    protected String descricao;
    
    @NotBlank
    protected String sigla;
    
    @NotBlank
    protected ColaboradorModel responsavel;

    public String getDescricao() {
        return descricao;
    }
    public ColaboradorModel getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(ColaboradorModel responsavel) {
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