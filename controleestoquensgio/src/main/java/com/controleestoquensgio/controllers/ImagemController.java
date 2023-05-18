package com.controleestoquensgio.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.controleestoquensgio.dtos.imagem.*;
import com.controleestoquensgio.dtos.programa.ListarProgramaDto;
import com.controleestoquensgio.models.ImagemModel;
import com.controleestoquensgio.models.ProgramaModel;
import com.controleestoquensgio.services.ImagemService;
import com.controleestoquensgio.services.ProgramaService;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/imagens"})
public class ImagemController {

    @Autowired
    ImagemService imagemSvc;

    @Autowired
    ProgramaService programaSvc;

    @PostMapping
    public String save(@Valid CriarImagemDto criarImagemDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("criarImagemDto", criarImagemDto);
            model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable).map(ListarImagemDto::new));
            return "imagem/cadastrarImagem";
        }

        var imagemModel = new ImagemModel();

        BeanUtils.copyProperties(criarImagemDto, imagemModel);

        var resultado = imagemSvc.save(imagemModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/imagens";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = imagemSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/imagens";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid CriarImagemDto criarImagemDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("criarImagemDto", criarImagemDto);
            model.addAttribute("addProgramaNaImagemDto", new AddProgramaNaImagemDto(id));
            model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable).map(ListarImagemDto::new));
            return "imagem/atualizarImagem";
        }

        Optional<ImagemModel> imagemModelOptional = imagemSvc.findById(id);

        if (imagemModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.imagemNaoEncontrada()
            );

            return "redirect:/imagens";
        }

        var imagemModel = imagemModelOptional.get();

        BeanUtils.copyProperties(criarImagemDto, imagemModel);

        var resultado = imagemSvc.update(imagemModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/imagens";
    }

    @PostMapping("/update/{id}/addPrograma")
    public String addProgramOnImage(@PathVariable(value = "id") int id, @Valid AddProgramaNaImagemDto addProgramaNaImagemDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            addProgramaNaImagemDto.setImagemId(id);
            model.addAttribute("addProgramaNaImagemDto", addProgramaNaImagemDto);
            model.addAttribute("listaDeProgramas", programaSvc.findAll(pageable).map(ListarProgramaDto::new));
            model.addAttribute("listarProgramasDaImagemDto", getListarProgramasDaImagemDto(id));
            return "imagem/adicionarProgramaNaImagem";
        }

        var resultado = imagemSvc.addPrograma(id, addProgramaNaImagemDto.getProgramaId());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/imagens/update/"+id+"/addPrograma";
    }

    @PostMapping("/update/{id}/removePrograma/{programaId}")
    public String removeProgramFromImage(@PathVariable(value = "id") int id, @PathVariable(value = "programaId") int programaId, RedirectAttributes redirectAttributes) {

        var resultado = imagemSvc.removePrograma(id, programaId);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/imagens/update/"+id+"/addPrograma";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable).map(ListarImagemDto::new));
        model.addAttribute("criarImagemDto",new CriarImagemDto());

        return "imagem/cadastrarImagem";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<ImagemModel> imagemModelOptional = imagemSvc.findById(id);

        if (imagemModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );

            return "redirect:/imagens";
        }


        model.addAttribute("criarImagemDto", new VisualizarImagemDto(imagemModelOptional.get()));

        return "imagem/atualizarImagem";
    }

    @GetMapping("/update/{id}/addPrograma")
    public String showFormAddProgramOnImage(@PathVariable(value = "id") int id, Model model, Pageable pageable) {

        model.addAttribute("listaDeProgramas", programaSvc.findAll(pageable).map(ListarProgramaDto::new));
        model.addAttribute("addProgramaNaImagemDto", new AddProgramaNaImagemDto(id));
        model.addAttribute("listarProgramasDaImagemDto", getListarProgramasDaImagemDto(id));

        return "imagem/adicionarProgramaNaImagem";
    }

    public List<ListarProgramaDaImagemDto> getListarProgramasDaImagemDto (int id) {
        Set<ProgramaModel> listaDeProgramasDaImagemModel = imagemSvc.findById(id).get().getProgramas();

        List<ListarProgramaDaImagemDto> listarProgramasDaImagemDto = new ArrayList<>();

        ListarProgramaDaImagemDto listarProgramaDto;

        for (ProgramaModel programaModel: listaDeProgramasDaImagemModel) {
            listarProgramaDto = new ListarProgramaDaImagemDto();
            BeanUtils.copyProperties(programaModel, listarProgramaDto);
            listarProgramaDto.setLicenca(programaModel.getLicenca().getDescricao());
            listarProgramaDto.setImagemId(id);
            listarProgramasDaImagemDto.add(listarProgramaDto);
        }

        return listarProgramasDaImagemDto;
    }

}
