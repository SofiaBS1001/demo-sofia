package com.example.demo.service;

import com.example.demo.dto.UsuarioResponseDTO;
import com.example.demo.dto.UsuarioResquestDTO;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }


    public UsuarioResponseDTO criar(UsuarioResquestDTO dto) {

        Usuario usuario = new Usuario();
        Usuario salva= repository.salvar(usuario);
        return toResponseDTO(salva);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCargo()
        );
    }

    public List<Usuario> listar() {
        System.out.println("[SERVICE] Solicitando lista de usuarios ao repository");
        return repository.listarTodas();
    }
    public Usuario buscarPorId(Long id) {
        System.out.println("[SERVICE] Processando busca por id: " + id);
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario não encontrada: " + id));
    }

    public Usuario atualizar(Long id, UsuarioResquestDTO usuarioResquestDTO) {
        System.out.println("[SERVICE] Processando busca por id: " + id);
        Usuario usuario= repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Usuario não encontrada: " + id));
        usuario.setNome(usuarioResquestDTO.nome());
        usuario.setEmail(usuarioResquestDTO.email());
        usuario.setCargo(usuarioResquestDTO.cargo());

        return repository.save(usuario);
    }

    public Usuario deletar(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario não encontrada: " + id));
        repository.delete(usuario);
        return usuario;
    }
}