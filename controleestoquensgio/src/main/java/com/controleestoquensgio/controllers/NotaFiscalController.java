package com.controleestoquensgio.controllers;

import java.util.Optional;


import javax.validation.Valid;
import com.controleestoquensgio.models.NotaFiscalModel;
import com.controleestoquensgio.dtos.NotaFiscalDto;
import com.controleestoquensgio.services.NotaFiscalService;

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
@RequestMapping(value = {"/controle-estoque/notaFiscal"})
public class NotaFiscalController extends ControllerFather{

    @Autowired
    NotaFiscalService notaFiscalSvc;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid NotaFiscalDto notaFiscalDto){
        var notaFiscalModel = new NotaFiscalModel();
        BeanUtils.copyProperties(notaFiscalDto, notaFiscalModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(notaFiscalSvc.save(notaFiscalModel));
    }

    @GetMapping
    public ResponseEntity<Page<NotaFiscalModel>> getAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(notaFiscalSvc.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") int id){
        Optional<NotaFiscalModel> notaFiscalModelOptional = notaFiscalSvc.findById(id);
        if(!notaFiscalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota fiscal não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(notaFiscalModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") int id){
        Optional<NotaFiscalModel> notaFiscalModelOptional = notaFiscalSvc.findById(id);
        if(!notaFiscalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota fiscal não encontrada");
        }
        notaFiscalSvc.delete(notaFiscalModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Nota fiscal deletada com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid NotaFiscalDto notaFiscalDto){
        Optional<NotaFiscalModel> notaFiscalModelOptional = notaFiscalSvc.findById(id);
        if(!notaFiscalModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nota fiscal não encontrada");
        }

        var notaFiscalModel = notaFiscalModelOptional.get();
        BeanUtils.copyProperties(notaFiscalDto, notaFiscalModel);
        return ResponseEntity.status(HttpStatus.OK).body(notaFiscalSvc.save(notaFiscalModel));
    }
}
