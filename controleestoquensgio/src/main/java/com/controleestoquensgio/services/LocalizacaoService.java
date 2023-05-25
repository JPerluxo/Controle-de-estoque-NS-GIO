package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
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
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<LocalizacaoModel> findAll(Pageable pageable) {
        return localizacaoRpt.findAll(pageable);
    }

    public Page<LocalizacaoModel> findAllAtivo(Pageable pageable, String ativo) {
        return localizacaoRpt.findAllByAtivo(pageable, ativo);
    }

    public Optional<LocalizacaoModel> findById(Integer id) {
        return localizacaoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<LocalizacaoModel> localizacaoModelOptional = findById(id);

        if (localizacaoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.localizacaoNaoEncontrada()
            );
        }

        LocalizacaoModel localizacaoModel = localizacaoModelOptional.get();
        localizacaoModel.setAtivo(SimOuNao.NAO.name());

        localizacaoRpt.save(localizacaoModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(LocalizacaoModel localizacaoMdl){

        localizacaoRpt.save(localizacaoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
