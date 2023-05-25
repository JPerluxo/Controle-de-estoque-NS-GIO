package com.controleestoquensgio.dtos.ocorrencia;

import com.controleestoquensgio.models.OcorrenciaModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarOcorrenciasDto {

    private int id;

    private String os;
    private String descricao;

    private String equipamento;

    private String colaborador;
    private String ativo;
    public ListarOcorrenciasDto(OcorrenciaModel ocorrenciaModel) {
        this.id = ocorrenciaModel.getId();
        this.os = ocorrenciaModel.getOs();
        this.descricao = ocorrenciaModel.getDescricao();
        this.equipamento = ocorrenciaModel.getEquipamento().getNumPatrimonio();
        this.colaborador = ocorrenciaModel.getColaborador().getNome();
        this.ativo = ocorrenciaModel.getAtivo();
    }
}
