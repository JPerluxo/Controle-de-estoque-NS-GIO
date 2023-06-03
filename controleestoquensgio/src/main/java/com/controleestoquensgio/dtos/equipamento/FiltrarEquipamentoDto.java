package com.controleestoquensgio.dtos.equipamento;

import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.models.TipoEquipamentoModel;
import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarEquipamentoDto {

    private String serial;
    private String numPatrimonio;
    private String observacao;
    private int tipoEquipamentoId;
    private int notaFiscalId;
    private int localizacaoId;
    private int contratoEquipamentoTerceiroId;
    private String ativo = SimOuNao.SIM.name();

    private TipoEquipamentoModel tipoEquipamento;
    private NotaFiscalModel notaFiscal;
    private LocalizacaoModel localizacao;
    private ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiro;
}
