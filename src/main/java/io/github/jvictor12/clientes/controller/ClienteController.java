package io.github.jvictor12.clientes.controller;

import io.github.jvictor12.clientes.entity.Cliente;
import io.github.jvictor12.clientes.service.ClienteService;
import io.github.jvictor12.clientes.utils.ClienteMapperUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController{

    private final ClienteService clienteService;

    private final ClienteMapperUtils clienteMapperUtils;

    @GetMapping
    public ResponseEntity findAll(){ return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){ return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id)); }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody @Valid Cliente cliente){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(cliente));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
