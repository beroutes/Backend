package com.beroutes.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Rutas.
 */
@Entity
@Table(name = "rutas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rutas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "titulo", length = 30, nullable = false, unique = true)
    private String titulo;

    @NotNull
    @Column(name = "duracion", nullable = false)
    private Double duracion;

    @NotNull
    @Column(name = "temporada", nullable = false)
    private String temporada;

    @NotNull
    @Column(name = "presupuesto", nullable = false)
    private Double presupuesto;

    @NotNull
    @Size(min = 10)
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Rutas titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getDuracion() {
        return duracion;
    }

    public Rutas duracion(Double duracion) {
        this.duracion = duracion;
        return this;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getTemporada() {
        return temporada;
    }

    public Rutas temporada(String temporada) {
        this.temporada = temporada;
        return this;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public Double getPresupuesto() {
        return presupuesto;
    }

    public Rutas presupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
        return this;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Rutas descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rutas)) {
            return false;
        }
        return id != null && id.equals(((Rutas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Rutas{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", duracion=" + getDuracion() +
            ", temporada='" + getTemporada() + "'" +
            ", presupuesto=" + getPresupuesto() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
