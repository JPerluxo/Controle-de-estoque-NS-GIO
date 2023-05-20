package com.controleestoquensgio.dtos.setor;

import com.controleestoquensgio.models.SetorModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarSetorDto {

    private int id;
    private String sigla;
    private String descricao;
    private int responsavelId;
    private String nivel;

    public VisualizarSetorDto(SetorModel setorModel) {
        this.id = setorModel.getId();
        this.sigla = setorModel.getSigla();
        this.descricao = setorModel.getDescricao();
        this.responsavelId = setorModel.getResponsavel().getId();
        this.nivel = setorModel.getNivel();
    }
}
