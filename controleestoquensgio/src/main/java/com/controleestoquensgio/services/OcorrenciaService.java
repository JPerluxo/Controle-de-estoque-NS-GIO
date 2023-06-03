package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.ocorrencia.OcorrenciaDto;
import com.controleestoquensgio.dtos.ocorrencia.FiltrarOcorrenciaDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.repositories.OcorrenciaQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.OcorrenciaModel;
import com.controleestoquensgio.repositories.OcorrenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OcorrenciaService {
    final OcorrenciaRepository ocorrenciaRpt;

    final OcorrenciaQueryRepository ocorrenciaQueryRpt;

    @Autowired
    EquipamentoService equipamentoSvc;

    @Autowired
    ColaboradorService colaboradorSvc;

    public OcorrenciaService (OcorrenciaRepository ocorrenciaRpt, OcorrenciaQueryRepository ocorrenciaQueryRpt){
        this.ocorrenciaRpt = ocorrenciaRpt;
        this.ocorrenciaQueryRpt = ocorrenciaQueryRpt;
    }

    @Transactional
    public OcorrenciaModel save(OcorrenciaModel ocorrenciaMdl){
        return ocorrenciaRpt.save(ocorrenciaMdl);
    }

    @Transactional
    public Resultado save(OcorrenciaDto ocorrenciaDto, OcorrenciaModel ocorrenciaMdl){

        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(ocorrenciaDto.getEquipamentoId());

        if (equipamentoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.equipamentoNaoEncontrado()
            );
        }

        ocorrenciaMdl.setEquipamento(equipamentoModelOptional.get());

        Optional<ColaboradorModel> colaboradorModelOptional = colaboradorSvc.findById(ocorrenciaDto.getColaboradorId());

        if (colaboradorModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.colaboradorNaoEncontrado()
            );
        }

        ocorrenciaMdl.setColaborador(colaboradorModelOptional.get());

        ocorrenciaRpt.save(ocorrenciaMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<OcorrenciaModel> findAll(Pageable pageable) {
        return ocorrenciaRpt.findAll(pageable);
    }

    public Page<OcorrenciaModel> findAllAtivo(Pageable pageable, String ativo) {
        return ocorrenciaRpt.findAllByAtivo(pageable, ativo);
    }

    public Page<OcorrenciaModel> findAllByFilter(Pageable pageable, FiltrarOcorrenciaDto filtrarOcorrenciaDto) {

        Optional<ColaboradorModel> colaboradorModelOptional = colaboradorSvc.findById(filtrarOcorrenciaDto.getColaboradorId());
        colaboradorModelOptional.ifPresent(filtrarOcorrenciaDto::setColaborador);

        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(filtrarOcorrenciaDto.getEquipamentoId());
        equipamentoModelOptional.ifPresent(filtrarOcorrenciaDto::setEquipamento);

        var ocorrencias = ocorrenciaQueryRpt.customQuery(filtrarOcorrenciaDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), ocorrencias.size());

        return new PageImpl<>(ocorrencias.subList(start, end), pageable, ocorrencias.size());
    }

    public Optional<OcorrenciaModel> findById(Integer id) {
        return ocorrenciaRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<OcorrenciaModel> ocorrenciaModelOptional = findById(id);

        if (ocorrenciaModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.ocorrenciaNaoEncontrada()
            );
        }

        OcorrenciaModel ocorrenciaModel = ocorrenciaModelOptional.get();
        ocorrenciaModel.setAtivo(SimOuNao.NAO.name());

        ocorrenciaRpt.save(ocorrenciaModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(OcorrenciaModel ocorrenciaMdl){

        ocorrenciaRpt.save(ocorrenciaMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
