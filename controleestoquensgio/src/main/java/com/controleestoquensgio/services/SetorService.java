package com.controleestoquensgio.services;

import com.controleestoquensgio.dtos.setor.SetorDto;
import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.models.SetorModel;
import com.controleestoquensgio.repositories.ColaboradorRepository;
import com.controleestoquensgio.repositories.SetorRepository;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.Resultado;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetorService {

    @Autowired
    private SetorRepository setorRpt;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

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
