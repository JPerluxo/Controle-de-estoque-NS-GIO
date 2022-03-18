package com.controleestoquensgio.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.controleestoquensgio.dtos.ImagemDto;
import com.controleestoquensgio.models.ImagemModel;
import com.controleestoquensgio.models.ProgramaModel;
import com.controleestoquensgio.services.ImagemService;
import com.controleestoquensgio.services.ProgramaService;

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
    final ProgramaService programaSvc;

    public ImagemController(ImagemService imagemSvc, ProgramaService programaSvc) {
        this.imagemSvc = imagemSvc;
        this.programaSvc = programaSvc;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ImagemDto imagemDto){

        Optional<ProgramaModel> programaModelOptional = programaSvc.findById(1);
        Optional<ProgramaModel> programaModelOptional2 = programaSvc.findById(12);
        Optional<ProgramaModel> programaModelOptional3 = programaSvc.findById(6);

        List<ProgramaModel> programas = new ArrayList<ProgramaModel>();
        
        programas.add(programaModelOptional.get());
        programas.add(programaModelOptional2.get());
        programas.add(programaModelOptional3.get());

        var imagemModel = new ImagemModel();

        BeanUtils.copyProperties(imagemDto, imagemModel);

        imagemModel.setProgramas(programas);
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
