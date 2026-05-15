package com.aplicaciones_web.tickets.dto.request;

import com.aplicaciones_web.tickets.enums.RolUsuario;

public class UsuarioRequestDTO {

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private RolUsuario rol;
    private Boolean activo;

    public UsuarioRequestDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}