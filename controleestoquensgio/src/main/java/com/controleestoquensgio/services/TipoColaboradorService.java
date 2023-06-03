package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.tipoColaborador.FiltrarTipoColaboradorDto;
import com.controleestoquensgio.models.TipoColaboradorModel;
import com.controleestoquensgio.repositories.TipoColaboradorQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.repositories.TipoColaboradorRepository;
import com.controleestoquensgio.util.SimOuNao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TipoColaboradorService {
    final TipoColaboradorRepository tipoColaboradorRpt;

    final TipoColaboradorQueryRepository tipoColaboradorQueryRpt;

    public TipoColaboradorService (TipoColaboradorRepository tipoColaboradorRpt, TipoColaboradorQueryRepository tipoColaboradorQueryRepository){
        this.tipoColaboradorRpt = tipoColaboradorRpt;
        this.tipoColaboradorQueryRpt = tipoColaboradorQueryRepository;
    }

    @Transactional
    public Resultado save(TipoColaboradorModel tipoColaboradorMdl) {

        tipoColaboradorRpt.save(tipoColaboradorMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(), Mensagens.operacaoBemSucedida()
        );
    }
    
    public Page<TipoColaboradorModel> findAll(Pageable pageable) {
        return tipoColaboradorRpt.findAll(pageable);
    }

    public Page<TipoColaboradorModel> findAllAtivo(Pageable pageable, String ativo) {
        return tipoColaboradorRpt.findAllByAtivo(pageable, ativo);
    }

    public Page<TipoColaboradorModel> findAllByFilter(Pageable pageable, FiltrarTipoColaboradorDto filtrarTipoColaboradorDto) {
        var tiposColaboradores = tipoColaboradorQueryRpt.customQuery(filtrarTipoColaboradorDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), tiposColaboradores.size());

        return new PageImpl<>(tiposColaboradores.subList(start, end), pageable, tiposColaboradores.size());
    }

    public Optional<TipoColaboradorModel> findById(Integer id) {
        return tipoColaboradorRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = findById(id);

        if (tipoColaboradorModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeColaboradorNaoEncontrado()
            );
        }

        TipoColaboradorModel tipoColaboradorModel = tipoColaboradorModelOptional.get();
        tipoColaboradorModel.setAtivo(SimOuNao.NAO.name());

        tipoColaboradorRpt.save(tipoColaboradorModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(TipoColaboradorModel tipoColaboradorMdl){

        tipoColaboradorRpt.save(tipoColaboradorMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
