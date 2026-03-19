package com.aplicaciones_web.tickets.entity;

import com.aplicaciones_web.tickets.enums.PrioridadTicket;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String asunto;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadTicket prioridad = PrioridadTicket.MEDIA;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    public Ticket() {
    }

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Ticket(Long id, String asunto, String descripcion, PrioridadTicket prioridad,
                  LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion,
                  Usuario usuario, Usuario tecnico, Categoria categoria, Estado estado) {
        this.id = id;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.usuario = usuario;
        this.tecnico = tecnico;
        this.categoria = categoria;
        this.estado = estado;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}