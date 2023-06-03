package com.controleestoquensgio.dtos.notaFiscal;

import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FiltrarNotaFiscalDto {

    private String numero;
    private Date data;
    private String ativo = SimOuNao.SIM.name();

}
