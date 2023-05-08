package com.controleestoquensgio.controllers;

import java.util.Optional;

import com.controleestoquensgio.util.ErroOuSucesso;
import jakarta.validation.Valid;

import com.controleestoquensgio.dtos.TipoAcessoDto;
import com.controleestoquensgio.models.TipoAcessoModel;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.services.TipoAcessoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = {"/tiposDeAcesso"})
public class TipoAcessoController {

    @Autowired
    TipoAcessoService tipoAcessoSvc;

    @PostMapping
    public String save(@Valid TipoAcessoDto tipoAcessoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){
        
        if (result.hasErrors()) {
            model.addAttribute("tipoAcessoDto", tipoAcessoDto);
            model.addAttribute("listaDeTiposDeAcesso", tipoAcessoSvc.findAll(pageable));
            return "tipoAcesso/cadastrarTipoAcesso";
        }
        
        var tipoAcessoModel = new TipoAcessoModel();
        
        BeanUtils.copyProperties(tipoAcessoDto, tipoAcessoModel);

        var resultado = tipoAcessoSvc.save(tipoAcessoModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());
        
        return "redirect:/tiposDeAcesso";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = tipoAcessoSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeAcesso";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid TipoAcessoDto tipoAcessoDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("tipoAcessoDto", tipoAcessoDto);
            model.addAttribute("listaDeTiposDeAcesso", tipoAcessoSvc.findAll(pageable));
            return "tipoAcesso/atualizarTipoAcesso";
        }

        Optional<TipoAcessoModel> tipoAcessoModelOptional = tipoAcessoSvc.findById(id);

        if (tipoAcessoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeAcessoNaoEncontrado()
            );

            return "redirect:/tiposDeAcesso";
        }

        var tipoAcessoModel = tipoAcessoModelOptional.get();

        BeanUtils.copyProperties(tipoAcessoDto, tipoAcessoModel);

        var resultado = tipoAcessoSvc.update(tipoAcessoModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/tiposDeAcesso";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        Iterable<TipoAcessoModel> listaDeTiposDeAcesso = tipoAcessoSvc.findAll(pageable);
        TipoAcessoDto tipoAcessoDto = new TipoAcessoDto();

        model.addAttribute("listaDeTiposDeAcesso", listaDeTiposDeAcesso);
        model.addAttribute("tipoAcessoDto", tipoAcessoDto);

        return "tipoAcesso/cadastrarTipoAcesso";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes) {

        Optional<TipoAcessoModel> tipoAcessoModelOptional = tipoAcessoSvc.findById(id);

        if (tipoAcessoModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.tipoDeEquipamentoNaoEncontrado()
            );

            return "redirect:/tiposDeAcesso";
        }

        model.addAttribute("tipoAcessoDto", tipoAcessoModelOptional.get());

        return "tipoAcesso/atualizarTipoAcesso";
    }
}
