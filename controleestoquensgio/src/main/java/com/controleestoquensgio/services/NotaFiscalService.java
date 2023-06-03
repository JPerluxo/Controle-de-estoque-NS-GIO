package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.notaFiscal.FiltrarNotaFiscalDto;
import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.repositories.NotaFiscalQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.NotaFiscalRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {
    final NotaFiscalRepository notaFiscalRpt;

    final NotaFiscalQueryRepository notaFiscalQueryRpt;

    public NotaFiscalService (NotaFiscalRepository notaFiscalRpt, NotaFiscalQueryRepository notaFiscalQueryRpt){
        this.notaFiscalRpt = notaFiscalRpt;
        this.notaFiscalQueryRpt = notaFiscalQueryRpt;
    }

    @Transactional
    public Resultado save(NotaFiscalModel notaFiscalMdl){

        notaFiscalRpt.save(notaFiscalMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    } 

    public Page<NotaFiscalModel> findAll(Pageable pageable) {
        return notaFiscalRpt.findAll(pageable);
    }

    public Page<NotaFiscalModel> findAllAtivo(Pageable pageable, String ativo) {
        return notaFiscalRpt.findAllByAtivo(pageable, ativo);
    }

    public Page<NotaFiscalModel> findAllByFilter(Pageable pageable, FiltrarNotaFiscalDto filtrarNotaFiscalDto) {
        var notasFiscais = notaFiscalQueryRpt.customQuery(filtrarNotaFiscalDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), notasFiscais.size());

        return new PageImpl<>(notasFiscais.subList(start, end), pageable, notasFiscais.size());
    }

    public Optional<NotaFiscalModel> findById(Integer id) {
        return notaFiscalRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<NotaFiscalModel> notaFiscalModelOptional = findById(id);

        if (notaFiscalModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.notaFiscalNaoEncontrada()
            );
        }

        NotaFiscalModel notaFiscalModel = notaFiscalModelOptional.get();
        notaFiscalModel.setAtivo(SimOuNao.NAO.name());

        notaFiscalRpt.save(notaFiscalModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(NotaFiscalModel notaFiscalMdl){

        notaFiscalRpt.save(notaFiscalMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
