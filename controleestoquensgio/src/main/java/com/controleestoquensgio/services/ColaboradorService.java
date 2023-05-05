package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.ColaboradorDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.ColaboradorRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
    @Autowired
    ColaboradorRepository colaboradorRpt;

    @Autowired
    ImagemService imagemSvc;

    @Autowired
    TipoAcessoService tipoAcessoSvc;

    @Autowired
    TipoColaboradorService tipoColaboradorSvc;

    @Autowired
    RegimeTrabalhoService regimeTrabalhoSvc;

    @Transactional
    public ColaboradorModel save(ColaboradorModel colaboradorMdl){
        return colaboradorRpt.save(colaboradorMdl);
    }

    @Transactional
    public Resultado save(ColaboradorDto colaboradorDto, ColaboradorModel colaboradorModel){

        BeanUtils.copyProperties(colaboradorDto, colaboradorModel);

        Optional<ImagemModel> imagemModelOption = imagemSvc.findById(colaboradorDto.getImagemId());

        if (imagemModelOption.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.imagemNaoEncontrada()
            );
        }

        colaboradorModel.setImagem(imagemModelOption.get());

        Optional<TipoAcessoModel> tipoAcessoModelOptional = tipoAcessoSvc.findById(colaboradorDto.getTipoAcessoId());

        if (tipoAcessoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeAcessoNaoEncontrado()
            );
        }

        colaboradorModel.setTipoAcesso(tipoAcessoModelOptional.get());

        Optional<TipoColaboradorModel> tipoColaboradorModelOption = tipoColaboradorSvc.findById(colaboradorDto.getTipoColaboradorId());

        if (tipoColaboradorModelOption.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeColaboradorNaoEncontrado()
            );
        }

        colaboradorModel.setTipoColaborador(tipoColaboradorModelOption.get());

        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = regimeTrabalhoSvc.findById(colaboradorDto.getRegimeTrabalhoId());

        if (regimeTrabalhoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.regimeDeTrabalhoNaoEncontrado()
            );
        }

        colaboradorModel.setRegimeTrabalho(regimeTrabalhoModelOptional.get());

        colaboradorRpt.save(colaboradorModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<ColaboradorModel> findAll(Pageable pageable) {
        return colaboradorRpt.findAll(pageable);
    }

    public Optional<ColaboradorModel> findById(Integer id) {
        return colaboradorRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<ColaboradorModel> colaboradorModelOptional = findById(id);

        if (colaboradorModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.colaboradorNaoEncontrado()
            );
        }

        colaboradorRpt.delete(colaboradorModelOptional.get());

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(ColaboradorModel colaboradorMdl){

        colaboradorRpt.save(colaboradorMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
