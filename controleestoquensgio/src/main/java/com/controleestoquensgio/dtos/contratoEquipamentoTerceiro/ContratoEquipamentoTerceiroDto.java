package com.controleestoquensgio.dtos.contratoEquipamentoTerceiro;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class ContratoEquipamentoTerceiroDto {
    
    @NotBlank(message = "Insira um fornecedor!")
    private String fornecedor;

    @NotNull(message = "Insira uma data de início!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataInicio;
    @NotNull(message = "Insira uma data final!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataFinal;

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}