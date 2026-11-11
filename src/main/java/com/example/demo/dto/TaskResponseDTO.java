package com.example.demo.dto;

import com.example.demo.model.Prioridade;

public record TaskResponseDTO(
        Long id,
        String titulo,
        boolean concluida,
        Prioridade prioridade
) {}