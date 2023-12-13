package br.com.projectDac.SistemaEstagio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projectDac.SistemaEstagio.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
  
    List<Aluno> findByName(String name);
}
 