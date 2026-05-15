package com.aplicaciones_web.tickets.service;

import com.aplicaciones_web.tickets.dto.request.UsuarioRequestDTO;
import com.aplicaciones_web.tickets.dto.response.UsuarioResponseDTO;
import com.aplicaciones_web.tickets.entity.Usuario;
import com.aplicaciones_web.tickets.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return null;
        }

        return toResponseDTO(usuario);
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());

        if (dto.getActivo() != null) {
            usuario.setActivo(dto.getActivo());
        }

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return toResponseDTO(usuarioGuardado);
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if (usuarioExistente == null) {
            return null;
        }

        usuarioExistente.setNombre(dto.getNombre());
        usuarioExistente.setApellido(dto.getApellido());
        usuarioExistente.setEmail(dto.getEmail());
        usuarioExistente.setPassword(dto.getPassword());
        usuarioExistente.setRol(dto.getRol());

        if (dto.getActivo() != null) {
            usuarioExistente.setActivo(dto.getActivo());
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);

        return toResponseDTO(usuarioActualizado);
    }

    public boolean deleteById(Long id) {
        if (!usuarioRepository.existsById(id)) {
            return false;
        }

        usuarioRepository.deleteById(id);
        return true;
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getActivo()
        );
    }
}