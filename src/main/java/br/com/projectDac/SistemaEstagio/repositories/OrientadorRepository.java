package br.com.projectDac.SistemaEstagio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projectDac.SistemaEstagio.entities.Orientador;

public interface OrientadorRepository extends JpaRepository<Orientador, Long> {
  
    List<Orientador> findByName(String name);
}