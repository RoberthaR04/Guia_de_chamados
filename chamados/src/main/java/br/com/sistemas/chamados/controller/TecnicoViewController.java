package br.com.sistemas.chamados.controller;

import br.com.sistemas.chamados.dto.CriarTecnicoRequest;
import br.com.sistemas.chamados.service.TecnicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tecnicos")
public class TecnicoViewController {

    private final TecnicoService service;

    public TecnicoViewController(TecnicoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tecnicos", service.listar());
        return "tecnicos/lista";
    }

    @GetMapping("/novo")
    public String novo() {
        return "tecnicos/formulario";
    }

    @PostMapping
    public String criar(CriarTecnicoRequest request) {
        service.criar(request);
        return "redirect:/tecnicos";
    }
}