package edu.cibertec.pe.ProyectoBiblioteca.Repository;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
    
    // 🔥 AGREGAR: Buscar autor por nombre
    Optional<Autor> findByNombre(String nombre);
}