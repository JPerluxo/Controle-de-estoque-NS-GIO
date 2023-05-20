package com.controleestoquensgio.dtos.setor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetorDto {

    @NotBlank(message = "Insira uma sigla!")
    private String sigla;

    @NotBlank(message = "Insira uma descrição!")
    private String descricao;

    @Positive(message = "Insira um responsável!")
    private int responsavelId;

    @NotBlank(message = "Insira um nível!")
    private String nivel;

}
