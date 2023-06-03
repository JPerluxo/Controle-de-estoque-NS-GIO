package com.controleestoquensgio.dtos.ocorrencia;

import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.models.EquipamentoModel;
import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarOcorrenciaDto {

    private String os;
    private String descricao;
    private int equipamentoId;
    private int colaboradorId;
    private String ativo = SimOuNao.SIM.name();

    private EquipamentoModel equipamento;
    private ColaboradorModel colaborador;

}
