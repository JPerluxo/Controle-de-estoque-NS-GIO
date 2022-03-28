package com.controleestoquensgio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.repositories.NotaFiscalRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalService {
    final NotaFiscalRepository notaFiscalRpt;

    public NotaFiscalService (NotaFiscalRepository notaFiscalRpt){
        this.notaFiscalRpt = notaFiscalRpt;
    }

    @Transactional
    public NotaFiscalModel save(NotaFiscalModel notaFiscalMdl){
        return notaFiscalRpt.save(notaFiscalMdl);
    } 

    public Page<NotaFiscalModel> findAll(Pageable pageable) {
        return notaFiscalRpt.findAll(pageable);
    }

    public Optional<NotaFiscalModel> findById(Integer id) {
        return notaFiscalRpt.findById(id);
    }

    @Transactional
    public void delete(NotaFiscalModel notaFiscalMdl) {
        notaFiscalRpt.delete(notaFiscalMdl);
    }
}
