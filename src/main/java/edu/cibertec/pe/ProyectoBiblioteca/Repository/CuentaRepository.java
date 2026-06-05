package edu.cibertec.pe.ProyectoBiblioteca.Repository;
import java.util.Optional;
import edu.cibertec.pe.ProyectoBiblioteca.Entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    Optional<Cuenta> findByUsername(String username);
}
