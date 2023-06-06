package com.controleestoquensgio.dtos.notaFiscal;

import com.controleestoquensgio.models.NotaFiscalModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VisualizarNotaFiscalDto {

    private int id;
    private String numero;
    private LocalDate data;

    public VisualizarNotaFiscalDto(NotaFiscalModel notaFiscalModel) {
        this.id = notaFiscalModel.getId();
        this.numero = notaFiscalModel.getNumero();
        this.data = notaFiscalModel.getData();
    }
}
