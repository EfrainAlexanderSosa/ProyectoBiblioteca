package edu.cibertec.pe.ProyectoBiblioteca.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {
    @GetMapping("/")
    public String redirigirAlInicio() {
        return "redirect:/inicio";
    }
    @GetMapping("/inicio")
    public String mostrarInicio() {
        return "inicio";
    }
    @GetMapping("/nosotros")
    public String mostrarNosotros() {
        return "nosotros";
    }
    @GetMapping("/contacto")
    public String mostrarContacto() {
        return "contacto";
    }

    @GetMapping("/gestion")
    public String mostrarGestion() {
        return "gestion";
    }
}
