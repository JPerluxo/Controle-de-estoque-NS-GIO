package com.controleestoquensgio.controllers;

import com.controleestoquensgio.dtos.colaborador.ListarColaboradoresDto;
import com.controleestoquensgio.dtos.setor.ListarSetorDto;
import com.controleestoquensgio.dtos.setor.SetorDto;
import com.controleestoquensgio.dtos.setor.VisualizarSetorDto;
import com.controleestoquensgio.models.SetorModel;
import com.controleestoquensgio.services.ColaboradorService;
import com.controleestoquensgio.services.SetorService;
import com.controleestoquensgio.util.ErroOuSucesso;
import com.controleestoquensgio.util.Mensagens;
import com.controleestoquensgio.util.SimOuNao;
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

import java.util.Optional;

@Controller
@RequestMapping(value = {"/setores"})
public class SetorController {

    @Autowired
    SetorService setorSvc;

    @Autowired
    ColaboradorService colaboradorSvc;

    @PostMapping
    public String save(@Valid SetorDto setorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("setorDto", setorDto);
            model.addAttribute("listaDeSetores", setorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarSetorDto::new));
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));
            return "setor/cadastrarSetor";
        }

        var setorModel = new SetorModel();

        BeanUtils.copyProperties(setorDto, setorModel);
        
        var resultado = setorSvc.save(setorDto, setorModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/setores";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = setorSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/setores";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid SetorDto setorDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("setorDto", setorDto);
            model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));
            return "setor/atualizarSetor";
        }

        Optional<SetorModel> setorModelOptional = setorSvc.findById(id);

        if (setorModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.setorNaoEncontrado()
            );

            return "redirect:/setores";
        }

        var setorModel = setorModelOptional.get();

        BeanUtils.copyProperties(setorDto, setorModel);

        var resultado = setorSvc.save(setorDto, setorModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/setores";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeSetores", setorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarSetorDto::new));
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));
        model.addAttribute("setorDto", new SetorDto());

        return "setor/cadastrarSetor";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {

        Optional<SetorModel> setorModelOptional = setorSvc.findById(id);

        if (setorModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.setorNaoEncontrado()
            );

            return "redirect:/setores";
        }

        model.addAttribute("setorDto", new VisualizarSetorDto(setorModelOptional.get()));
        model.addAttribute("listaDeColaboradores", colaboradorSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarColaboradoresDto::new));

        return "setor/atualizarSetor";
    }

}
