package com.example.ReseptiLista.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Ruokalaji {
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
Long ruokalajiId;
String ruokalajike;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "ruokalaji")
@JsonIgnoreProperties("ruokalaji")
private List<Resepti> reseptit;

public Ruokalaji(String ruokalajike) {
	super();
	this.ruokalajike = ruokalajike;
}

public Ruokalaji() {
	this.ruokalajike = null;

}


public Long getRuokalajiId() {
	return ruokalajiId;
}

public void setRuokalajiId(Long ruokalajiId) {
	this.ruokalajiId = ruokalajiId;
}

public String getRuokalajike() {
	return ruokalajike;
}

public void setRuokalajike(String ruokalajike) {
	this.ruokalajike = ruokalajike;
}

public List<Resepti> getReseptit() {
	return reseptit;
}

public void setReseptit(List<Resepti> reseptit) {
	this.reseptit = reseptit;
}


}
