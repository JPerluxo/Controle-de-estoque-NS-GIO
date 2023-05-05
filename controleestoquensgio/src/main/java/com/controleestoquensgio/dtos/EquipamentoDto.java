package com.controleestoquensgio.dtos;

import jakarta.validation.constraints.Positive;

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

    public int getTipoEquipamentoId() {
        return tipoEquipamentoId;
    }

    public void setTipoEquipamentoId(int tipoEquipamentoId) {
        this.tipoEquipamentoId = tipoEquipamentoId;
    }

    public int getNotaFiscalId() {
        return notaFiscalId;
    }

    public void setNotaFiscalId(int notaFiscalId) {
        this.notaFiscalId = notaFiscalId;
    }

    public int getLocalizacaoId() {
        return localizacaoId;
    }

    public void setLocalizacaoId(int localizacaoId) {
        this.localizacaoId = localizacaoId;
    }

    public int getContratoEquipamentoTerceiroId() {
        return contratoEquipamentoTerceiroId;
    }

    public void setContratoEquipamentoTerceiroId(int contratoEquipamentoTerceiroId) {
        this.contratoEquipamentoTerceiroId = contratoEquipamentoTerceiroId;
    }
}
