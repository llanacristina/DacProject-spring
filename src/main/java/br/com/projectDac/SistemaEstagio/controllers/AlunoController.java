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

import br.com.projectDac.SistemaEstagio.entities.Aluno;
import br.com.projectDac.SistemaEstagio.repositories.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    //CRIA ESTUDANTE
     @PostMapping
    public ResponseEntity<Aluno> createStudent(@Validated @RequestBody Aluno aluno) {
        Aluno newStudent = alunoRepository.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
    }

    //LISTA ESTUDANTES
      @GetMapping
        public List<Aluno> getAllStudents() {
        return alunoRepository.findAll();
    }

    //ATUALIZA ESTUDANTE POR ID
     @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateStudent(@PathVariable Long id, @Validated @RequestBody Aluno updatedStudent) {
        return alunoRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setUsername(updatedStudent.getUsername());
                    student.setSenha(updatedStudent.getSenha());
                    // Atualize outros campos conforme necessário
                    Aluno updatedStudentDB = alunoRepository.save(student);
                    return ResponseEntity.ok().body(updatedStudentDB);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    //DELETA ESTUDANTE POR ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(student -> {
                    alunoRepository.delete(student);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    //LISTA ESTUDANTE ESPECÍFICO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getStudentById(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(aluno -> ResponseEntity.ok().body(aluno))
                .orElse(ResponseEntity.notFound().build());
    }


    
}
