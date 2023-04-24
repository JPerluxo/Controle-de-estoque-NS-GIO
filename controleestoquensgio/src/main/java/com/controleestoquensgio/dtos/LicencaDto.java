/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos;

import jakarta.validation.constraints.NotBlank;

public class LicencaDto {

    @NotBlank(message = "Insira uma descrição!")
    private String descricao;

    public void setDescricao (String descricao){
        this.descricao = descricao;
    }
    public String getDescricao (){
        return this.descricao;
    }
}