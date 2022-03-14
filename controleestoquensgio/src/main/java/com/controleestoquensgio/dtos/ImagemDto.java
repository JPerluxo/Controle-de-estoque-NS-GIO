/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class ImagemDto {

    @NotBlank
    private String  descricao;

    @NotBlank
    private List<ProgramaDto> programas;

    public List<ProgramaDto> getProgramas() {
        return programas;
    }
    public void setProgramas(List<ProgramaDto> programas) {
        this.programas = programas;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
}
