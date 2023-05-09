package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.OcorrenciaDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.OcorrenciaModel;
import com.controleestoquensgio.repositories.OcorrenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OcorrenciaService {
    final OcorrenciaRepository ocorrenciaRpt;

    @Autowired
    EquipamentoService equipamentoSvc;

    @Autowired
    ColaboradorService colaboradorSvc;

    public OcorrenciaService (OcorrenciaRepository ocorrenciaRpt){
        this.ocorrenciaRpt = ocorrenciaRpt;
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

        ocorrenciaRpt.delete(ocorrenciaModelOptional.get());

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
