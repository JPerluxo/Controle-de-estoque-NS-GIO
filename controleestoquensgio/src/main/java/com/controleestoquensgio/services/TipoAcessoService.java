package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.tipoAcesso.FiltrarTipoAcessoDto;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.repositories.TipoAcessoQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.TipoAcessoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoAcessoService {
    final TipoAcessoRepository tipoAcessoRpt;

    final TipoAcessoQueryRepository tipoAcessoQueryRpt;

    public TipoAcessoService (TipoAcessoRepository tipoAcessoRpt, TipoAcessoQueryRepository tipoAcessoQueryRpt){
        this.tipoAcessoRpt = tipoAcessoRpt;
        this.tipoAcessoQueryRpt = tipoAcessoQueryRpt;
    }

    @Transactional
    public Resultado save(TipoAcessoModel tipoAcessoMdl){
        
        tipoAcessoRpt.save(tipoAcessoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    } 

    public Page<TipoAcessoModel> findAll(Pageable pageable) {
        return tipoAcessoRpt.findAll(pageable);
    }

    public Page<TipoAcessoModel> findAllAtivo(Pageable pageable, String ativo) {
        return tipoAcessoRpt.findAllByAtivo(pageable, ativo);
    }

    public Page<TipoAcessoModel> findAllByFilter(Pageable pageable, FiltrarTipoAcessoDto filtrarTipoAcessoDto) {
        var tiposAcessos = tipoAcessoQueryRpt.customQuery(filtrarTipoAcessoDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), tiposAcessos.size());

        return new PageImpl<>(tiposAcessos.subList(start, end), pageable, tiposAcessos.size());
    }

    public Optional<TipoAcessoModel> findById(Integer id) {
        return tipoAcessoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<TipoAcessoModel> tipoAcessoModelOptional = findById(id);

        if (tipoAcessoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeAcessoNaoEncontrado()
            );
        }

        TipoAcessoModel tipoAcessoModel = tipoAcessoModelOptional.get();
        tipoAcessoModel.setAtivo(SimOuNao.NAO.name());

        tipoAcessoRpt.save(tipoAcessoModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(TipoAcessoModel tipoAcessoMdl){

        tipoAcessoRpt.save(tipoAcessoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
