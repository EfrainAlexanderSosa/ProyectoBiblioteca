package edu.cibertec.pe.ProyectoBiblioteca.Controller;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Idioma;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/idiomas")
public class IdiomaController {
    @Autowired
    private IdiomaRepository idiomaRepository;

    @GetMapping
    public String listarIdiomas(Model model) {
        model.addAttribute("idiomas", idiomaRepository.findAll());
        return "idiomas/index";
    }
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("idioma", new Idioma());
        return "idiomas/formulario";
    }
    @PostMapping("/guardar")
    public String guardarIdioma(@ModelAttribute Idioma idioma) {
        idiomaRepository.save(idioma);
        return "redirect:/idiomas";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Idioma idioma = idiomaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("idioma", idioma);
        return "idiomas/formulario";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarIdioma(@PathVariable Integer id) {
        idiomaRepository.deleteById(id);
        return "redirect:/idiomas";
    }
}
