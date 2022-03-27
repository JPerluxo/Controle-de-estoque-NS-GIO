package com.controleestoquensgio.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class EquipamentoDto {

    private String serial;
    private String numPatrimonio;
    private String observacao;
    private TipoEquipamentoDto tipoEquipamento; 
    private NotaFiscalDto notaFiscal;

    @NotBlank
    private LocalizacaoDto localizacao;

    private ContratoEquipamentoTerceiroDto contratoEquipamentoTerceiro;
    private List <OcorrenciaDto> ocorrencias;

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
    public TipoEquipamentoDto getTipoEquipamento() {
        return tipoEquipamento;
    }
    public void setTipoEquipamento(TipoEquipamentoDto tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }
    public NotaFiscalDto getNotaFiscal() {
        return notaFiscal;
    }
    public void setNotaFiscal(NotaFiscalDto notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
    public LocalizacaoDto getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(LocalizacaoDto localizacao) {
        this.localizacao = localizacao;
    }
    public ContratoEquipamentoTerceiroDto getContratoEquipamentoTerceiro() {
        return contratoEquipamentoTerceiro;
    }
    public void setContratoEquipamentoTerceiro(ContratoEquipamentoTerceiroDto contratoEquipamentoTerceiro) {
        this.contratoEquipamentoTerceiro = contratoEquipamentoTerceiro;
    }
    public List<OcorrenciaDto> getOcorrencias() {
        return ocorrencias;
    }
    public void setOcorrencias(List<OcorrenciaDto> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    
}
