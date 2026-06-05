package edu.cibertec.pe.ProyectoBiblioteca.Controller;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Usuario;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.UsuarioRepository;
import edu.cibertec.pe.ProyectoBiblioteca.Service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PdfService pdfService;
    
    @GetMapping
    public String listarUsuarios(
            @RequestParam(required = false) String busqueda,
            Model model) {
        
        List<Usuario> usuarios;
        
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            usuarios = usuarioRepository
                .findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    busqueda, busqueda);
        } else {
            usuarios = usuarioRepository.findAll();
        }
        
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("busqueda", busqueda);
        return "usuarios/index";
    }
    
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarPdfUsuarios(
            @RequestParam(required = false) String busqueda) {
        
        List<Usuario> usuarios;
        
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            usuarios = usuarioRepository
                .findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    busqueda, busqueda);
        } else {
            usuarios = usuarioRepository.findAll();
        }
        
        byte[] pdfBytes = pdfService.generarPdfUsuarios(usuarios);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "usuarios.pdf");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de usuario inválido: " + id));
        model.addAttribute("usuario", usuario);
        return "usuarios/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }
}