package com.controleestoquensgio.models;

import javax.persistence.*;

@Entity
@Table (name = "EQUIPAMENTOS")
public class EquipamentoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="eqp_id")
    private int id;

    @Column(name="eqp_serial", nullable = false, length = 50)
    private String serial;

    @Column(name="eqp_patrimonio", nullable = false, length = 50)
    private String numPatrimonio;

    @Column(name="eqp_observacao", nullable = false, length = 100)
    private String observacao;

    @ManyToOne
    private TipoEquipamentoModel tipoEquipamento; 
    
    @ManyToOne
    private NotaFiscalModel notaFiscal;

    @ManyToOne
    private LocalizacaoModel localizacao;

    @ManyToOne
    private ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiro;
    
    @OneToMany
    private OcorrenciaModel ocorrencias;

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
    public OcorrenciaModel getOcorrencias() {
        return ocorrencias;
    }
    public void setOcorrencias(OcorrenciaModel ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
}
