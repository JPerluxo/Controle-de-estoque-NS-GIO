package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.LocalizacaoModel;
import com.controleestoquensgio.dtos.LocalizacaoDto;
import com.controleestoquensgio.services.LocalizacaoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin (origins = "*", maxAge = 3600)
@RequestMapping(value = {"/controle-estoque/localizacao"})
public class LocalizacaoController {

    @Autowired
    LocalizacaoService localizacaoSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid LocalizacaoDto localizacaoDto){
        var localizacaoModel = new LocalizacaoModel();
        BeanUtils.copyProperties(localizacaoDto, localizacaoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(localizacaoSvc.save(localizacaoModel));
    }

    @GetMapping
    public ResponseEntity<Page<LocalizacaoModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(localizacaoSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<LocalizacaoModel> localizacaoModelOptional = localizacaoSvc.findById(id);
        if(!localizacaoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Localização não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(localizacaoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<LocalizacaoModel> localizacaoModelOptional = localizacaoSvc.findById(id);
        if(!localizacaoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Localização não encontrada");
        }
        localizacaoSvc.delete(localizacaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Localização deletada com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid LocalizacaoDto localizacaoDto){
        Optional<LocalizacaoModel> localizacaoModelOptional = localizacaoSvc.findById(id);
        if(!localizacaoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Localização não encontrada");
        }

        var localizacaoModel = localizacaoModelOptional.get();
        BeanUtils.copyProperties(localizacaoDto, localizacaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(localizacaoSvc.save(localizacaoModel));
    }
}
