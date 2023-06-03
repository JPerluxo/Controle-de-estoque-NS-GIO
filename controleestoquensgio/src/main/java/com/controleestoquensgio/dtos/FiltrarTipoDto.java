package com.controleestoquensgio.dtos;

import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarTipoDto {

    private String descricao;
    private String ativo = SimOuNao.SIM.name();
}
