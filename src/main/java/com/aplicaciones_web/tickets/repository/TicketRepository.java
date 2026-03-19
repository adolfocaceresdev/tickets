package com.aplicaciones_web.tickets.repository;

import com.aplicaciones_web.tickets.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}