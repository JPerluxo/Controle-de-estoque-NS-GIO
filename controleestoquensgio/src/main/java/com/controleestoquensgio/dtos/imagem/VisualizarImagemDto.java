/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos.imagem;

import com.controleestoquensgio.models.ImagemModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VisualizarImagemDto {

    private int id;
    private String  descricao;

    public VisualizarImagemDto(ImagemModel imagemModel) {
        this.id = imagemModel.getId();
        this.descricao = imagemModel.getDescricao();
    }
}
