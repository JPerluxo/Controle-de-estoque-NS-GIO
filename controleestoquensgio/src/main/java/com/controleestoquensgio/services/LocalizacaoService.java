package com.controleestoquensgio.services;

import java.util.Optional;

import jakarta.transaction.Transactional;

import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.repositories.LocalizacaoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {
    final LocalizacaoRepository localizacaoRpt;

    public LocalizacaoService (LocalizacaoRepository localizacaoRpt){
        this.localizacaoRpt = localizacaoRpt;
    }

    @Transactional
    public LocalizacaoModel save(LocalizacaoModel localizacaoMdl){
        return localizacaoRpt.save(localizacaoMdl);
    } 

    public Page<LocalizacaoModel> findAll(Pageable pageable) {
        return localizacaoRpt.findAll(pageable);
    }

    public Optional<LocalizacaoModel> findById(Integer id) {
        return localizacaoRpt.findById(id);
    }

    @Transactional
    public void delete(LocalizacaoModel localizacaoMdl) {
        localizacaoRpt.delete(localizacaoMdl);
    }
}
