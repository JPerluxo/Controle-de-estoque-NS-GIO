package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

public class LocalizacaoDto{

    @NotBlank
    private String predio;
    
    @NotBlank
    private String andar;
    
    @NotBlank
    private String lado;
    
    private String referencia;

    public String getPredio() {
        return predio;
    }
    public String getReferencia() {
        return referencia;
    }
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public String getLado() {
        return lado;
    }
    public void setLado(String lado) {
        this.lado = lado;
    }
    public String getAndar() {
        return andar;
    }
    public void setAndar(String andar) {
        this.andar = andar;
    }
    public void setPredio(String predio) {
        this.predio = predio;
    }
       
}
