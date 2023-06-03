package com.controleestoquensgio.services;

import com.controleestoquensgio.dtos.setor.SetorDto;
import com.controleestoquensgio.dtos.setor.FiltrarSetorDto;
import com.controleestoquensgio.models.*;
import com.controleestoquensgio.repositories.ColaboradorRepository;
import com.controleestoquensgio.repositories.SetorRepository;
import com.controleestoquensgio.repositories.SetorQueryRepository;
import com.controleestoquensgio.repositories.TipoAcessoQueryRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRpt;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    SetorQueryRepository setorQueryRpt;

    @Autowired
    SetorQueryRepository tipoAcessoQueryRpt;

    @Transactional
    public Resultado save(SetorModel setorMdl){

        setorRpt.save(setorMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado save(SetorDto setorDto, SetorModel setorMdl){


        Optional<ColaboradorModel> colaboradorModelOptional = colaboradorRepository.findById(setorDto.getResponsavelId());

        if (colaboradorModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.colaboradorNaoEncontrado()
            );
        }

        setorMdl.setResponsavel(colaboradorModelOptional.get());

        setorRpt.save(setorMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    public Page<SetorModel> findAll(Pageable pageable) {
        return setorRpt.findAll(pageable);
    }

    public Page<SetorModel> findAllAtivo(Pageable pageable, String ativo) {
        return setorRpt.findAllByAtivo(pageable, ativo);
    }

    public Page<SetorModel> findAllByFilter(Pageable pageable, FiltrarSetorDto filtrarSetorDto) {

        Optional<ColaboradorModel> colaboradorModelOptional = colaboradorRepository.findById(filtrarSetorDto.getResponsavelId());
        colaboradorModelOptional.ifPresent(filtrarSetorDto::setResponsavel);

        var setoress = setorQueryRpt.customQuery(filtrarSetorDto);

        final int start = (int)pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), setoress.size());

        return new PageImpl<>(setoress.subList(start, end), pageable, setoress.size());
    }

    public Page<SetorModel> findAllByNivel(Pageable pageable, String nivel) {
        return setorRpt.findAllByNivel(pageable, nivel);
    }

    public Optional<SetorModel> findById(Integer id) {
        return setorRpt.findById(id);
    }

    @Transactional
    public Resultado deleteById(int id) {

        Optional<SetorModel> setorModelOptional = findById(id);

        if (setorModelOptional.isEmpty()) {
            return new Resultado(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.setorNaoEncontrado()
            );
        }

        setorRpt.delete(setorModelOptional.get());

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }

    @Transactional
    public Resultado update(SetorModel setorMdl){

        setorRpt.save(setorMdl);

        return new Resultado(
                ErroOuSucesso.SUCESSO.name(),
                Mensagens.operacaoBemSucedida()
        );
    }
}
