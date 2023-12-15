package br.com.projectDac.SistemaEstagio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projectDac.SistemaEstagio.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
  
    List<Empresa> findByName(String name);
}