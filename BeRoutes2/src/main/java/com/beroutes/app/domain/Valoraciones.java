package com.beroutes.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Valoraciones.
 */
@Entity
@Table(name = "valoraciones")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Valoraciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estrellas")
    private Integer estrellas;

    @Column(name = "comentarios")
    private String comentarios;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public Valoraciones estrellas(Integer estrellas) {
        this.estrellas = estrellas;
        return this;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentarios() {
        return comentarios;
    }

    public Valoraciones comentarios(String comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Valoraciones)) {
            return false;
        }
        return id != null && id.equals(((Valoraciones) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Valoraciones{" +
            "id=" + getId() +
            ", estrellas=" + getEstrellas() +
            ", comentarios='" + getComentarios() + "'" +
            "}";
    }
}
