package com.aplicaciones_web.tickets.dto.request;

import com.aplicaciones_web.tickets.enums.EstadoTicket;
import com.aplicaciones_web.tickets.enums.PrioridadTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TicketRequestDTO {

    @NotBlank(message = "El asunto es obligatorio")
    @Size(max = 150, message = "El asunto no puede superar los 150 caracteres")
    private String asunto;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La prioridad es obligatoria")
    private PrioridadTicket prioridad;

    @NotNull(message = "El estado es obligatorio")
    private EstadoTicket estado;

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    private Long tecnicoId;

    @NotNull(message = "La categoría es obligatoria")
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