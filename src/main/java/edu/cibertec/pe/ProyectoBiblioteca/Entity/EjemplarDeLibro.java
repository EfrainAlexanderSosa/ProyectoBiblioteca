package edu.cibertec.pe.ProyectoBiblioteca.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull; // ✅ CORREGIDO
import java.time.LocalDate;

@Entity
public class EjemplarDeLibro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEjemplar;

    @Column(nullable = false, unique = true, length = 255)
    private String idUnico;

    private LocalDate fechaDeDevolucion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoEjemplar estado;

    @Column(length = 255)
    private String pieDeImprenta;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_prestatario")
    private Usuario prestatario;

    public EjemplarDeLibro() {
    }

    // Getters y Setters
    public Integer getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(Integer idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public String getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(String idUnico) {
        this.idUnico = idUnico;
    }

    public LocalDate getFechaDeDevolucion() {
        return fechaDeDevolucion;
    }

    public void setFechaDeDevolucion(LocalDate fechaDeDevolucion) {
        this.fechaDeDevolucion = fechaDeDevolucion;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }

    public String getPieDeImprenta() {
        return pieDeImprenta;
    }

    public void setPieDeImprenta(String pieDeImprenta) {
        this.pieDeImprenta = pieDeImprenta;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getPrestatario() {
        return prestatario;
    }

    public void setPrestatario(Usuario prestatario) {
        this.prestatario = prestatario;
    }
}