package edu.cibertec.pe.ProyectoBiblioteca.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(length = 100, unique = true)
    private String email;

    @OneToMany(mappedBy = "prestatario")
    private List<EjemplarDeLibro> ejemplaresPrestados;

    public Usuario() {
    }

    // Getters y Setters
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<EjemplarDeLibro> getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(List<EjemplarDeLibro> ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }
}
