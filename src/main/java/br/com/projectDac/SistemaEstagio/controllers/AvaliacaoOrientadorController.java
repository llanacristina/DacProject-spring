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

import br.com.projectDac.SistemaEstagio.entities.AvaliacaoOrientador;
import br.com.projectDac.SistemaEstagio.repositories.AvaliacaoOrientadorRepository;

@RestController
@RequestMapping("/avaliacoesOrientador")
public class AvaliacaoOrientadorController {

    @Autowired
    private AvaliacaoOrientadorRepository avaliacaoOrientadorRepository;

    //CRIA AVALIACAOORIENTADOR
    @PostMapping
    public ResponseEntity<AvaliacaoOrientador> createAvaliacaoOrientador(@Validated @RequestBody AvaliacaoOrientador avaliacaoOrientador) {
        AvaliacaoOrientador newavaliacaoOrientador = avaliacaoOrientadorRepository.save(avaliacaoOrientador);
        return ResponseEntity.status(HttpStatus.CREATED).body(newavaliacaoOrientador);
    }

    //LISTA AVALIACAOORIENTADOR
      @GetMapping
        public List<AvaliacaoOrientador> getAllAvaliacaoOrientador() {
        return avaliacaoOrientadorRepository.findAll();
    }

    //ATUALIZA AVALIACAOORIENTADOR POR ID
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoOrientador> updateAvaliacaoOrientador(@PathVariable Long id, @Validated @RequestBody AvaliacaoOrientador updatedAvaliacaoOrientador) {
        return avaliacaoOrientadorRepository.findById(id)
                .map(avaliacaoOrientador -> {
                    avaliacaoOrientador.setAssiduidade(updatedAvaliacaoOrientador.getAssiduidade());
                    avaliacaoOrientador.setDisciplina(updatedAvaliacaoOrientador.getDisciplina());
                    avaliacaoOrientador.setIniciativa(updatedAvaliacaoOrientador.getIniciativa());
                    avaliacaoOrientador.setSociabilidade(updatedAvaliacaoOrientador.getSociabilidade());
                    avaliacaoOrientador.setResponsabilidade(updatedAvaliacaoOrientador.getResponsabilidade());
                    // Atualize outros campos conforme necessário
                    AvaliacaoOrientador updatedAvaliacaoOrientadorDB = avaliacaoOrientadorRepository.save(avaliacaoOrientador);
                    return ResponseEntity.ok().body(updatedAvaliacaoOrientadorDB);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETA AVALIACAOORIENTADOR POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAvaliacaoOrientador(@PathVariable Long id) {
        return avaliacaoOrientadorRepository.findById(id)
                .map(avaliacaoOrientador -> {
                    avaliacaoOrientadorRepository.delete(avaliacaoOrientador);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    //LISTA AVALIACAOORIENTADOR ESPECÍFICO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoOrientador> getAvaliacaoOrientadorById(@PathVariable Long id) {
        return avaliacaoOrientadorRepository.findById(id)
                .map(avaliacaoOrientador -> ResponseEntity.ok().body(avaliacaoOrientador))
                .orElse(ResponseEntity.notFound().build());
    }
}
