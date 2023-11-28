package com.example.ReseptiLista.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ReseptiLista.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
