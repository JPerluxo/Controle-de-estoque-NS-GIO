package com.controleestoquensgio.dtos.imagem;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProgramaNaImagemDto {

    @Positive(message = "Insira um programa!")
    private int programaId;
    private int imagemId;

    public AddProgramaNaImagemDto() {}

    public AddProgramaNaImagemDto(int imagemId) {
        this.imagemId = imagemId;
    }

}
