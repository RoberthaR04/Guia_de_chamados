package br.com.sistemas.chamados.controller;

import br.com.sistemas.chamados.dto.CriarCategoriaRequest;
import br.com.sistemas.chamados.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categorias")
public class CategoriaViewController {

    private final CategoriaService service;

    public CategoriaViewController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", service.listar());
        return "categorias/lista";
    }

    @GetMapping("/nova")
    public String nova() {
        return "categorias/formulario";
    }

    @PostMapping
    public String criar(CriarCategoriaRequest request) {
        service.criar(request);
        return "redirect:/categorias";
    }
}