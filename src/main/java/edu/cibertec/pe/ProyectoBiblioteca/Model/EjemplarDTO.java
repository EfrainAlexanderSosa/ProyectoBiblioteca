package edu.cibertec.pe.ProyectoBiblioteca.Model;

import edu.cibertec.pe.ProyectoBiblioteca.Entity.EjemplarDeLibro;


import java.time.LocalDate;


public class EjemplarDTO {

    private Integer idEjemplar;
    private String titulo;
    private String prestatario;
    private String estado;
    private LocalDate fechaDeDevolucion;

    public EjemplarDTO(EjemplarDeLibro ej) {
        this.idEjemplar = ej.getIdEjemplar();
        this.titulo = ej.getLibro().getTitulo();
        this.prestatario = ej.getPrestatario() != null ? ej.getPrestatario().getNombre() : "N/A";
        this.estado = ej.getEstado().name();
        this.fechaDeDevolucion = ej.getFechaDeDevolucion();
    }

    // Getters para JasperReports
    public Integer getIdEjemplar() { return idEjemplar; }
    public String getTitulo() { return titulo; }
    public String getPrestatario() { return prestatario; }
    public String getEstado() { return estado; }
    public LocalDate getFechaDeDevolucion() { return fechaDeDevolucion; }
}
