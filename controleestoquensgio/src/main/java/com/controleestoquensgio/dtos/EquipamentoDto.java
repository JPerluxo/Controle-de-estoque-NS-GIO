package com.controleestoquensgio.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.controleestoquensgio.models.*;


public class EquipamentoDto {

    private String serial;
    private String numPatrimonio;
    private String observacao;

    @NotBlank(message = "Insira um tipo de equipamento!")
    private TipoEquipamentoModel tipoEquipamento; 

    private NotaFiscalModel notaFiscal;

    @NotBlank(message = "Insira uma localização!")
    private LocalizacaoModel localizacao;

    private ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiro;
    private List <OcorrenciaModel> ocorrencias;

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
    public TipoEquipamentoModel getTipoEquipamento() {
        return tipoEquipamento;
    }
    public void setTipoEquipamento(TipoEquipamentoModel tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }
    public NotaFiscalModel getNotaFiscal() {
        return notaFiscal;
    }
    public void setNotaFiscal(NotaFiscalModel notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
    public LocalizacaoModel getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(LocalizacaoModel localizacao) {
        this.localizacao = localizacao;
    }
    public ContratoEquipamentoTerceiroModel getContratoEquipamentoTerceiro() {
        return contratoEquipamentoTerceiro;
    }
    public void setContratoEquipamentoTerceiro(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiro) {
        this.contratoEquipamentoTerceiro = contratoEquipamentoTerceiro;
    }
    public List<OcorrenciaModel> getOcorrencias() {
        return ocorrencias;
    }
    public void setOcorrencias(List<OcorrenciaModel> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    
}
