package com.controleestoquensgio.dtos.contratoEquipamentoTerceiro;

import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.util.ConversorData;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class ListarContratoEquipamentoTerceiroDto {

    private int id;
    private String fornecedor;
    private String dataInicio;
    private String dataFinal;
    private String ativo;
    public ListarContratoEquipamentoTerceiroDto(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroModel) {
        this.id = contratoEquipamentoTerceiroModel.getId();
        this.fornecedor = contratoEquipamentoTerceiroModel.getFornecedor();
        this.dataInicio = ConversorData.converterData(contratoEquipamentoTerceiroModel.getDataInicio());
        this.dataFinal = ConversorData.converterData(contratoEquipamentoTerceiroModel.getDataFinal());
        this.ativo = contratoEquipamentoTerceiroModel.getAtivo();
    }
}