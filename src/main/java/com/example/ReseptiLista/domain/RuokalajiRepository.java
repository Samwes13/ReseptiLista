package com.example.ReseptiLista.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RuokalajiRepository extends CrudRepository<Ruokalaji, Long> {
    
	//Iterable<Resepti> findByRuokalajiId(Long ruokalajiId);;
}

