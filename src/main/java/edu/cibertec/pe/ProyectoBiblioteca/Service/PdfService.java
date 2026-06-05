package edu.cibertec.pe.ProyectoBiblioteca.Service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import edu.cibertec.pe.ProyectoBiblioteca.Entity.Libro;
import edu.cibertec.pe.ProyectoBiblioteca.Entity.Usuario;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfService {

    public byte[] generarPdfUsuarios(List<Usuario> usuarios) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            // Título
            Paragraph titulo = new Paragraph("REPORTE DE USUARIOS")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);
            
            // Fecha
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            Paragraph fechaParrafo = new Paragraph("Fecha de generación: " + fecha)
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(fechaParrafo);
            
            document.add(new Paragraph("\n"));
            
            // Tabla
            Table tabla = new Table(UnitValue.createPercentArray(new float[]{1, 3, 4}));
            tabla.setWidth(UnitValue.createPercentValue(100));
            
            // Encabezados
            tabla.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Nombre").setBold()));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Email").setBold()));
            
            // Datos
            for (Usuario usuario : usuarios) {
                tabla.addCell(new Cell().add(new Paragraph(usuario.getIdUsuario().toString())));
                tabla.addCell(new Cell().add(new Paragraph(usuario.getNombre())));
                tabla.addCell(new Cell().add(new Paragraph(usuario.getEmail())));
            }
            
            document.add(tabla);
            
            // Total
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Total de usuarios: " + usuarios.size())
                    .setBold()
                    .setFontSize(12));
            
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return out.toByteArray();
    }

    public byte[] generarPdfLibros(List<Libro> libros) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            // Título
            Paragraph titulo = new Paragraph("CATÁLOGO DE LIBROS")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(titulo);
            
            // Fecha
            String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            Paragraph fechaParrafo = new Paragraph("Fecha de generación: " + fecha)
                    .setFontSize(10)
                    .setTextAlignment(TextAlignment.RIGHT);
            document.add(fechaParrafo);
            
            document.add(new Paragraph("\n"));
            
            // Tabla
            Table tabla = new Table(UnitValue.createPercentArray(new float[]{1, 4, 3, 3, 2}));
            tabla.setWidth(UnitValue.createPercentValue(100));
            
            // Encabezados
            tabla.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Título").setBold()));
            tabla.addHeaderCell(new Cell().add(new Paragraph("ISBN").setBold()));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Autor").setBold()));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Idioma").setBold()));
            
            // Datos
            for (Libro libro : libros) {
                tabla.addCell(new Cell().add(new Paragraph(libro.getIdLibro().toString())));
                tabla.addCell(new Cell().add(new Paragraph(libro.getTitulo())));
                tabla.addCell(new Cell().add(new Paragraph(libro.getIsbn())));
                tabla.addCell(new Cell().add(new Paragraph(libro.getAutor().getNombre())));
                tabla.addCell(new Cell().add(new Paragraph(libro.getIdioma().getNombre())));
            }
            
            document.add(tabla);
            
            // Total
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Total de libros: " + libros.size())
                    .setBold()
                    .setFontSize(12));
            
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return out.toByteArray();
    }
}