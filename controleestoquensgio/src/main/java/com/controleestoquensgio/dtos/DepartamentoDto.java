package com.controleestoquensgio.dtos;

import jakarta.validation.constraints.NotBlank;

import com.controleestoquensgio.models.ColaboradorModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DepartamentoDto{
    
    @NotBlank(message = "Informe uma descrição!")
    protected String descricao;
    
    @NotBlank(message = "Informe a ")
    protected String sigla;
    
    @NotBlank
    protected ColaboradorModel responsavel;
}