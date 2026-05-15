package com.aplicaciones_web.tickets.service;

import com.aplicaciones_web.tickets.dto.request.TicketRequestDTO;
import com.aplicaciones_web.tickets.dto.response.CategoriaResponseDTO;
import com.aplicaciones_web.tickets.dto.response.TicketResponseDTO;
import com.aplicaciones_web.tickets.dto.response.UsuarioResponseDTO;
import com.aplicaciones_web.tickets.entity.Categoria;
import com.aplicaciones_web.tickets.entity.Ticket;
import com.aplicaciones_web.tickets.entity.Usuario;
import com.aplicaciones_web.tickets.exception.ResourceNotFoundException;
import com.aplicaciones_web.tickets.repository.CategoriaRepository;
import com.aplicaciones_web.tickets.repository.TicketRepository;
import com.aplicaciones_web.tickets.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    public TicketService(TicketRepository ticketRepository,
                         UsuarioRepository usuarioRepository,
                         CategoriaRepository categoriaRepository,
                         ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<TicketResponseDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public TicketResponseDTO findById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con ID: " + id));

        return toResponseDTO(ticket);
    }

    @Transactional
    public TicketResponseDTO save(TicketRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Usuario tecnico = null;

        if (dto.getTecnicoId() != null) {
            tecnico = usuarioRepository.findById(dto.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + dto.getTecnicoId()));
        }

        Ticket ticket = modelMapper.map(dto, Ticket.class);

        ticket.setUsuario(usuario);
        ticket.setTecnico(tecnico);
        ticket.setCategoria(categoria);

        Ticket ticketGuardado = ticketRepository.save(ticket);

        return toResponseDTO(ticketGuardado);
    }

    @Transactional
    public TicketResponseDTO update(Long id, TicketRequestDTO dto) {
        Ticket ticketExistente = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con ID: " + id));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + dto.getCategoriaId()));

        Usuario tecnico = null;

        if (dto.getTecnicoId() != null) {
            tecnico = usuarioRepository.findById(dto.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + dto.getTecnicoId()));
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

    @Transactional
    public void deleteById(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ticket no encontrado con ID: " + id);
        }

        ticketRepository.deleteById(id);
    }

    private TicketResponseDTO toResponseDTO(Ticket ticket) {
        UsuarioResponseDTO usuarioDTO = modelMapper.map(ticket.getUsuario(), UsuarioResponseDTO.class);

        UsuarioResponseDTO tecnicoDTO = null;

        if (ticket.getTecnico() != null) {
            tecnicoDTO = modelMapper.map(ticket.getTecnico(), UsuarioResponseDTO.class);
        }

        CategoriaResponseDTO categoriaDTO = modelMapper.map(ticket.getCategoria(), CategoriaResponseDTO.class);

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