package edu.cibertec.pe.ProyectoBiblioteca.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGenero;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @ManyToMany(mappedBy = "generos")
    private List<Libro> libros;

    public Genero() {}

    // Getters y Setters
    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}