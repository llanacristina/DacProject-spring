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

import br.com.projectDac.SistemaEstagio.entities.AvaliacaoEmpresa;
import br.com.projectDac.SistemaEstagio.repositories.AvaliacaoEmpresaRepository;

@RestController
@RequestMapping("/avaliacoesEmpresa")
public class AvaliacaoEmpresaController {

    @Autowired
    private AvaliacaoEmpresaRepository avaliacaoEmpresaRepository;

    //CRIA AVALIACAOEMPRESA
    @PostMapping
    public ResponseEntity<AvaliacaoEmpresa> createAvaliacaoEmpresa(@Validated @RequestBody AvaliacaoEmpresa avaliacaoEmpresa) {
        AvaliacaoEmpresa newavaliacaoEmpresa = avaliacaoEmpresaRepository.save(avaliacaoEmpresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(newavaliacaoEmpresa);
    }

    //LISTA AVALIACAOEMPRESA
      @GetMapping
        public List<AvaliacaoEmpresa> getAllAvaliacaoEmpresa() {
        return avaliacaoEmpresaRepository.findAll();
    }

    //ATUALIZA AVALIACAOEMPRESA POR ID
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoEmpresa> updateAvaliacaoEmpresa(@PathVariable Long id, @Validated @RequestBody AvaliacaoEmpresa updatedAvaliacaoEmpresa) {
        return avaliacaoEmpresaRepository.findById(id)
                .map(avaliacaoEmpresa -> {
                    avaliacaoEmpresa.setAprendizagem(updatedAvaliacaoEmpresa.getAprendizagem());
                    avaliacaoEmpresa.setConhecimentos(updatedAvaliacaoEmpresa.getConhecimentos());
                    avaliacaoEmpresa.setCumprimento(updatedAvaliacaoEmpresa.getCumprimento());
                    avaliacaoEmpresa.setDesempenho(updatedAvaliacaoEmpresa.getDesempenho());
                    avaliacaoEmpresa.setRendimento(updatedAvaliacaoEmpresa.getRendimento());
                    // Atualize outros campos conforme necessário
                    AvaliacaoEmpresa updatedAvaliacaoOrientadorDB = avaliacaoEmpresaRepository.save(avaliacaoEmpresa);
                    return ResponseEntity.ok().body(updatedAvaliacaoOrientadorDB);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETA AVALIACAOEMPRESA POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAvaliacaoEmpresa(@PathVariable Long id) {
        return avaliacaoEmpresaRepository.findById(id)
                .map(avaliacaoEmpresa -> {
                    avaliacaoEmpresaRepository.delete(avaliacaoEmpresa);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    //LISTA AVALIACAOEMPRESA ESPECÍFICO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoEmpresa> getAvaliacaoEmpresaById(@PathVariable Long id) {
        return avaliacaoEmpresaRepository.findById(id)
                .map(avaliacaoEmpresa -> ResponseEntity.ok().body(avaliacaoEmpresa))
                .orElse(ResponseEntity.notFound().build());
    }
}
