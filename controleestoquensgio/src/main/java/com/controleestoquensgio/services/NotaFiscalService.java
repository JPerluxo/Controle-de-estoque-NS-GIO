package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.NotaFiscalRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {
    final NotaFiscalRepository notaFiscalRpt;

    public NotaFiscalService (NotaFiscalRepository notaFiscalRpt){
        this.notaFiscalRpt = notaFiscalRpt;
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

        notaFiscalRpt.delete(notaFiscalModelOptional.get());

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
