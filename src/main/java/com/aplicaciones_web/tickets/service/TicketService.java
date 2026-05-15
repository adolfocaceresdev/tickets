package com.aplicaciones_web.tickets.service;

import com.aplicaciones_web.tickets.dto.request.TicketRequestDTO;
import com.aplicaciones_web.tickets.dto.response.CategoriaResponseDTO;
import com.aplicaciones_web.tickets.dto.response.TicketResponseDTO;
import com.aplicaciones_web.tickets.dto.response.UsuarioResponseDTO;
import com.aplicaciones_web.tickets.entity.Categoria;
import com.aplicaciones_web.tickets.entity.Ticket;
import com.aplicaciones_web.tickets.entity.Usuario;
import com.aplicaciones_web.tickets.repository.CategoriaRepository;
import com.aplicaciones_web.tickets.repository.TicketRepository;
import com.aplicaciones_web.tickets.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public TicketService(TicketRepository ticketRepository,
                         UsuarioRepository usuarioRepository,
                         CategoriaRepository categoriaRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<TicketResponseDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public TicketResponseDTO findById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);

        if (ticket == null) {
            return null;
        }

        return toResponseDTO(ticket);
    }

    public TicketResponseDTO save(TicketRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElse(null);

        if (usuario == null || categoria == null) {
            return null;
        }

        Usuario tecnico = null;

        if (dto.getTecnicoId() != null) {
            tecnico = usuarioRepository.findById(dto.getTecnicoId()).orElse(null);

            if (tecnico == null) {
                return null;
            }
        }

        Ticket ticket = new Ticket();
        ticket.setAsunto(dto.getAsunto());
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setEstado(dto.getEstado());
        ticket.setUsuario(usuario);
        ticket.setTecnico(tecnico);
        ticket.setCategoria(categoria);

        Ticket ticketGuardado = ticketRepository.save(ticket);

        return toResponseDTO(ticketGuardado);
    }

    public TicketResponseDTO update(Long id, TicketRequestDTO dto) {
        Ticket ticketExistente = ticketRepository.findById(id).orElse(null);

        if (ticketExistente == null) {
            return null;
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId()).orElse(null);

        if (usuario == null || categoria == null) {
            return null;
        }

        Usuario tecnico = null;

        if (dto.getTecnicoId() != null) {
            tecnico = usuarioRepository.findById(dto.getTecnicoId()).orElse(null);

            if (tecnico == null) {
                return null;
            }
        }

        ticketExistente.setAsunto(dto.getAsunto());
        ticketExistente.setDescripcion(dto.getDescripcion());
        ticketExistente.setPrioridad(dto.getPrioridad());
        ticketExistente.setEstado(dto.getEstado());
        ticketExistente.setUsuario(usuario);
        ticketExistente.setTecnico(tecnico);
        ticketExistente.setCategoria(categoria);

        Ticket ticketActualizado = ticketRepository.save(ticketExistente);

        return toResponseDTO(ticketActualizado);
    }

    public boolean deleteById(Long id) {
        if (!ticketRepository.existsById(id)) {
            return false;
        }

        ticketRepository.deleteById(id);
        return true;
    }

    private TicketResponseDTO toResponseDTO(Ticket ticket) {
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                ticket.getUsuario().getId(),
                ticket.getUsuario().getNombre(),
                ticket.getUsuario().getApellido(),
                ticket.getUsuario().getEmail(),
                ticket.getUsuario().getRol(),
                ticket.getUsuario().getActivo()
        );

        UsuarioResponseDTO tecnicoDTO = null;

        if (ticket.getTecnico() != null) {
            tecnicoDTO = new UsuarioResponseDTO(
                    ticket.getTecnico().getId(),
                    ticket.getTecnico().getNombre(),
                    ticket.getTecnico().getApellido(),
                    ticket.getTecnico().getEmail(),
                    ticket.getTecnico().getRol(),
                    ticket.getTecnico().getActivo()
            );
        }

        CategoriaResponseDTO categoriaDTO = new CategoriaResponseDTO(
                ticket.getCategoria().getId(),
                ticket.getCategoria().getNombre(),
                ticket.getCategoria().getDescripcion()
        );

        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getAsunto(),
                ticket.getDescripcion(),
                ticket.getPrioridad(),
                ticket.getEstado(),
                ticket.getFechaCreacion(),
                ticket.getFechaActualizacion(),
                usuarioDTO,
                tecnicoDTO,
                categoriaDTO
        );
    }
}