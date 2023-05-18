package com.controleestoquensgio.dtos.imagem;

import com.controleestoquensgio.dtos.programa.ListarProgramaDto;
import com.controleestoquensgio.models.ProgramaModel;

public class ListarProgramaDaImagemDto extends ListarProgramaDto {

    private int imagemId;

    public ListarProgramaDaImagemDto(ProgramaModel programaModel, int imagemId) {
        super(programaModel);
        this.imagemId = imagemId;
    }

    public ListarProgramaDaImagemDto() {}

    public int getImagemId() {
        return imagemId;
    }

    public void setImagemId(int imagemId) {
        this.imagemId = imagemId;
    }
}
