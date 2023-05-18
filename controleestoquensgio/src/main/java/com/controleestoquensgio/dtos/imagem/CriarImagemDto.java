/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos.imagem;

import jakarta.validation.constraints.NotBlank;

public class CriarImagemDto {

    @NotBlank(message = "Insira uma descriçao!")
    private String  descricao;

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
}
