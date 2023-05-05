package com.controleestoquensgio.dtos;

import com.controleestoquensgio.models.EquipamentoModel;
import com.controleestoquensgio.models.LocalizacaoModel;

public class ListarEquipamentosDto {

    private int id;
    private String serial;
    private String numPatrimonio;
    private String observacao;
    private String tipoEquipamento;
    private String notaFiscal;
    private String localizacao;
    private String contratoDeEquipamentoDeTerceiro;

    public ListarEquipamentosDto(EquipamentoModel equipamentoModel) {
        this.id = equipamentoModel.getId();
        this.serial = equipamentoModel.getSerial();
        this.numPatrimonio = equipamentoModel.getNumPatrimonio();
        this.observacao = equipamentoModel.getObservacao();
        this.tipoEquipamento = equipamentoModel.getTipoEquipamento().getDescricao();
        this.localizacao = buildLocalizacao(equipamentoModel.getLocalizacao());

        if (equipamentoModel.getNotaFiscal() != null) this.notaFiscal = equipamentoModel.getNotaFiscal().getNumero();
        if (equipamentoModel.getContratoEquipamentoTerceiro() != null) this.contratoDeEquipamentoDeTerceiro = equipamentoModel.getContratoEquipamentoTerceiro().getFornecedor();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getNumPatrimonio() {
        return numPatrimonio;
    }

    public void setNumPatrimonio(String numPatrimonio) {
        this.numPatrimonio = numPatrimonio;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getContratoDeEquipamentoDeTerceiro() {
        return contratoDeEquipamentoDeTerceiro;
    }

    public void setContratoDeEquipamentoDeTerceiro(String contratoDeEquipamentoDeTerceiro) {
        this.contratoDeEquipamentoDeTerceiro = contratoDeEquipamentoDeTerceiro;
    }

    private String buildLocalizacao(LocalizacaoModel localizacaoModel) {
        var predio = localizacaoModel.getPredio();
        var andar = localizacaoModel.getAndar();
        var lado = localizacaoModel.getLado();

        return predio + " - " + andar + " - " + lado;
    }
}
