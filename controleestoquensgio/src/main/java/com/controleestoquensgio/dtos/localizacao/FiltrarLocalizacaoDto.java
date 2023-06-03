package com.controleestoquensgio.dtos.localizacao;

import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarLocalizacaoDto {

    private String predio;
    private String andar;
    private String lado;
    private String referencia;
    private String ativo = SimOuNao.SIM.name();
       
}
