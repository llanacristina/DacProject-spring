package br.com.projectDac.SistemaEstagio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projectDac.SistemaEstagio.entities.Empresa;
import br.com.projectDac.SistemaEstagio.repositories.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    //CRIA EMPRESA
    @PostMapping
    public ResponseEntity<Empresa> createEmpresa(@Validated @RequestBody Empresa empresa) {
        Empresa newEmpresa = empresaRepository.save(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmpresa);
    }

    //LISTA EMPRESAS
      @GetMapping
        public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }

    //ATUALIZA EMPRESA POR ID
    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @Validated @RequestBody Empresa updatedEmpresa) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresa.setName(updatedEmpresa.getName());
                    empresa.setUsername(updatedEmpresa.getUsername());
                    empresa.setEndereco(updatedEmpresa.getEndereco());
                    empresa.setSenha(updatedEmpresa.getSenha());
                    // Atualize outros campos conforme necessário
                    Empresa updatedEmpresaDB = empresaRepository.save(empresa);
                    return ResponseEntity.ok().body(updatedEmpresaDB);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETA EMPRESA POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .map(empresa -> {
                    empresaRepository.delete(empresa);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    //LISTA EMPRESA ESPECÍFICO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long id) {
        return empresaRepository.findById(id)
                .map(empresa -> ResponseEntity.ok().body(empresa))
                .orElse(ResponseEntity.notFound().build());
    }
}
