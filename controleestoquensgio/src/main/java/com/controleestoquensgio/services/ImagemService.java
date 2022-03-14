package com.controleestoquensgio.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.controleestoquensgio.models.ImagemModel;
import com.controleestoquensgio.repositories.ImagemRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImagemService {
    final ImagemRepository imagemRpt;

    public ImagemService (ImagemRepository imagemRpt){
        this.imagemRpt = imagemRpt;
    }

    @Transactional
    public ImagemModel save(ImagemModel imagemMdl){
        return imagemRpt.save(imagemMdl);
    } 

    public Page<ImagemModel> findAll(Pageable pageable) {
        return imagemRpt.findAll(pageable);
    }

    public Optional<ImagemModel> findById(UUID img_id) {
        return imagemRpt.findById(img_id);
    }

    @Transactional
    public void delete(ImagemModel imagemMdl) {
        imagemRpt.delete(imagemMdl);
    }
}
