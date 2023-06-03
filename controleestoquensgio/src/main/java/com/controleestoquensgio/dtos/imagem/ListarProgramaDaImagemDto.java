package com.controleestoquensgio.dtos.imagem;

import com.controleestoquensgio.dtos.programa.ListarProgramaDto;
import com.controleestoquensgio.models.ProgramaModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarProgramaDaImagemDto extends ListarProgramaDto {

    private int imagemId;

    public ListarProgramaDaImagemDto(ProgramaModel programaModel, int imagemId) {
        super(programaModel);
        this.imagemId = imagemId;
    }

    public ListarProgramaDaImagemDto() {}

}
