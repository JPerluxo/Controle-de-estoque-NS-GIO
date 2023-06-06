package com.controleestoquensgio.dtos.notaFiscal;

import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FiltrarNotaFiscalDto {

    private String numero;
    private LocalDate data;
    private String ativo = SimOuNao.SIM.name();

}
