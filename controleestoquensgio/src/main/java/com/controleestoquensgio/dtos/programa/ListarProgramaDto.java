package com.controleestoquensgio.dtos.programa;

import com.controleestoquensgio.models.ProgramaModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarProgramaDto {

    private int id;

    private String descricao;

    private String observacao;

    private String licenca;
    private String ativo;
    public ListarProgramaDto(ProgramaModel programaModel) {
        this.id = programaModel.getId();
        this.descricao = programaModel.getDescricao();
        this.observacao = programaModel.getObservacao();
        this.licenca = programaModel.getLicenca().getDescricao();
        this.ativo = programaModel.getAtivo();
    }

    public ListarProgramaDto() {}

}
