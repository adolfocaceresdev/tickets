package com.aplicaciones_web.tickets.service;

import com.aplicaciones_web.tickets.dto.request.CategoriaRequestDTO;
import com.aplicaciones_web.tickets.dto.response.CategoriaResponseDTO;
import com.aplicaciones_web.tickets.entity.Categoria;
import com.aplicaciones_web.tickets.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponseDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);

        if (categoria == null) {
            return null;
        }

        return toResponseDTO(categoria);
    }

    public CategoriaResponseDTO save(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());

        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        return toResponseDTO(categoriaGuardada);
    }

    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
        Categoria categoriaExistente = categoriaRepository.findById(id).orElse(null);

        if (categoriaExistente == null) {
            return null;
        }

        categoriaExistente.setNombre(dto.getNombre());
        categoriaExistente.setDescripcion(dto.getDescripcion());

        Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);

        return toResponseDTO(categoriaActualizada);
    }

    public boolean deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            return false;
        }

        categoriaRepository.deleteById(id);
        return true;
    }

    private CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion()
        );
    }
}