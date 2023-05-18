package com.controleestoquensgio.dtos.programa;


import com.controleestoquensgio.models.ProgramaModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarProgramaDto {

    private int id;
    private String descricao;

    private String observacao;

    private int licencaId;

    public VisualizarProgramaDto(ProgramaModel programaModel) {
        this.id = programaModel.getId();
        this.descricao = programaModel.getDescricao();
        this.observacao = programaModel.getObservacao();
        this.licencaId = programaModel.getLicenca().getId();
    }
}
