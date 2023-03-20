package com.example.demo.domain.service;

import com.example.demo.domain.model.Entrega;
import com.example.demo.domain.model.Ocorrencia;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }

}
