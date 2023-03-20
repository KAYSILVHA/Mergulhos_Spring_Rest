package com.example.demo.api.controller;
import com.example.demo.api.assembler.EntregaAssembler;
import com.example.demo.api.model.EntregaModel;
import com.example.demo.api.model.input.EntregaInput;
import com.example.demo.domain.model.Entrega;
import com.example.demo.domain.repository.EntregaRepository;
import com.example.demo.domain.service.FinalizacaoEntregaService;
import com.example.demo.domain.service.SolicitacaoEntregaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController{

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);

        return entregaAssembler.toModel(entregaSolicitada);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @GetMapping
    public List<EntregaModel> listar() {
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId).map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega))).orElse(ResponseEntity.notFound().build());
    }

}
