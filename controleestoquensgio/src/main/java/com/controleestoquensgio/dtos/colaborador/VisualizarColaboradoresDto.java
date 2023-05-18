package com.controleestoquensgio.dtos.colaborador;

import com.controleestoquensgio.models.ColaboradorModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarColaboradoresDto {

    private int id;
    private String rf;
    private String nome;
    private int imagemId;
    private int tipoAcessoId;
    private int tipoColaboradorId;
    private int regimeTrabalhoId;

    public VisualizarColaboradoresDto(ColaboradorModel colaboradorModel) {
        this.id = colaboradorModel.getId();
        this.rf = colaboradorModel.getRf();
        this.nome = colaboradorModel.getNome();
        this.imagemId = colaboradorModel.getImagem().getId();
        this.tipoAcessoId = colaboradorModel.getTipoAcesso().getId();
        this.tipoColaboradorId = colaboradorModel.getTipoColaborador().getId();
        this.regimeTrabalhoId = colaboradorModel.getRegimeTrabalho().getId();
    }
}
