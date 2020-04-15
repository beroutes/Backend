package com.beroutes.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A Usuarios.
 */
@Entity
@Table(name = "usuarios")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "nombre", length = 20, nullable = false)
    private String nombre;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name = "apellidos", length = 30, nullable = false)
    private String apellidos;

    @NotNull
    @Size(min = 5, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "email", length = 30, nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 6, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "usuario", length = 20, nullable = false, unique = true)
    private String usuario;

    @NotNull
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "password", length = 30, nullable = false)
    private String password;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name = "ciudad", length = 20, nullable = false)
    private String ciudad;

    @Size(min = 5, max = 100000)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @Column(name = "url_foto_perfil", length = 100000, unique = true)
    private String urlFotoPerfil;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Usuarios nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Usuarios apellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public Usuarios email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public Usuarios usuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public Usuarios password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getFechaRegistro() {
        return fechaRegistro;
    }

    public Usuarios fechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    public void setFechaRegistro(Instant fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Usuarios ciudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public Usuarios urlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
        return this;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Usuarios)) {
            return false;
        }
        return id != null && id.equals(((Usuarios) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Usuarios{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", email='" + getEmail() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", password='" + getPassword() + "'" +
            ", fechaRegistro='" + getFechaRegistro() + "'" +
            ", ciudad='" + getCiudad() + "'" +
            ", urlFotoPerfil='" + getUrlFotoPerfil() + "'" +
            "}";
    }
}
