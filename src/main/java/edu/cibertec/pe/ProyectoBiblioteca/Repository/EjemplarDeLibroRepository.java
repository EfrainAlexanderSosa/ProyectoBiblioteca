package edu.cibertec.pe.ProyectoBiblioteca.Repository;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.EjemplarDeLibro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarDeLibroRepository extends JpaRepository<EjemplarDeLibro, Integer> {
}
