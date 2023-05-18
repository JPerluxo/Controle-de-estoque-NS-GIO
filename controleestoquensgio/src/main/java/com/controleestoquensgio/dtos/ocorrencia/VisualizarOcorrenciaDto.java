package com.controleestoquensgio.dtos.ocorrencia;


import com.controleestoquensgio.models.OcorrenciaModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarOcorrenciaDto {

    private int id;
    private String os;
    private String descricao;
    private int equipamentoId;
    private int colaboradorId;

    public VisualizarOcorrenciaDto(OcorrenciaModel ocorrenciaModel) {
        this.id = ocorrenciaModel.getId();
        this.os = ocorrenciaModel.getOs();
        this.descricao = ocorrenciaModel.getDescricao();
        this.equipamentoId = ocorrenciaModel.getEquipamento().getId();
        this.colaboradorId = ocorrenciaModel.getColaborador().getId();
    }
}
