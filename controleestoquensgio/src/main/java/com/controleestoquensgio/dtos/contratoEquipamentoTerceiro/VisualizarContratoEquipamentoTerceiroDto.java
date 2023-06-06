package com.controleestoquensgio.dtos.contratoEquipamentoTerceiro;

import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class VisualizarContratoEquipamentoTerceiroDto {

    private int id;
    private String fornecedor;
    private LocalDate dataInicio;
    private LocalDate dataFinal;

    public VisualizarContratoEquipamentoTerceiroDto(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroModel) {
        this.id = contratoEquipamentoTerceiroModel.getId();
        this.fornecedor = contratoEquipamentoTerceiroModel.getFornecedor();
        this.dataInicio = contratoEquipamentoTerceiroModel.getDataInicio();
        this.dataFinal = contratoEquipamentoTerceiroModel.getDataFinal();
    }
}