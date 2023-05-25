package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.equipamento.EquipamentoDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.util.SimOuNao;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import com.controleestoquensgio.repositories.EquipamentoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRpt;

    @Autowired
    private TipoEquipamentoService tipoEquipamentoSvc;

    @Autowired
    NotaFiscalService notaFiscalSvc;

    @Autowired
    LocalizacaoService localizacaoSvc;

    @Autowired
    ContratoEquipamentoTerceiroService contratoEquipamentoTerceiroSvc;

    public EquipamentoService (EquipamentoRepository equipamentoRpt){
        this.equipamentoRpt = equipamentoRpt;
    }

    @Transactional
    public Resultado save(EquipamentoModel equipamentoMdl){

        equipamentoRpt.save(equipamentoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado save(EquipamentoDto equipamentoDto, EquipamentoModel equipamentoModel){

        if (equipamentoDto.getSerial().isBlank() && equipamentoDto.getNumPatrimonio().isBlank()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.insiraNumeroDeSerieOuNumeroDePatrimonio()
            );
        }

        BeanUtils.copyProperties(equipamentoDto, equipamentoModel);

        Optional<TipoEquipamentoModel> tipoEquipamentoModelOption = tipoEquipamentoSvc.findById(equipamentoDto.getTipoEquipamentoId());

        if (tipoEquipamentoModelOption.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );
        }

        equipamentoModel.setTipoEquipamento(tipoEquipamentoModelOption.get());

        Optional<LocalizacaoModel> localizacaoModelOption = localizacaoSvc.findById(equipamentoDto.getLocalizacaoId());

        if (localizacaoModelOption.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.localizacaoNaoEncontrada()
            );
        }

        equipamentoModel.setLocalizacao(localizacaoModelOption.get());

        if (equipamentoDto.getNotaFiscalId() > 0) {
            Optional<NotaFiscalModel> notaFiscalModelOption = notaFiscalSvc.findById(equipamentoDto.getLocalizacaoId());

            if (notaFiscalModelOption.isEmpty()) {
                return new Resultado(
                        ErroOuSucesso.ERRO.name(),
                        Mensagens.notaFiscalNaoEncontrada()
                );
            }

            equipamentoModel.setNotaFiscal(notaFiscalModelOption.get());
        }

        if (equipamentoDto.getContratoEquipamentoTerceiroId() > 0) {
            Optional<ContratoEquipamentoTerceiroModel> contratoEquipamentoTerceiroModelOptional = contratoEquipamentoTerceiroSvc.findById(equipamentoDto.getLocalizacaoId());

            if (contratoEquipamentoTerceiroModelOptional.isEmpty()) {
                return new Resultado(
                        ErroOuSucesso.ERRO.name(),
                        Mensagens.contratoDeEquipamentoDeTerceiroNaoEncontrado()
                );
            }

            equipamentoModel.setContratoEquipamentoTerceiro(contratoEquipamentoTerceiroModelOptional.get());
        }

        equipamentoRpt.save(equipamentoModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<EquipamentoModel> findAll(Pageable pageable) {
        return equipamentoRpt.findAll(pageable);
    }

    public Page<EquipamentoModel> findAllAtivo(Pageable pageable, String ativo) {
        return equipamentoRpt.findAllByAtivo(pageable, ativo);
    }

    public Optional<EquipamentoModel> findById(Integer id) {
        return equipamentoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<EquipamentoModel> equipamentoModelOptional = findById(id);

        if (equipamentoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.equipamentoNaoEncontrado()
            );
        }

        EquipamentoModel equipamentoModel = equipamentoModelOptional.get();
        equipamentoModel.setAtivo(SimOuNao.NAO.name());

        equipamentoRpt.save(equipamentoModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(EquipamentoModel equipamentoMdl){

        equipamentoRpt.save(equipamentoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
