package com.controleestoquensgio.dtos.notaFiscal;

import com.controleestoquensgio.models.NotaFiscalModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VisualizarNotaFiscalDto {

    private int id;
    private String numero;
    private Date data;

    public VisualizarNotaFiscalDto(NotaFiscalModel notaFiscalModel) {
        this.id = notaFiscalModel.getId();
        this.numero = notaFiscalModel.getNumero();
        this.data = notaFiscalModel.getData();
    }
}
