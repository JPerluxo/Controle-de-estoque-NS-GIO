package com.controleestoquensgio.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.LicencaModel;
import com.controleestoquensgio.repositories.LicencaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LicencaService {
    final LicencaRepository licencaRpt;

    public LicencaService (LicencaRepository licencaRpt){
        this.licencaRpt = licencaRpt;
    }

    @Transactional
    public LicencaModel save(LicencaModel licencaMdl){
        return licencaRpt.save(licencaMdl);
    } 

    public Page<LicencaModel> findAll(Pageable pageable) {
        return licencaRpt.findAll(pageable);
    }

    public Optional<LicencaModel> findById(UUID lic_id) {
        return licencaRpt.findById(lic_id);
    }

    @Transactional
    public void delete(LicencaModel licencaMdl) {
        licencaRpt.delete(licencaMdl);
    }
}
