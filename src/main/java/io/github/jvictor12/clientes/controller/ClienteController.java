package io.github.jvictor12.clientes.controller;

import io.github.jvictor12.clientes.entity.Cliente;
import io.github.jvictor12.clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity findAll(){ return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){ return ResponseEntity.status(HttpStatus.OK).body(clienteService.findById(id)); }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid Cliente cliente){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.update(cliente));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(cliente));
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
