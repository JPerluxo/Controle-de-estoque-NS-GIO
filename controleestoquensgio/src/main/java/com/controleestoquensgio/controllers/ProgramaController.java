package com.controleestoquensgio.controllers;

import com.controleestoquensgio.dtos.licenca.ListarLicencaDto;
import com.controleestoquensgio.dtos.programa.ListarProgramaDto;
import com.controleestoquensgio.dtos.programa.ProgramaDto;
import com.controleestoquensgio.dtos.programa.VisualizarProgramaDto;
import com.controleestoquensgio.models.ProgramaModel;
import com.controleestoquensgio.services.LicencaService;
import com.controleestoquensgio.services.ProgramaService;
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
@RequestMapping(value = {"/programas"})
public class ProgramaController {

    @Autowired
    ProgramaService programaSvc;

    @Autowired
    LicencaService licencaSvc;

    @PostMapping
    public String save(@Valid ProgramaDto programaDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            model.addAttribute("programaDto", programaDto);
            model.addAttribute("listaDeProgramas", programaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarProgramaDto::new));
            model.addAttribute("listaDeLicencas", licencaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLicencaDto::new));
            return "programa/cadastrarPrograma";
        }

        var programaModel = new ProgramaModel();

        BeanUtils.copyProperties(programaDto, programaModel);

        var resultado = programaSvc.save(programaDto, programaModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/programas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") int id, RedirectAttributes redirectAttributes) {

        var resultado = programaSvc.deleteById(id);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/programas";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(value = "id") int id, @Valid ProgramaDto programaDto, BindingResult result, Model model, Pageable pageable, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("programaDto", programaDto);
            model.addAttribute("listaDeProgramas", programaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarProgramaDto::new));
            model.addAttribute("listaDeLicencas", licencaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLicencaDto::new));
            return "programa/atualizarPrograma";
        }

        Optional<ProgramaModel> programaModelOptional = programaSvc.findById(id);

        if (programaModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.programaNaoEncontrado()
            );

            return "redirect:/programas";
        }

        var programaModel = programaModelOptional.get();

        BeanUtils.copyProperties(programaDto, programaModel);

        var resultado = programaSvc.save(programaDto, programaModel);

        redirectAttributes.addFlashAttribute(resultado.getErroOuSucesso(), resultado.getMensagem());

        return "redirect:/programas";
    }

    @GetMapping
    public String getAll(Pageable pageable, Model model) {

        model.addAttribute("listaDeProgramas", programaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarProgramaDto::new));
        model.addAttribute("listaDeLicencas", licencaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLicencaDto::new));
        model.addAttribute("programaDto", new ProgramaDto());

        return "programa/cadastrarPrograma";
    }

    @GetMapping("/update/{id}")
    public String showFormUpdate(@PathVariable(value = "id") int id, Model model, RedirectAttributes redirectAttributes, Pageable pageable) {

        Optional<ProgramaModel> programaModelOptional = programaSvc.findById(id);

        if (programaModelOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    ErroOuSucesso.ERRO.name(),
                    Mensagens.programaNaoEncontrado()
            );

            return "redirect:/programas";
        }

        model.addAttribute("programaDto", new VisualizarProgramaDto(programaModelOptional.get()));
        model.addAttribute("listaDeLicencas", licencaSvc.findAllAtivo(pageable, SimOuNao.SIM.name()).map(ListarLicencaDto::new));

        return "programa/atualizarPrograma";
    }

}
