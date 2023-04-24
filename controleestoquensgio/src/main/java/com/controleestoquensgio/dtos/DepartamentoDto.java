package com.controleestoquensgio.dtos;

import jakarta.validation.constraints.NotBlank;

import com.controleestoquensgio.models.ColaboradorModel;

public abstract class DepartamentoDto{
    
    @NotBlank(message = "Informe uma descrição!")
    protected String descricao;
    
    @NotBlank(message = "Informe a ")
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