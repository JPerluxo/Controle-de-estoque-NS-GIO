package com.controleestoquensgio.services;

import java.util.Optional;

import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.TipoColaboradorModel;
import com.controleestoquensgio.repositories.TipoColaboradorRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipoColaboradorService {
    final TipoColaboradorRepository tipoColaboradorRpt;

    public TipoColaboradorService (TipoColaboradorRepository tipoColaboradorRpt){
        this.tipoColaboradorRpt = tipoColaboradorRpt;
    }

    @Transactional
    public TipoColaboradorModel save(TipoColaboradorModel tipoColaboradorMdl){
        return tipoColaboradorRpt.save(tipoColaboradorMdl);
    } 

    public Page<TipoColaboradorModel> findAll(Pageable pageable) {
        return tipoColaboradorRpt.findAll(pageable);
    }

    public Optional<TipoColaboradorModel> findById(Integer id) {
        return tipoColaboradorRpt.findById(id);
    }

    @Transactional
    public void delete(TipoColaboradorModel tipoColaboradorMdl) {
        tipoColaboradorRpt.delete(tipoColaboradorMdl);
    }
}
