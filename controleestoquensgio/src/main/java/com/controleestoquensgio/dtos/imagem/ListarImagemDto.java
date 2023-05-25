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
public class ListarImagemDto {

    private int id;
    private String  descricao;
    private String ativo;
    public ListarImagemDto (ImagemModel imagemModel) {
        this.id = imagemModel.getId();
        this.descricao = imagemModel.getDescricao();
        this.ativo = imagemModel.getAtivo();
    }
}
