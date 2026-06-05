package edu.cibertec.pe.ProyectoBiblioteca.Controller;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Autor;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        return "autores/index";
    }
    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/formulario";
    }
    @PostMapping("/guardar")
    public String guardarAutor(@ModelAttribute Autor autor) {
        autorRepository.save(autor);
        return "redirect:/autores";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            model.addAttribute("autor", autor.get());
            return "autores/formulario";
        } else {
            return "redirect:/autores";
        }
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Integer id) {
        autorRepository.deleteById(id);
        return "redirect:/autores";
    }

}
