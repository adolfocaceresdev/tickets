package com.aplicaciones_web.tickets.repository;

import com.aplicaciones_web.tickets.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}