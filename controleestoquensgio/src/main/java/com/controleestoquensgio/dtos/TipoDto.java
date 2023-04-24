package com.controleestoquensgio.dtos;

import jakarta.validation.constraints.NotBlank;

public class TipoDto {

    @NotBlank(message = "Insira a descrição!")
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
