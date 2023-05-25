package com.controleestoquensgio.controllers;

import com.controleestoquensgio.util.Mensagens;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrosController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();

        if (response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            modelAndView.addObject("ERRO_CODE", Mensagens.paginaNaoEncontrada());
        }
        else if (response.getStatus() == HttpStatus.FORBIDDEN.value()) {
            modelAndView.addObject("ERRO_CODE", Mensagens.erroForbidden());
        }
        else if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            modelAndView.addObject("ERRO_CODE", Mensagens.erroInterno());
        }
        else {
            modelAndView.addObject("ERRO_CODE", Mensagens.erroInterno());
        }

        modelAndView.setViewName("erros/error");

        return modelAndView;
    }

    @Bean
    public String getErrorPath() {
        return "/error";
    }
}
