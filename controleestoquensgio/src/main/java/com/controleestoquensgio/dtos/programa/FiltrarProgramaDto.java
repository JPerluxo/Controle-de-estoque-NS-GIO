package com.controleestoquensgio.dtos.programa;

import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarProgramaDto {

    private String descricao;
    private String observacao;
    private int licencaId;
    private String ativo = SimOuNao.SIM.name();
    private LicencaModel licencaModel;
}
