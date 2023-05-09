package com.controleestoquensgio.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class NotaFiscalDto {

    @NotBlank(message = "Insira um número!")
    private String numero;

    @NotNull(message = "Insira uma data!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
