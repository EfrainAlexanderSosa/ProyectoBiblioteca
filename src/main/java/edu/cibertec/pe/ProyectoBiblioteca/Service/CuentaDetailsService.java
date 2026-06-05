package edu.cibertec.pe.ProyectoBiblioteca.Service;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Cuenta;
import edu.cibertec.pe.ProyectoBiblioteca.Repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CuentaDetailsService implements UserDetailsService {
    @Autowired
    private CuentaRepository cuentaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cuenta cuenta = cuentaRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        return User.builder()
                .username(cuenta.getUsername())
                .password(cuenta.getPassword())
                .roles("USER")
                .disabled(!cuenta.isEnabled())
                .build();
    }
}
