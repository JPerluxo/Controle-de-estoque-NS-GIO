package com.controleestoquensgio.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;

public class NotaFiscalDto {

    @NotBlank(message = "Insira um número!")
    private String numero;
    
    @NotBlank(message = "Insira uma data!")
    private LocalDate data;

    public void setLocalDate(LocalDate data) {
        this.data = data;
    }
    public LocalDate getLocalDate() {
        return data;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    

}
