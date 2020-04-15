package com.beroutes.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Ubicaciones.
 */
@Entity
@Table(name = "ubicaciones")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ubicaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Column(name = "coordenada_x", nullable = false)
    private Long coordenadaX;

    @Column(name = "coordenada_y")
    private Long coordenadaY;

    @Column(name = "duracion")
    private Double duracion;

    
    @Column(name = "qr", unique = true)
    private String qr;

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

    public Ubicaciones titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Ubicaciones descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCoordenadaX() {
        return coordenadaX;
    }

    public Ubicaciones coordenadaX(Long coordenadaX) {
        this.coordenadaX = coordenadaX;
        return this;
    }

    public void setCoordenadaX(Long coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public Long getCoordenadaY() {
        return coordenadaY;
    }

    public Ubicaciones coordenadaY(Long coordenadaY) {
        this.coordenadaY = coordenadaY;
        return this;
    }

    public void setCoordenadaY(Long coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public Double getDuracion() {
        return duracion;
    }

    public Ubicaciones duracion(Double duracion) {
        this.duracion = duracion;
        return this;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getQr() {
        return qr;
    }

    public Ubicaciones qr(String qr) {
        this.qr = qr;
        return this;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ubicaciones)) {
            return false;
        }
        return id != null && id.equals(((Ubicaciones) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Ubicaciones{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", coordenadaX=" + getCoordenadaX() +
            ", coordenadaY=" + getCoordenadaY() +
            ", duracion=" + getDuracion() +
            ", qr='" + getQr() + "'" +
            "}";
    }
}
