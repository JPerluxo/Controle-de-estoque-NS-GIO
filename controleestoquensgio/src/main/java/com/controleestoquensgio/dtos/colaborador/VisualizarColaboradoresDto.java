package com.controleestoquensgio.dtos.colaborador;

import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.models.SetorModel;
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
    private int presidenciaId;
    private int diretoriaId;
    private int gerenciaId;
    private int nucleoId;

    public VisualizarColaboradoresDto(ColaboradorModel colaboradorModel) {
        this.id = colaboradorModel.getId();
        this.rf = colaboradorModel.getRf();
        this.nome = colaboradorModel.getNome();
        this.imagemId = colaboradorModel.getImagem().getId();
        this.tipoAcessoId = colaboradorModel.getTipoAcesso().getId();
        this.tipoColaboradorId = colaboradorModel.getTipoColaborador().getId();
        this.regimeTrabalhoId = colaboradorModel.getRegimeTrabalho().getId();
        this.presidenciaId = getSetorId(colaboradorModel.getPresidencia());
        this.diretoriaId = getSetorId(colaboradorModel.getDiretoria());
        this.gerenciaId = getSetorId(colaboradorModel.getGerencia());
        this.nucleoId = getSetorId(colaboradorModel.getNucleo());
    }

    public int getSetorId(SetorModel setorModel) {
        if (setorModel != null) {
            return setorModel.getId();
        }
        return 0;
    }
}

