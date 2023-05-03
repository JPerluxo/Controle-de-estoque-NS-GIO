package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.dtos.AddProgramaNaImagemDto;
import com.controleestoquensgio.dtos.CriarImagemDto;
import com.controleestoquensgio.models.ImagemModel;
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
public class ImagemController extends ControllerFather{

    @Autowired
    ImagemService imagemSvc;

    @Autowired
    ProgramaService programaSvc;

    @PostMapping
    public String save(@Valid CriarImagemDto criarImagemDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("criarImagemDto", criarImagemDto);
            model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable));
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
            model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable));
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

    @PostMapping("/update/{id}/addPrograma/{programaId}")
    public String addProgramaNaImagem(@PathVariable(value = "id") int id, @PathVariable(value = "programaId") int programaId, CriarImagemDto criarImagemDto, @Valid AddProgramaNaImagemDto addProgramaNaImagemDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            addProgramaNaImagemDto.setImagemId(id);
            model.addAttribute("criarImagemDto", criarImagemDto);
            model.addAttribute("addProgramaNaImagemDto", addProgramaNaImagemDto);
            model.addAttribute("listaDeProgramas", programaSvc.findAll(pageable));
            //model.addAttribute("listaDeProgramasDaImagem", imagemSvc.findById(id).get().getProgramas());
            return "imagem/atualizarImagem";
        }

        var resultado = imagemSvc.addPrograma(id, programaId);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/update/"+id;
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        Iterable<ImagemModel> listaDeImagens = imagemSvc.findAll(pageable);
        CriarImagemDto criarImagemDto = new CriarImagemDto();

        model.addAttribute("listaDeImagens", listaDeImagens);
        model.addAttribute("criarImagemDto", criarImagemDto);

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

        model.addAttribute("addProgramaNaImagemDto", new AddProgramaNaImagemDto(id));
        model.addAttribute("criarImagemDto", imagemModelOptional.get());

        return "imagem/atualizarImagem";
    }
}
