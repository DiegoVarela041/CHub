package com.chub.persistence.model;

import com.chub.DTO.curso.DetalleCurso;
import jakarta.persistence.*;


@Table(name = "cursos")
@Entity(name = "Curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;
    private String nombre;
    private String categoria;
    private boolean estado;

    public Curso() {
    }

    public Curso(Long id, String nombre, String categoria, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void actualizarDatos(DetalleCurso datosActualizarCurso) {
        if (datosActualizarCurso.nombre() != null) {
            this.nombre = datosActualizarCurso.nombre();
        }
        if (datosActualizarCurso.categoria() != null) {
            this.categoria = datosActualizarCurso.categoria();
        }
        if (datosActualizarCurso.estado() != null) {
            this.estado = datosActualizarCurso.estado();
        }
    }
}
