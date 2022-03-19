package com.controleestoquensgio.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class NotaFiscalDto {

    @NotBlank
    private String numero;
    
    @NotBlank
    private Date date;

    public NotaFiscalDto(){
        this.numero = " "; 
    }
    public NotaFiscalDto(String numero, Date date){
        this.numero = numero;
        this.date = date; 
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    

}
