package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.LocalizacaoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {
    final LocalizacaoRepository localizacaoRpt;

    public LocalizacaoService (LocalizacaoRepository localizacaoRpt){
        this.localizacaoRpt = localizacaoRpt;
    }

    @Transactional
    public Resultado save(LocalizacaoModel localizacaoMdl){

        localizacaoRpt.save(localizacaoMdl);

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    }

    public Page<LocalizacaoModel> findAll(Pageable pageable) {
        return localizacaoRpt.findAll(pageable);
    }

    public Optional<LocalizacaoModel> findById(Integer id) {
        return localizacaoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<LocalizacaoModel> localizacaoModelOptional = findById(id);

        if (localizacaoModelOptional.isEmpty()) {
            return new Resultado(
                    Mensagens.localizacaoNaoEncontrada(),
                    Mensagens.localizacaoNaoEncontradaTipoDeMensagem()
            );
        }

        localizacaoRpt.delete(localizacaoModelOptional.get());

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    }

    @Transactional
    public Resultado update(LocalizacaoModel localizacaoMdl){

        localizacaoRpt.save(localizacaoMdl);

        return new Resultado(
                Mensagens.operacaoBemSucedida(),
                Mensagens.operacaoBemSucedidaTipoDeMensagem()
        );
    }
}
