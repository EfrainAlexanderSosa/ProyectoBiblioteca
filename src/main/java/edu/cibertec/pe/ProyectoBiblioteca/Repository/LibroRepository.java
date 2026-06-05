package edu.cibertec.pe.ProyectoBiblioteca.Repository;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    
    // Buscar por título o nombre del autor
    @Query("SELECT l FROM Libro l WHERE " +
           "LOWER(l.titulo) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(l.autor.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Libro> buscarPorTituloOAutor(@Param("busqueda") String busqueda);
}