package com.example.ReseptiLista.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ReseptiRepository extends CrudRepository<Resepti, Long> {
    List<Resepti> findByTitle(String title);
    List<Resepti> findByreseptiText(String reseptiText);
    Iterable<Resepti> findByRuokalajiRuokalajiId(Long ruokalajiId);
}
