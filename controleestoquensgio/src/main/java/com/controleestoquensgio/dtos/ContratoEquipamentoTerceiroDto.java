package com.controleestoquensgio.dtos;



import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContratoEquipamentoTerceiroDto {
    
    @NotBlank
    private String fornecedor;

    @NotNull
    private LocalDate dataInicio;
    
    @NotNull
    private LocalDate dataFinal;

    public String getFornecedor() {
        return fornecedor;
    }
    public LocalDate getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

}