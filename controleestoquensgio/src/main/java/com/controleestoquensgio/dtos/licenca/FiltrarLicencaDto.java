/**
 *
 * @author Tiago
 */

package com.controleestoquensgio.dtos.licenca;

import com.controleestoquensgio.util.SimOuNao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltrarLicencaDto {

    private String descricao;
    private String ativo = SimOuNao.SIM.name();

}