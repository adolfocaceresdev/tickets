package com.aplicaciones_web.tickets.controller;

import com.aplicaciones_web.tickets.dto.request.TicketRequestDTO;
import com.aplicaciones_web.tickets.dto.response.TicketResponseDTO;
import com.aplicaciones_web.tickets.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> findAll() {
        List<TicketResponseDTO> tickets = ticketService.findAll();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> findById(@PathVariable Long id) {
        TicketResponseDTO ticket = ticketService.findById(id);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> save(@Valid @RequestBody TicketRequestDTO dto) {
        TicketResponseDTO nuevoTicket = ticketService.save(dto);
        return ResponseEntity.ok(nuevoTicket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> update(@PathVariable Long id,
                                                    @Valid @RequestBody TicketRequestDTO dto) {
        TicketResponseDTO ticketActualizado = ticketService.update(id, dto);
        return ResponseEntity.ok(ticketActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        ticketService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}