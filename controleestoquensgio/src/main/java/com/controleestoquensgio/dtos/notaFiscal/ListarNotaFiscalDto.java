package com.controleestoquensgio.dtos.notaFiscal;

import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.util.ConversorData;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ListarNotaFiscalDto {

    private int id;
    private String numero;
    private String data;
    private String ativo;
    public ListarNotaFiscalDto(NotaFiscalModel notaFiscalModel) {
        this.id = notaFiscalModel.getId();
        this.numero = notaFiscalModel.getNumero();
        this.data = ConversorData.converterData(notaFiscalModel.getData());
        this.ativo = notaFiscalModel.getAtivo();
    }
}
