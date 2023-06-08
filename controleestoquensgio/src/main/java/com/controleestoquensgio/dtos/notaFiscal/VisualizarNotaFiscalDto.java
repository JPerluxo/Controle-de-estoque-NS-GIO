package com.controleestoquensgio.dtos.notaFiscal;

import com.controleestoquensgio.models.NotaFiscalModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class VisualizarNotaFiscalDto {

    private int id;
    private String numero;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    public VisualizarNotaFiscalDto(NotaFiscalModel notaFiscalModel) {
        this.id = notaFiscalModel.getId();
        this.numero = notaFiscalModel.getNumero();
        this.data = notaFiscalModel.getData();
    }
}
