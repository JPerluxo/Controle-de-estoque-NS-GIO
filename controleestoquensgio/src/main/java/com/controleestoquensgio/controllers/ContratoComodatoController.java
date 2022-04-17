package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.ContratoComodatoModel;
import com.controleestoquensgio.dtos.ContratoComodatoDto;
import com.controleestoquensgio.services.ContratoComodatoService;

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
@RequestMapping(value = {"/controle-estoque/contratoComodato"})
public class ContratoComodatoController {

    @Autowired
    ContratoComodatoService contratoComodatoSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ContratoComodatoDto contratoComodatoDto){
        var contratoComodatoModel = new ContratoComodatoModel();
        BeanUtils.copyProperties(contratoComodatoDto, contratoComodatoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoComodatoSvc.save(contratoComodatoModel));
    }

    @GetMapping
    public ResponseEntity<Page<ContratoComodatoModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(contratoComodatoSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<ContratoComodatoModel> contratoComodatoModelOptional = contratoComodatoSvc.findById(id);
        if(!contratoComodatoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato de comodato não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contratoComodatoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<ContratoComodatoModel> contratoComodatoModelOptional = contratoComodatoSvc.findById(id);
        if(!contratoComodatoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato de comodato não encontrado");
        }
        contratoComodatoSvc.delete(contratoComodatoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Contrato de comodato deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid ContratoComodatoDto contratoComodatoDto){
        Optional<ContratoComodatoModel> contratoComodatoModelOptional = contratoComodatoSvc.findById(id);
        if(!contratoComodatoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato de comodato não encontrado");
        }

        var contratoComodatoModel = contratoComodatoModelOptional.get();
        BeanUtils.copyProperties(contratoComodatoDto, contratoComodatoModel);
        return ResponseEntity.status(HttpStatus.OK).body(contratoComodatoSvc.save(contratoComodatoModel));
    }
}
