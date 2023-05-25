package com.controleestoquensgio.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoDto {

    @NotBlank(message = "Insira a descrição!")
    private String descricao;
}
