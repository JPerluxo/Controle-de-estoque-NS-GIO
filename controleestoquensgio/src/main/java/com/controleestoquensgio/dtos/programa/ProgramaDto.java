package com.controleestoquensgio.dtos.programa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramaDto {

    @NotBlank(message = "Insira uma descrição!")
    private String descricao;

    private String observacao;

    @Positive(message = "Insira uma licença!")
    private int licencaId;

}
