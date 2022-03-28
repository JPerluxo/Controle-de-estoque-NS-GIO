package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.NucleoModel;
import com.controleestoquensgio.repositories.NucleoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NucleoService {
    final NucleoRepository nucleoRpt;

    public NucleoService (NucleoRepository nucleoRpt){
        this.nucleoRpt = nucleoRpt;
    }

    @Transactional
    public NucleoModel save(NucleoModel nucleoMdl){
        return nucleoRpt.save(nucleoMdl);
    } 

    public Page<NucleoModel> findAll(Pageable pageable) {
        return nucleoRpt.findAll(pageable);
    }

    public Optional<NucleoModel> findById(Integer id) {
        return nucleoRpt.findById(id);
    }

    @Transactional
    public void delete(NucleoModel nucleoMdl) {
        nucleoRpt.delete(nucleoMdl);
    }
}
