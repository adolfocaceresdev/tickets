package com.aplicaciones_web.tickets.dto.response;

import com.aplicaciones_web.tickets.enums.RolUsuario;

public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private RolUsuario rol;
    private Boolean activo;

    public UsuarioResponseDTO(Long id, String nombre, String apellido, String email, RolUsuario rol, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.rol = rol;
        this.activo = activo;
    }

    public Long getId() {
        return id;
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

    public RolUsuario getRol() {
        return rol;
    }

    public Boolean getActivo() {
        return activo;
    }
}