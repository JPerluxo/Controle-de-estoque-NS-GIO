package com.controleestoquensgio.dtos.setor;

import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarSetorDto {

    private String sigla;
    private String descricao;
    private int responsavelId;
    private String nivel;
    private String ativo = SimOuNao.SIM.name();

    private ColaboradorModel responsavel;
}
