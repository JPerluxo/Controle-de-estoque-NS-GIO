package com.controleestoquensgio.controllers;

import java.util.Optional;

import javax.validation.Valid;

import com.controleestoquensgio.dtos.ImagemDto;
import com.controleestoquensgio.models.ImagemModel;
import com.controleestoquensgio.services.ImagemService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping(value = {"/controle-estoque/imagem", "/"})
public class ImagemController {

    final ImagemService imagemSvc;

    public ImagemController(ImagemService imagemSvc) {
        this.imagemSvc = imagemSvc;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ImagemDto imagemDto){
        
        var imagemModel = new ImagemModel();
        BeanUtils.copyProperties(imagemDto, imagemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(imagemSvc.save(imagemModel));
    }

    @GetMapping
    public ResponseEntity<Page<ImagemModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(imagemSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<ImagemModel> imagemModelOptional = imagemSvc.findById(id);
        if(!imagemModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(imagemModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<ImagemModel> imagemModelOptional = imagemSvc.findById(id);
        if(!imagemModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem not found");
        }
        imagemSvc.delete(imagemModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Imagem deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid ImagemDto imagemDto){
        Optional<ImagemModel> imagemModelOptional = imagemSvc.findById(id);
        if(!imagemModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imagem not found");
        }

        var imagemModel = imagemModelOptional.get();
        imagemModel.setDescricao(imagemDto.getDescricao());

        return ResponseEntity.status(HttpStatus.OK).body(imagemSvc.save(imagemModel));
    }
}
