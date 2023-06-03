package com.controleestoquensgio.dtos.contratoEquipamentoTerceiro;

import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FiltrarContratoEquipamentoTerceiroDto {

    private String fornecedor;
    private Date dataInicio;
    private Date dataFinal;
    private String ativo = SimOuNao.SIM.name();
}