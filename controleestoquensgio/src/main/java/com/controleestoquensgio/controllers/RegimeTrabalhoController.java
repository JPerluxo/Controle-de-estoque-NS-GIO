package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.RegimeTrabalhoModel;
import com.controleestoquensgio.dtos.RegimeTrabalhoDto;
import com.controleestoquensgio.services.RegimeTrabalhoService;

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
@RequestMapping(value = {"/controle-estoque/regimeTrabalho"})
public class RegimeTrabalhoController extends ControllerFather{

    @Autowired
    RegimeTrabalhoService regimeTrabalhoSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid RegimeTrabalhoDto regimeTrabalhoDto){
        
        var regimeTrabalhoModel = new RegimeTrabalhoModel();
        BeanUtils.copyProperties(regimeTrabalhoDto, regimeTrabalhoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(regimeTrabalhoSvc.save(regimeTrabalhoModel));
    }

    @GetMapping
    public ResponseEntity<Page<RegimeTrabalhoModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(regimeTrabalhoSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = regimeTrabalhoSvc.findById(id);
        if(!regimeTrabalhoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Regime de trabalho não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(regimeTrabalhoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = regimeTrabalhoSvc.findById(id);
        if(!regimeTrabalhoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Regime de trabalho não encontrado");
        }
        regimeTrabalhoSvc.delete(regimeTrabalhoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Regime de trabalho deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid RegimeTrabalhoDto regimeTrabalhoDto){
        Optional<RegimeTrabalhoModel> regimeTrabalhoModelOptional = regimeTrabalhoSvc.findById(id);
        if(!regimeTrabalhoModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Regime de trabalho não encontrado");
        }

        var regimeTrabalhoModel = regimeTrabalhoModelOptional.get();
        BeanUtils.copyProperties(regimeTrabalhoDto, regimeTrabalhoModel);

        return ResponseEntity.status(HttpStatus.OK).body(regimeTrabalhoSvc.save(regimeTrabalhoModel));
    }
}
