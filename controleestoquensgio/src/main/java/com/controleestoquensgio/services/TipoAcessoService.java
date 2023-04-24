package com.controleestoquensgio.services;

import java.util.Optional;

import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.repositories.TipoAcessoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoAcessoService {
    final TipoAcessoRepository tipoAcessoRpt;

    public TipoAcessoService (TipoAcessoRepository tipoAcessoRpt){
        this.tipoAcessoRpt = tipoAcessoRpt;
    }

    @Transactional
    public TipoAcessoModel save(TipoAcessoModel tipoAcessoMdl){
        return tipoAcessoRpt.save(tipoAcessoMdl);
    } 

    public Page<TipoAcessoModel> findAll(Pageable pageable) {
        return tipoAcessoRpt.findAll(pageable);
    }

    public Optional<TipoAcessoModel> findById(Integer id) {
        return tipoAcessoRpt.findById(id);
    }

    @Transactional
    public void delete(TipoAcessoModel tipoAcessoMdl) {
        tipoAcessoRpt.delete(tipoAcessoMdl);
    }
}
