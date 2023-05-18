package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.emprestimo.EmprestimoDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.EmprestimoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {
    final EmprestimoRepository emprestimoRpt;

    @Autowired
    ColaboradorService colaboradorSvc;

    @Autowired
    EquipamentoService equipamentoSvc;

    public EmprestimoService (EmprestimoRepository emprestimoRpt){
        this.emprestimoRpt = emprestimoRpt;
    }

    @Transactional
    public EmprestimoModel save(EmprestimoModel emprestimoMdl){
        return emprestimoRpt.save(emprestimoMdl);
    }

    @Transactional
    public Resultado save(EmprestimoDto emprestimoDto, EmprestimoModel emprestimoModel){

        BeanUtils.copyProperties(emprestimoDto, emprestimoModel);

        Optional<ColaboradorModel> colaboradorModelOption = colaboradorSvc.findById(emprestimoDto.getColaboradorId());

        if (colaboradorModelOption.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.colaboradorNaoEncontrado()
            );
        }

        emprestimoModel.setColaborador(colaboradorModelOption.get());

        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(emprestimoDto.getEquipamentoId());

        if (equipamentoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeAcessoNaoEncontrado()
            );
        }

        emprestimoModel.setEquipamento(equipamentoModelOptional.get());

        Optional<ColaboradorModel> respEntregaModelOption = colaboradorSvc.findById(emprestimoDto.getRespEntregaId());

        if (colaboradorModelOption.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.respEntregaNaoEncontrado()
            );
        }

        emprestimoModel.setRespEntrega(respEntregaModelOption.get());

        emprestimoModel.setDataDisponibilizacao(new java.sql.Date(emprestimoDto.getDataDisponibilizacao().getTime()));
        emprestimoModel.setVigente(emprestimoDto.getVigente());

        if (emprestimoDto.getDataDevolucao() != null) {
            emprestimoModel.setDataDevolucao(new java.sql.Date(emprestimoDto.getDataDevolucao().getTime()));
        }

        emprestimoRpt.save(emprestimoModel);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }


    public Page<EmprestimoModel> findAll(Pageable pageable) {
        return emprestimoRpt.findAll(pageable);
    }

    public Optional<EmprestimoModel> findById(Integer id) {
        return emprestimoRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<EmprestimoModel> emprestimoModelOptional = findById(id);

        if (emprestimoModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.emprestimoNaoEncontrado()
            );
        }

        emprestimoRpt.delete(emprestimoModelOptional.get());

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(EmprestimoModel emprestimoMdl){

        emprestimoRpt.save(emprestimoMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
