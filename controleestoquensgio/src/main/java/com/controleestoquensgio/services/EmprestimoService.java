package com.controleestoquensgio.services;

import java.util.Optional;

import com.controleestoquensgio.dtos.emprestimo.EmprestimoDto;
import com.controleestoquensgio.dtos.emprestimo.FiltrarEmprestimoDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.repositories.EmprestimoQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;

import com.controleestoquensgio.repositories.EmprestimoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {
    final EmprestimoRepository emprestimoRpt;

    final EmprestimoQueryRepository emprestimoQueryRpt;
    

    @Autowired
    ColaboradorService colaboradorSvc;

    @Autowired
    EquipamentoService equipamentoSvc;

    public EmprestimoService (EmprestimoRepository emprestimoRpt, EmprestimoQueryRepository emprestimoQueryRpt){
        this.emprestimoRpt = emprestimoRpt;
        this.emprestimoQueryRpt = emprestimoQueryRpt;
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
                    Mensagens.equipamentoNaoEncontrado()
            );
        }

        emprestimoModel.setEquipamento(equipamentoModelOptional.get());

        Optional<ColaboradorModel> respEntregaModelOption = colaboradorSvc.findById(emprestimoDto.getRespEntregaId());

        if (respEntregaModelOption.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.respEntregaNaoEncontrado()
            );
        }

        emprestimoModel.setRespEntrega(respEntregaModelOption.get());

        emprestimoModel.setDataDisponibilizacao(new java.sql.Date(emprestimoDto.getDataDisponibilizacao().getTime()));

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

    public Page<EmprestimoModel> findAllAtivo(Pageable pageable, String ativo) {
        return emprestimoRpt.findAllByAtivo(pageable, ativo);
    }

    public Page<EmprestimoModel> findAllByFilter(Pageable pageable, FiltrarEmprestimoDto filtrarEmprestimoDto) {

        Optional<ColaboradorModel> colaboradorModelOptional = colaboradorSvc.findById(filtrarEmprestimoDto.getColaboradorId());
        colaboradorModelOptional.ifPresent(filtrarEmprestimoDto::setColaborador);

        Optional<EquipamentoModel> equipamentoModelOptional = equipamentoSvc.findById(filtrarEmprestimoDto.getEquipamentoId());
        equipamentoModelOptional.ifPresent(filtrarEmprestimoDto::setEquipamento);

        Optional<ColaboradorModel> respEntregaModelOptional = colaboradorSvc.findById(filtrarEmprestimoDto.getRespEntregaId());
        respEntregaModelOptional.ifPresent(filtrarEmprestimoDto::setRespEntrega);

        var emprestimos = emprestimoQueryRpt.customQuery(filtrarEmprestimoDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), emprestimos.size());

        return new PageImpl<>(emprestimos.subList(start, end), pageable, emprestimos.size());
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
