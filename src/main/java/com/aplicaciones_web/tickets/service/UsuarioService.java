package com.aplicaciones_web.tickets.service;

import com.aplicaciones_web.tickets.dto.request.UsuarioRequestDTO;
import com.aplicaciones_web.tickets.dto.response.UsuarioResponseDTO;
import com.aplicaciones_web.tickets.entity.Usuario;
import com.aplicaciones_web.tickets.exception.ResourceNotFoundException;
import com.aplicaciones_web.tickets.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> modelMapper.map(usuario, UsuarioResponseDTO.class))
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    @Transactional
    public UsuarioResponseDTO save(UsuarioRequestDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);

        if (usuario.getActivo() == null) {
            usuario.setActivo(true);
        }

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return modelMapper.map(usuarioGuardado, UsuarioResponseDTO.class);
    }

    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        usuarioExistente.setNombre(dto.getNombre());
        usuarioExistente.setApellido(dto.getApellido());
        usuarioExistente.setEmail(dto.getEmail());
        usuarioExistente.setPassword(dto.getPassword());
        usuarioExistente.setRol(dto.getRol());

        if (dto.getActivo() != null) {
            usuarioExistente.setActivo(dto.getActivo());
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);

        return modelMapper.map(usuarioActualizado, UsuarioResponseDTO.class);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }

        usuarioRepository.deleteById(id);
    }
}