package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.ProfilRemuneration;

public interface ProfilRenumerationRepository extends JpaRepository<ProfilRemuneration, Integer> {

	ProfilRemuneration findById(Integer id);

}
