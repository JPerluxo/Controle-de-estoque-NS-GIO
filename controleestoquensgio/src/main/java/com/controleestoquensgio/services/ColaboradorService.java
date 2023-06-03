package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.colaborador.ColaboradorDto;
import com.controleestoquensgio.dtos.colaborador.FiltrarColaboradorDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.repositories.ColaboradorQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.ColaboradorRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorService {
    @Autowired
    ColaboradorRepository colaboradorRpt;

    @Autowired
    ColaboradorQueryRepository colaboradorQueryRpt;

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

    public Page<ColaboradorModel> findAllByFilter(Pageable pageable, FiltrarColaboradorDto filtrarColaboradorDto) {

        Optional<ImagemModel> imagemModelOptional = imagemSvc.findById(filtrarColaboradorDto.getImagemId());
        imagemModelOptional.ifPresent(filtrarColaboradorDto::setImagem);

        Optional<TipoAcessoModel> tipoAcessoModelOptional = tipoAcessoSvc.findById(filtrarColaboradorDto.getTipoAcessoId());
        tipoAcessoModelOptional.ifPresent(filtrarColaboradorDto::setTipoAcesso);

        Optional<TipoColaboradorModel> tipoColaboradorModelOptional = tipoColaboradorSvc.findById(filtrarColaboradorDto.getTipoColaboradorId());
        tipoColaboradorModelOptional.ifPresent(filtrarColaboradorDto::setTipoColaborador);

        Optional<RegimeTrabalhoModel> regimeTrabalhoModel = regimeTrabalhoSvc.findById(filtrarColaboradorDto.getRegimeTrabalhoId());
        regimeTrabalhoModel.ifPresent(filtrarColaboradorDto::setRegimeTrabalho);

        Optional<SetorModel> presidenciaModelOptional = setorSvc.findById(filtrarColaboradorDto.getPresidenciaId());
        presidenciaModelOptional.ifPresent(filtrarColaboradorDto::setPresidencia);

        Optional<SetorModel> diretoriaModelOptional = setorSvc.findById(filtrarColaboradorDto.getDiretoriaId());
        diretoriaModelOptional.ifPresent(filtrarColaboradorDto::setDiretoria);

        Optional<SetorModel> gerenciaModelOptional = setorSvc.findById(filtrarColaboradorDto.getGerenciaId());
        gerenciaModelOptional.ifPresent(filtrarColaboradorDto::setGerencia);

        Optional<SetorModel> nucleoModelOptional = setorSvc.findById(filtrarColaboradorDto.getNucleoId());
        nucleoModelOptional.ifPresent(filtrarColaboradorDto::setNucleo);

        var colaboradores = colaboradorQueryRpt.customQuery(filtrarColaboradorDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), colaboradores.size());

        return new PageImpl<>(colaboradores.subList(start, end), pageable, colaboradores.size());
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
