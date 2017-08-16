package dev.paie.entite;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Performance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nomService;
	private LocalDateTime dateHeure;
	private Long tempsExecution;
	
	public Performance() {}

	public Performance(String nomService, LocalDateTime dateHeure, Long tempsExecution) {
		this.nomService = nomService;
		this.dateHeure = dateHeure;
		this.tempsExecution = tempsExecution;
	}
	
	
}
