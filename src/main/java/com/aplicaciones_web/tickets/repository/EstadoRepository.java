package com.aplicaciones_web.tickets.repository;

import com.aplicaciones_web.tickets.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
