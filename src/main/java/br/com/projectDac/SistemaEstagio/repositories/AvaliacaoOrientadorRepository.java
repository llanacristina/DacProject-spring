package br.com.projectDac.SistemaEstagio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projectDac.SistemaEstagio.entities.Aluno;
import br.com.projectDac.SistemaEstagio.entities.AvaliacaoOrientador;

public interface AvaliacaoOrientadorRepository extends JpaRepository<AvaliacaoOrientador, Long> {
  
    List<AvaliacaoOrientador> findByAluno(Aluno aluno);
}