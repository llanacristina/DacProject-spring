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

import br.com.projectDac.SistemaEstagio.entities.Estagio;
import br.com.projectDac.SistemaEstagio.repositories.EstagioRepository;

@RestController
@RequestMapping("/estagios")
public class EstagioController {

    @Autowired
    private EstagioRepository estagioRepository;

    //CRIA ESTAGIO
    @PostMapping
    public ResponseEntity<Estagio> createEstagio(@Validated @RequestBody Estagio estagio) {
        Estagio newEstagio = estagioRepository.save(estagio);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEstagio);
    }

    //LISTA ESTAGIO
      @GetMapping
        public List<Estagio> getAllEstagios() {
        return estagioRepository.findAll();
    }

    //ATUALIZA ESTAGIO POR ID
    @PutMapping("/{id}")
    public ResponseEntity<Estagio> updateEstagio(@PathVariable Long id, @Validated @RequestBody Estagio updatedEstagio) {
        return estagioRepository.findById(id)
                .map(estagio -> {
                    estagio.setName(updatedEstagio.getName());
                    estagio.setCargaHoraria(updatedEstagio.getCargaHoraria());
                    estagio.setInicio(updatedEstagio.getInicio());
                    estagio.setFim(updatedEstagio.getFim());
                    estagio.setStatus(updatedEstagio.getStatus());
                    // Atualize outros campos conforme necessário
                    Estagio updatedEstagioDB = estagioRepository.save(estagio);
                    return ResponseEntity.ok().body(updatedEstagioDB);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETA ESTAGIO POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstagio(@PathVariable Long id) {
        return estagioRepository.findById(id)
                .map(estagio -> {
                    estagioRepository.delete(estagio);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    //LISTA ORIENTADOR ESPECÍFICO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Estagio> getEstagioById(@PathVariable Long id) {
        return estagioRepository.findById(id)
                .map(estagio -> ResponseEntity.ok().body(estagio))
                .orElse(ResponseEntity.notFound().build());
    }
}
