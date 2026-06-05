package edu.cibertec.pe.ProyectoBiblioteca.Controller;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.EjemplarDeLibro;
import edu.cibertec.pe.ProyectoBiblioteca.Entity.EstadoEjemplar;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.EjemplarDeLibroRepository;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.LibroRepository;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ejemplares")
public class EjemplarDeLibroController {
    @Autowired
    private EjemplarDeLibroRepository ejemplarRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listarEjemplares(Model model) {
        model.addAttribute("ejemplares", ejemplarRepository.findAll());
        return "ejemplares/index";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("ejemplar", new EjemplarDeLibro());
        model.addAttribute("libros", libroRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("estados", EstadoEjemplar.values());
        return "ejemplares/formulario";
    }

    @PostMapping("/guardar")
    public String guardarEjemplar(@ModelAttribute EjemplarDeLibro ejemplar) {
        ejemplarRepository.save(ejemplar);
        return "redirect:/ejemplares";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        EjemplarDeLibro ejemplar = ejemplarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("ejemplar", ejemplar);
        model.addAttribute("libros", libroRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("estados", EstadoEjemplar.values());
        return "ejemplares/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEjemplar(@PathVariable Integer id) {
        ejemplarRepository.deleteById(id);
        return "redirect:/ejemplares";
    }
}
