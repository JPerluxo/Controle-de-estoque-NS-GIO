/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

import com.controleestoquensgio.models.ProgramaModel;

public class ImagemDto {

    @NotBlank(message = "Insira um prédio!")
    private String  descricao;
    
    @NotBlank(message = "Insira ao menos um programa!")
    private List<ProgramaModel> programas;

    public List<ProgramaModel> getProgramas() {
        return programas;
    }
    public void setProgramas(List<ProgramaModel> programas) {
        this.programas = programas;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
}
