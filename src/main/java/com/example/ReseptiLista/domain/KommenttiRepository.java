package com.example.ReseptiLista.domain;

import org.springframework.data.repository.CrudRepository;

public interface KommenttiRepository extends CrudRepository<Kommentti, Long> {
	 Iterable<Kommentti> findByReseptiReseptiId(Long reseptiId);

}

