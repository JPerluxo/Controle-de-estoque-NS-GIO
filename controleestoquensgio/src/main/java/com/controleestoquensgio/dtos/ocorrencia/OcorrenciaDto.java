package com.controleestoquensgio.dtos.ocorrencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaDto {

    @NotBlank(message = "Insira uma OS!")
    private String os;
    
    @NotBlank(message = "Insira uma descrição!")
    private String descricao;
    
    @Positive(message = "Insira um equipamento!")
    private int equipamentoId;
    
    @Positive(message = "Insira um colaborador!")
    private int colaboradorId;

}
