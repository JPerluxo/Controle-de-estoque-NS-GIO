package com.controleestoquensgio.dtos.equipamento;


import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.models.EquipamentoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarEquipamentoDto {

    private int id;
    private String serial;
    private String numPatrimonio;
    private String observacao;
    private int tipoEquipamentoId;
    private int notaFiscalId;
    private int localizacaoId;
    private int contratoEquipamentoTerceiroId;

    public VisualizarEquipamentoDto(EquipamentoModel equipamentoModel) {
        this.id = equipamentoModel.getId();
        this.serial = equipamentoModel.getSerial();
        this.numPatrimonio = equipamentoModel.getNumPatrimonio();
        this.observacao = equipamentoModel.getObservacao();
        this.tipoEquipamentoId = equipamentoModel.getTipoEquipamento().getId();
        this.notaFiscalId = equipamentoModel.getNotaFiscal().getId();
        this.localizacaoId = equipamentoModel.getLocalizacao().getId();
        this.contratoEquipamentoTerceiroId = getContratoEqpTerceiroId(equipamentoModel.getContratoEquipamentoTerceiro());
    }

    public int getContratoEqpTerceiroId(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroModel) {

        if (contratoEquipamentoTerceiroModel != null) return contratoEquipamentoTerceiroModel.getId();

        return 0;
    }
}
