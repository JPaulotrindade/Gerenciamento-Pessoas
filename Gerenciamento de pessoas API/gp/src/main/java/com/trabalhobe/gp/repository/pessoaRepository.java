package com.trabalhobe.gp.repository;

import com.trabalhobe.gp.model.pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface pessoaRepository extends JpaRepository <pessoa, UUID> {

}
