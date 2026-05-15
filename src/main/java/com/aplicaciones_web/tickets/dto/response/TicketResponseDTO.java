package com.aplicaciones_web.tickets.dto.response;

import com.aplicaciones_web.tickets.enums.EstadoTicket;
import com.aplicaciones_web.tickets.enums.PrioridadTicket;

import java.time.LocalDateTime;

public class TicketResponseDTO {

    private Long id;
    private String asunto;
    private String descripcion;
    private PrioridadTicket prioridad;
    private EstadoTicket estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private UsuarioResponseDTO usuario;
    private UsuarioResponseDTO tecnico;
    private CategoriaResponseDTO categoria;

    public TicketResponseDTO(Long id, String asunto, String descripcion,
                             PrioridadTicket prioridad, EstadoTicket estado,
                             LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion,
                             UsuarioResponseDTO usuario, UsuarioResponseDTO tecnico,
                             CategoriaResponseDTO categoria) {
        this.id = id;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuario = usuario;
        this.tecnico = tecnico;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public PrioridadTicket getPrioridad() {
        return prioridad;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public UsuarioResponseDTO getTecnico() {
        return tecnico;
    }

    public CategoriaResponseDTO getCategoria() {
        return categoria;
    }
}