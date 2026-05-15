package com.aplicaciones_web.tickets.service;

import com.aplicaciones_web.tickets.dto.request.CategoriaRequestDTO;
import com.aplicaciones_web.tickets.dto.response.CategoriaResponseDTO;
import com.aplicaciones_web.tickets.entity.Categoria;
import com.aplicaciones_web.tickets.exception.ResourceNotFoundException;
import com.aplicaciones_web.tickets.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoria -> modelMapper.map(categoria, CategoriaResponseDTO.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));

        return modelMapper.map(categoria, CategoriaResponseDTO.class);
    }

    @Transactional
    public CategoriaResponseDTO save(CategoriaRequestDTO dto) {
        Categoria categoria = modelMapper.map(dto, Categoria.class);

        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        return modelMapper.map(categoriaGuardada, CategoriaResponseDTO.class);
    }

    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con ID: " + id));

        categoriaExistente.setNombre(dto.getNombre());
        categoriaExistente.setDescripcion(dto.getDescripcion());

        Categoria categoriaActualizada = categoriaRepository.save(categoriaExistente);

        return modelMapper.map(categoriaActualizada, CategoriaResponseDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada con ID: " + id);
        }

        categoriaRepository.deleteById(id);
    }
}