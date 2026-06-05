package edu.cibertec.pe.ProyectoBiblioteca.Repository;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
   
    List<Usuario> findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String nombre, String email);
}