package com.controleestoquensgio.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class ContratoComodatoDto{
    @NotBlank
    private Date date; 
    
    @NotBlank
    private String caminhoArquivo;
    
    @NotBlank
    private String status;

    public Date getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }
    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }
    public void setDate(Date date) {
        this.date = date;
    } 

    
}