package com.aplicaciones_web.tickets.controller;

import com.aplicaciones_web.tickets.dto.request.UsuarioRequestDTO;
import com.aplicaciones_web.tickets.dto.response.UsuarioResponseDTO;
import com.aplicaciones_web.tickets.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        List<UsuarioResponseDTO> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        UsuarioResponseDTO usuario = usuarioService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> save(@Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO nuevoUsuario = usuarioService.save(dto);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuarioActualizado = usuarioService.update(id, dto);
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}