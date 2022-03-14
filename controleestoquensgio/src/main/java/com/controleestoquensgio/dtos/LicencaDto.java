/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

public class LicencaDto {

    @NotBlank
    private String descricao;

    public void setDescricao (String descricao){
        this.descricao = descricao;
    }
    public String getDescricao (){
        return this.descricao;
    }
}