package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.localizacao.FiltrarLocalizacaoDto;
import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.repositories.LocalizacaoQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.LocalizacaoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {
    final LocalizacaoRepository localizacaoRpt;

    final LocalizacaoQueryRepository localizacaoQueryRpt;

    public LocalizacaoService (LocalizacaoRepository localizacaoRpt, LocalizacaoQueryRepository localizacaoQueryRpt){
        this.localizacaoRpt = localizacaoRpt;
        this.localizacaoQueryRpt = localizacaoQueryRpt;
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

    public Page<LocalizacaoModel> findAllByFilter(Pageable pageable, FiltrarLocalizacaoDto filtrarLocalizacaoDto) {
        var localizacoes = localizacaoQueryRpt.customQuery(filtrarLocalizacaoDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), localizacoes.size());

        return new PageImpl<>(localizacoes.subList(start, end), pageable, localizacoes.size());
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
