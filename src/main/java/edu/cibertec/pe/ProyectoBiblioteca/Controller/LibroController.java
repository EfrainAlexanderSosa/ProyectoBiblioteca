package edu.cibertec.pe.ProyectoBiblioteca.Controller;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Libro;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.AutorRepository;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.GeneroRepository;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.IdiomaRepository;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.LibroRepository;
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
@RequestMapping("/libros")
public class LibroController {
    
    @Autowired
    private LibroRepository libroRepo;
    
    @Autowired
    private AutorRepository autorRepo;
    
    @Autowired
    private IdiomaRepository idiomaRepo;
    
    @Autowired
    private GeneroRepository generoRepo;
    
    @Autowired
    private PdfService pdfService;
    
    @GetMapping
    public String listarLibros(
            @RequestParam(required = false) String busqueda,
            Model model) {
        
        List<Libro> libros;
        
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            libros = libroRepo.buscarPorTituloOAutor(busqueda);
        } else {
            libros = libroRepo.findAll();
        }
        
        model.addAttribute("libros", libros);
        model.addAttribute("busqueda", busqueda);
        return "libros/index";
    }
    
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarPdfLibros(
            @RequestParam(required = false) String busqueda) {
        
        List<Libro> libros;
        
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            libros = libroRepo.buscarPorTituloOAutor(busqueda);
        } else {
            libros = libroRepo.findAll();
        }
        
        byte[] pdfBytes = pdfService.generarPdfLibros(libros);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "libros.pdf");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
    
    @GetMapping("/nuevo")
    public String nuevoLibro(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("autores", autorRepo.findAll());
        model.addAttribute("idiomas", idiomaRepo.findAll());
        model.addAttribute("generos", generoRepo.findAll());
        return "libros/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro) {
        libroRepo.save(libro);
        return "redirect:/libros";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Integer id) {
        libroRepo.deleteById(id);
        return "redirect:/libros";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Libro libro = libroRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de libro inválido: " + id));
        model.addAttribute("libro", libro);
        model.addAttribute("autores", autorRepo.findAll());
        model.addAttribute("idiomas", idiomaRepo.findAll());
        model.addAttribute("generos", generoRepo.findAll());
        return "libros/formulario";
    }
}