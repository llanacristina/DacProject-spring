package br.com.projectDac.SistemaEstagio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projectDac.SistemaEstagio.entities.Estagio;

public interface EstagioRepository extends JpaRepository<Estagio, Long> {
  
    List<Estagio> findByName(String name);
}