package com.aplicaciones_web.tickets.dto.request;

import com.aplicaciones_web.tickets.enums.EstadoTicket;
import com.aplicaciones_web.tickets.enums.PrioridadTicket;

public class TicketRequestDTO {

    private String asunto;
    private String descripcion;
    private PrioridadTicket prioridad;
    private EstadoTicket estado;
    private Long usuarioId;
    private Long tecnicoId;
    private Long categoriaId;

    public TicketRequestDTO() {
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getTecnicoId() {
        return tecnicoId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(PrioridadTicket prioridad) {
        this.prioridad = prioridad;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setTecnicoId(Long tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }
}