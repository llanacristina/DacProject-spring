package br.com.projectDac.SistemaEstagio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projectDac.SistemaEstagio.entities.Aluno;
import br.com.projectDac.SistemaEstagio.entities.AvaliacaoEmpresa;

public interface AvaliacaoEmpresaRepository extends JpaRepository<AvaliacaoEmpresa, Long> {
  
    List<AvaliacaoEmpresa> findByAluno(Aluno aluno);
}