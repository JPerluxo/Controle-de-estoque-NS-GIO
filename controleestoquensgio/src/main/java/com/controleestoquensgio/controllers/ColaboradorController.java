package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.dtos.regimeTrabalho.ListarRegimeTrabalhoDto;
import com.controleestoquensgio.dtos.colaborador.ColaboradorDto;
import com.controleestoquensgio.dtos.colaborador.ListarColaboradoresDto;
import com.controleestoquensgio.dtos.colaborador.VisualizarColaboradoresDto;
import com.controleestoquensgio.dtos.imagem.ListarImagemDto;
import com.controleestoquensgio.dtos.setor.ListarSetorDto;
import com.controleestoquensgio.dtos.tipoAcesso.ListarTipoAcessoDto;
import com.controleestoquensgio.dtos.tipoColaborador.ListarTipoColaboradorDto;
import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.services.*;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.NivelSetores;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/colaboradores"})
public class ColaboradorController {

    @Autowired
    ColaboradorService colaboradorSvc;

    @Autowired
    ImagemService imagemSvc;

    @Autowired
    TipoAcessoService tipoAcessoSvc;

    @Autowired
    TipoColaboradorService tipoColaboradorSvc;

    @Autowired
    RegimeTrabalhoService regimeTrabalhoSvc;

    @Autowired
    SetorService setorService;

    @PostMapping
    public String save(@Valid ColaboradorDto colaboradorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("colaboradorDto", colaboradorDto);
            setDominios(pageable, model, true);
            return "colaborador/cadastrarColaborador";
        }

        var resultado = colaboradorSvc.save(colaboradorDto, new ColaboradorModel());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/colaboradores";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = colaboradorSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/colaboradores";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid ColaboradorDto colaboradorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("colaboradorDto", colaboradorDto);
            setDominios(pageable, model, false);
            return "colaborador/atualizarColaborador";
        }


        Optional<ColaboradorModel> colaboradorModelOptional = colaboradorSvc.findById(id);

        if (colaboradorModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.colaboradorNaoEncontrado()
            );

            return "redirect:/colaboradores";
        }

        var resultado = colaboradorSvc.save(colaboradorDto, colaboradorModelOptional.get());

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/colaboradores";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("colaboradorDto", new ColaboradorDto());
        setDominios(pageable, model, true);

        return "colaborador/cadastrarColaborador";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {

        Optional<ColaboradorModel> colaboradorModelOptional = colaboradorSvc.findById(id);

        if (colaboradorModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeColaboradorNaoEncontrado()
            );

            return "redirect:/colaboradores";
        }

        model.addAttribute("colaboradorDto", new VisualizarColaboradoresDto(colaboradorModelOptional.get()));
        setDominios(pageable, model, false);

        return "colaborador/atualizarColaborador";
    }
    
    private void setDominios(Pageable pageable, Model model, Boolean getAllColaboradoresToo) {

        if (getAllColaboradoresToo) {
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
        }

        model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable).map(ListarImagemDto::new));
        model.addAttribute("listaDeTiposDeAcesso", tipoAcessoSvc.findAll(pageable).map(ListarTipoAcessoDto::new));
        model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAll(pageable).map(ListarTipoColaboradorDto::new));
        model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAll(pageable).map(ListarRegimeTrabalhoDto::new));
        model.addAttribute("listaDePresidencias", setorService.findAllByNivel(pageable, NivelSetores.PRESIDENCIA.name()).map(ListarSetorDto::new));
        model.addAttribute("listaDeDiretorias", setorService.findAllByNivel(pageable, NivelSetores.DIRETORIA.name()).map(ListarSetorDto::new));
        model.addAttribute("listaDeGerencias", setorService.findAllByNivel(pageable, NivelSetores.GERENCIA.name()).map(ListarSetorDto::new));
        model.addAttribute("listaDeNucleos", setorService.findAllByNivel(pageable, NivelSetores.NUCLEO.name()).map(ListarSetorDto::new));
    }
}
