package com.example.demo.dto;

import com.example.demo.model.Prioridade;
import com.example.demo.model.Usuario;

public record TaskRequestDTO(
        String titulo,
        boolean concluida,
        Prioridade prioridade,
        Usuario usuario
) {
}
