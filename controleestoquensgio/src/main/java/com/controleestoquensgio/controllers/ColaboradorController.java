package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.dtos.ColaboradorDto;
import com.controleestoquensgio.dtos.ListarColaboradoresDto;
import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.services.*;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
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

    @PostMapping
    public String save(@Valid ColaboradorDto colaboradorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("colaboradorDto", colaboradorDto);
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
            model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable));
            model.addAttribute("listaDeTiposDeAcesso", tipoAcessoSvc.findAll(pageable));
            model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAll(pageable));
            model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAll(pageable));
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
            model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable));
            model.addAttribute("listaDeTiposDeAcesso", tipoAcessoSvc.findAll(pageable));
            model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAll(pageable));
            model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAll(pageable));
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
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAll(pageable).map(ListarColaboradoresDto::new));
        model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable));
        model.addAttribute("listaDeTiposDeAcesso", tipoAcessoSvc.findAll(pageable));
        model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAll(pageable));
        model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAll(pageable));

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

        ColaboradorDto colaboradorDto = new ColaboradorDto();
        ColaboradorModel colaboradorModel = colaboradorModelOptional.get();

        BeanUtils.copyProperties(colaboradorModel, colaboradorDto);

        colaboradorDto.setImagemId(colaboradorModel.getImagem().getId());
        colaboradorDto.setTipoAcessoId(colaboradorModel.getTipoAcesso().getId());
        colaboradorDto.setTipoColaboradorId(colaboradorModel.getTipoColaborador().getId());
        colaboradorDto.setRegimeTrabalhoId(colaboradorModel.getRegimeTrabalho().getId());

        model.addAttribute("colaboradorDto", colaboradorDto);
        model.addAttribute("listaDeImagens", imagemSvc.findAll(pageable));
        model.addAttribute("listaDeTiposDeAcesso", tipoAcessoSvc.findAll(pageable));
        model.addAttribute("listaDeTiposDeColaborador", tipoColaboradorSvc.findAll(pageable));
        model.addAttribute("listaDeRegimesDeTrabalho", regimeTrabalhoSvc.findAll(pageable));

        return "colaborador/atualizarColaborador";
    }
}
