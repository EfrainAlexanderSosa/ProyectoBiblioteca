package edu.cibertec.pe.ProyectoBiblioteca.Controller;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Cuenta;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CuentaController {
    @Autowired
    private CuentaRepository cuentaRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "registro";
    }
    @PostMapping("/registro")
    public String registrarCuenta(@ModelAttribute("cuenta") Cuenta cuenta) {
        cuenta.setPassword(passwordEncoder.encode(cuenta.getPassword()));
        cuentaRepo.save(cuenta);
        return "redirect:/login?registrado";
    }
}
