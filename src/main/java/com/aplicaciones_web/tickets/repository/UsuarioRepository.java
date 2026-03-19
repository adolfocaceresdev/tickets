package com.aplicaciones_web.tickets.repository;

import com.aplicaciones_web.tickets.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}