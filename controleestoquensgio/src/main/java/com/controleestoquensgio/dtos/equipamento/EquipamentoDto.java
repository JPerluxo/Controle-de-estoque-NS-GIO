package com.controleestoquensgio.dtos.equipamento;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipamentoDto {

    private String serial;
    private String numPatrimonio;
    private String observacao;

    @Positive(message = "Insira um tipo de equipamento!")
    private int tipoEquipamentoId;

    private int notaFiscalId;

    @Positive(message = "Insira uma localização!")
    private int localizacaoId;

    private int contratoEquipamentoTerceiroId;

}
