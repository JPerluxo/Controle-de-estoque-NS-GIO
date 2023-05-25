package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.colaborador.ColaboradorDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
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

    @Autowired
    SetorService setorSvc;

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

        if (colaboradorDto.getPresidenciaId() > 0 ) {
            Optional<SetorModel> presidenciaOptional = setorSvc.findById(colaboradorDto.getPresidenciaId());
            if (presidenciaOptional.isEmpty()) {
                return new Resultado(
                        ErroOuSucesso.ERRO.name(),
                        Mensagens.presidenciaNaoEncontrada()
                );
            }
            colaboradorModel.setPresidencia(presidenciaOptional.get());
        }

        if (colaboradorDto.getDiretoriaId() > 0) {
            Optional<SetorModel> diretoriaOptional = setorSvc.findById(colaboradorDto.getDiretoriaId());
            if (diretoriaOptional.isEmpty()) {
                return new Resultado(
                        ErroOuSucesso.ERRO.name(),
                        Mensagens.diretoriaNaoEncontrada()
                );
            }
            colaboradorModel.setDiretoria(diretoriaOptional.get());
        }

        if (colaboradorDto.getGerenciaId() > 0) {
            Optional<SetorModel> gerenciaOptional = setorSvc.findById(colaboradorDto.getGerenciaId());
            if (gerenciaOptional.isEmpty()) {
                return new Resultado(
                        ErroOuSucesso.ERRO.name(),
                        Mensagens.gerenciaNaoEncontrada()
                );
            }
            colaboradorModel.setGerencia(gerenciaOptional.get());
        }

        if (colaboradorDto.getNucleoId() > 0) {
            Optional<SetorModel> nucleoOptional = setorSvc.findById(colaboradorDto.getNucleoId());
            if (nucleoOptional.isEmpty()) {
                return new Resultado(
                        ErroOuSucesso.ERRO.name(),
                        Mensagens.nucleoNaoEncontrado()
                );
            }
            colaboradorModel.setNucleo(nucleoOptional.get());
        }

        colaboradorRpt.save(colaboradorModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<ColaboradorModel> findAll(Pageable pageable) {
        return colaboradorRpt.findAll(pageable);
    }

    public Page<ColaboradorModel> findAllAtivo(Pageable pageable, String ativo) {
        return colaboradorRpt.findAllByAtivo(pageable, ativo);
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

        ColaboradorModel colaboradorModel = colaboradorModelOptional.get();
        colaboradorModel.setAtivo(SimOuNao.NAO.name());

        colaboradorRpt.save(colaboradorModel);

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
