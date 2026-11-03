package br.com.sistemas.chamados.controller;

import br.com.sistemas.chamados.dto.CriarChamadoRequest;
import br.com.sistemas.chamados.service.ChamadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chamados")
public class ChamadoViewController {

    private final ChamadoService service;

    public ChamadoViewController(ChamadoService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("chamados", service.listarRespostas());
        return "chamados/lista";
    }

    @GetMapping("/novo")
    public String novo() {
        return "chamados/formulario";
    }

    @PostMapping
    public String criar(CriarChamadoRequest request) {
        service.abrir(request);
        return "redirect:/chamados";
    }
}