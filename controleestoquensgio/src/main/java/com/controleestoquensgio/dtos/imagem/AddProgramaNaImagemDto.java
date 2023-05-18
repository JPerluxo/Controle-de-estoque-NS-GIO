package com.controleestoquensgio.dtos.imagem;

import jakarta.validation.constraints.Positive;

public class AddProgramaNaImagemDto {

    @Positive(message = "Insira um programa!")
    private int programaId;
    private int imagemId;

    public AddProgramaNaImagemDto() {}

    public AddProgramaNaImagemDto(int imagemId) {
        this.imagemId = imagemId;
    }

    public int getProgramaId() {
        return programaId;
    }

    public void setProgramaId(int programaId) {
        this.programaId = programaId;
    }

    public int getImagemId() {
        return imagemId;
    }

    public void setImagemId(int imagemId) {
        this.imagemId = imagemId;
    }
}
