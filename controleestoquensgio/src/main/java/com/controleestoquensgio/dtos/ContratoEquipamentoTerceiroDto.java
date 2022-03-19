package com.controleestoquensgio.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class ContratoEquipamentoTerceiroDto {
    
    @NotBlank
    private String fornecedor;

    @NotBlank
    private Date dataInicio;
    
    @NotBlank
    private Date dataFinal;

    public String getFornecedor() {
        return fornecedor;
    }
    public Date getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

}