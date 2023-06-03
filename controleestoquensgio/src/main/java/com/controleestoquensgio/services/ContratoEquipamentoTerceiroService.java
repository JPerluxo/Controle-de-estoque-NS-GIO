package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.contratoEquipamentoTerceiro.FiltrarContratoEquipamentoTerceiroDto;
import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.models.ContratoEquipamentoTerceiroModel;
import com.controleestoquensgio.repositories.ContratoEquipamentoTerceiroQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.ContratoEquipamentoTerceiroRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContratoEquipamentoTerceiroService {
    final ContratoEquipamentoTerceiroRepository contratoEquipamentoTerceiroRpt;

    final ContratoEquipamentoTerceiroQueryRepository contratoEquipamentoTerceiroQueryRpt;

    public ContratoEquipamentoTerceiroService (ContratoEquipamentoTerceiroRepository contratoEquipamentoTerceiroRpt, ContratoEquipamentoTerceiroQueryRepository contratoEquipamentoTerceiroQueryRpt){
        this.contratoEquipamentoTerceiroRpt = contratoEquipamentoTerceiroRpt;
        this.contratoEquipamentoTerceiroQueryRpt = contratoEquipamentoTerceiroQueryRpt;
    }

    @Transactional
    public Resultado save(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroMdl){

        contratoEquipamentoTerceiroRpt.save(contratoEquipamentoTerceiroMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(), Mensagens.operacaoBemSucedida()
        );
    } 

    public Page<ContratoEquipamentoTerceiroModel> findAll(Pageable pageable) {
        return contratoEquipamentoTerceiroRpt.findAll(pageable);
    }

    public Page<ContratoEquipamentoTerceiroModel> findAllAtivo(Pageable pageable, String ativo) {
        return contratoEquipamentoTerceiroRpt.findAllByAtivo(pageable, ativo);

    }

    public Page<ContratoEquipamentoTerceiroModel> findAllByFilter(Pageable pageable, FiltrarContratoEquipamentoTerceiroDto filtrarContratoEquipamentoTerceiroDto) {
        var contratosEquipamentoTerceiros = contratoEquipamentoTerceiroQueryRpt.customQuery(filtrarContratoEquipamentoTerceiroDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), contratosEquipamentoTerceiros.size());

        return new PageImpl<>(contratosEquipamentoTerceiros.subList(start, end), pageable, contratosEquipamentoTerceiros.size());
    }

    public Optional<ContratoEquipamentoTerceiroModel> findById(Integer id) {
        return contratoEquipamentoTerceiroRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<ContratoEquipamentoTerceiroModel> contratoEquipamentoTerceiroModelOptional = findById(id);

        if (contratoEquipamentoTerceiroModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(), Mensagens.contratoDeEquipamentoDeTerceiroNaoEncontrado()
            );
        }

        ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroModel = contratoEquipamentoTerceiroModelOptional.get();
        contratoEquipamentoTerceiroModel.setAtivo(SimOuNao.NAO.name());

        contratoEquipamentoTerceiroRpt.save(contratoEquipamentoTerceiroModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(), Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(ContratoEquipamentoTerceiroModel contratoEquipamentoTerceiroMdl){

        contratoEquipamentoTerceiroRpt.save(contratoEquipamentoTerceiroMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(), Mensagens.operacaoBemSucedida()
        );
    }
}
