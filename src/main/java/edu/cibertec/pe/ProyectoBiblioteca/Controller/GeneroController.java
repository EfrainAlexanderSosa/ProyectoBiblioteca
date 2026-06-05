package edu.cibertec.pe.ProyectoBiblioteca.Controller;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Genero;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/generos")
public class GeneroController {
    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping
    public String listarGeneros(Model model) {
        model.addAttribute("generos", generoRepository.findAll());
        return "generos/index";
    }
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("genero", new Genero());
        return "generos/formulario";
    }
    @PostMapping("/guardar")
    public String guardarGenero(@ModelAttribute Genero genero) {
        generoRepository.save(genero);
        return "redirect:/generos";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("genero", genero);
        return "generos/formulario";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarGenero(@PathVariable Integer id) {
        generoRepository.deleteById(id);
        return "redirect:/generos";
    }
}
