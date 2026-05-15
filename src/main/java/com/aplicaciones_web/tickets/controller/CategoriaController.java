package com.aplicaciones_web.tickets.controller;

import com.aplicaciones_web.tickets.dto.request.CategoriaRequestDTO;
import com.aplicaciones_web.tickets.dto.response.CategoriaResponseDTO;
import com.aplicaciones_web.tickets.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> findAll() {
        List<CategoriaResponseDTO> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> findById(@PathVariable Long id) {
        CategoriaResponseDTO categoria = categoriaService.findById(id);

        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> save(@RequestBody CategoriaRequestDTO dto) {
        CategoriaResponseDTO nuevaCategoria = categoriaService.save(dto);
        return ResponseEntity.ok(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(@PathVariable Long id, @RequestBody CategoriaRequestDTO dto) {
        CategoriaResponseDTO categoriaActualizada = categoriaService.update(id, dto);

        if (categoriaActualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        boolean eliminado = categoriaService.deleteById(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}