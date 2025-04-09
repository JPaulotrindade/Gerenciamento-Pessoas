package com.trabalhobe.gp.controller;

import com.trabalhobe.gp.dto.pessoaDto;
import com.trabalhobe.gp.model.pessoa;
import com.trabalhobe.gp.repository.pessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class pessoaController {

    @Autowired
    private pessoaRepository pessoaRepository;

    @PostMapping("/pessoa")
    public ResponseEntity<pessoa> createPessoa(@RequestBody pessoaDto pessoaDto){
        pessoa pessoa = new pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);

        pessoa savedPessoa = pessoaRepository.save(pessoa);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @GetMapping("/pessoa")
    public ResponseEntity<List<pessoa>> getAllPessoa(){
        List<pessoa> allPessoa = pessoaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allPessoa);
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Object> getPessoaById(@PathVariable UUID id){
        Optional<pessoa> foundPessoa = pessoaRepository.findById(id);
        if(foundPessoa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundPessoa.get());
    }

    @DeleteMapping("/pessoa/{id}")
    public ResponseEntity<String> deletePessoaById(@PathVariable UUID id){
        Optional<pessoa> foundPessoa = pessoaRepository.findById(id);
        if(foundPessoa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found!");
        }
        pessoaRepository.delete(foundPessoa.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deleted successfully!");
    }

    @PutMapping("/pessoa/{id}")
    public ResponseEntity<Object> updatePessoaById(@RequestBody com.trabalhobe.gp.dto.pessoaDto pessoaDto,
                                                    @PathVariable UUID id){
        Optional<pessoa> foundPessoa = pessoaRepository.findById(id);
        if(foundPessoa.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa not found!");
        }

        pessoa pessoa = foundPessoa.get();
        BeanUtils.copyProperties(pessoaDto, pessoa);
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.save(pessoa));
    }
}
