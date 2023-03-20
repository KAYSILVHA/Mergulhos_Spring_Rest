package com.example.demo.domain.service;
import com.example.demo.domain.exception.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Entrega;
import com.example.demo.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
