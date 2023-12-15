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

import br.com.projectDac.SistemaEstagio.entities.Orientador;
import br.com.projectDac.SistemaEstagio.repositories.OrientadorRepository;

@RestController
@RequestMapping("/orientadores")
public class OrientadorController {

    @Autowired
    private OrientadorRepository orientadorRepository;

    //CRIA ORIENTADOR
    @PostMapping
    public ResponseEntity<Orientador> createOrientador(@Validated @RequestBody Orientador orientador) {
        Orientador newOrientador = orientadorRepository.save(orientador);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrientador);
    }

    //LISTA ORIENTADOR
      @GetMapping
        public List<Orientador> getAllOrientadores() {
        return orientadorRepository.findAll();
    }

    //ATUALIZA ORIENTADOR POR ID
    @PutMapping("/{id}")
    public ResponseEntity<Orientador> updateOrientador(@PathVariable Long id, @Validated @RequestBody Orientador updatedOrientador) {
        return orientadorRepository.findById(id)
                .map(orientandor -> {
                    orientandor.setName(updatedOrientador.getName());
                    orientandor.setCargo(updatedOrientador.getCargo());
                    orientandor.setAreaEspecializacao(updatedOrientador.getAreaEspecializacao());
                    orientandor.setUsername(updatedOrientador.getUsername());
                    orientandor.setSenha(updatedOrientador.getSenha());
                    // Atualize outros campos conforme necessário
                    Orientador updatedOrientadorDB = orientadorRepository.save(orientandor);
                    return ResponseEntity.ok().body(updatedOrientadorDB);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETA ORIENTADOR POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrientador(@PathVariable Long id) {
        return orientadorRepository.findById(id)
                .map(orientador -> {
                    orientadorRepository.delete(orientador);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    //LISTA ORIENTADOR ESPECÍFICO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Orientador> getEmpresaById(@PathVariable Long id) {
        return orientadorRepository.findById(id)
                .map(orientador -> ResponseEntity.ok().body(orientador))
                .orElse(ResponseEntity.notFound().build());
    }
}
