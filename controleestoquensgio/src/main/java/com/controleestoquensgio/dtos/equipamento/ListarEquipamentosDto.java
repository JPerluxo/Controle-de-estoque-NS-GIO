package com.controleestoquensgio.dtos.equipamento;

import com.controleestoquensgio.models.EquipamentoModel;
import com.controleestoquensgio.models.LocalizacaoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarEquipamentosDto {

    private int id;
    private String serial;
    private String numPatrimonio;
    private String observacao;
    private String tipoEquipamento;
    private String notaFiscal;
    private String localizacao;
    private String contratoDeEquipamentoDeTerceiro;
    private String ativo;
    public ListarEquipamentosDto(EquipamentoModel equipamentoModel) {
        this.id = equipamentoModel.getId();
        this.serial = equipamentoModel.getSerial();
        this.numPatrimonio = equipamentoModel.getNumPatrimonio();
        this.observacao = equipamentoModel.getObservacao();
        this.tipoEquipamento = equipamentoModel.getTipoEquipamento().getDescricao();
        this.localizacao = buildLocalizacao(equipamentoModel.getLocalizacao());
        this.ativo = equipamentoModel.getAtivo();

        if (equipamentoModel.getNotaFiscal() != null) this.notaFiscal = equipamentoModel.getNotaFiscal().getNumero();
        if (equipamentoModel.getContratoEquipamentoTerceiro() != null) this.contratoDeEquipamentoDeTerceiro = equipamentoModel.getContratoEquipamentoTerceiro().getFornecedor();
    }

    private String buildLocalizacao(LocalizacaoModel localizacaoModel) {
        var predio = localizacaoModel.getPredio();
        var andar = localizacaoModel.getAndar();
        var lado = localizacaoModel.getLado();

        return predio + " - " + andar + " - " + lado;
    }
}
