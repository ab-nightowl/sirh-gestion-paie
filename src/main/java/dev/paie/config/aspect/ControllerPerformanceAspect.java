package dev.paie.config.aspect;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import dev.paie.entite.Performance;
import dev.paie.repository.PerformanceRepository;

@Configuration
@Aspect
public class ControllerPerformanceAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerPerformanceAspect.class);
	
	@Autowired PerformanceRepository performanceRepo;

	@Around("execution(* dev.paie.web.controller.*.*(..))")
	public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.debug("Début d'exécution de la méthode " + pjp.getSignature().toString());
		long debut = System.currentTimeMillis();
		LocalDateTime dhDebut = LocalDateTime.ofInstant(Instant.ofEpochMilli(debut), ZoneId.systemDefault());
		
		Object resultat = pjp.proceed();
		
		long fin = System.currentTimeMillis();
		LocalDateTime dhFin = LocalDateTime.ofInstant(Instant.ofEpochMilli(fin), ZoneId.systemDefault());
		
		long tempsExecution = fin - debut;
		
		String nomService = pjp.getSignature().toString();
		
		Performance performance = new Performance(nomService, dhDebut, tempsExecution);
		
		performanceRepo.save(performance);
		
		LOGGER.debug("Fin d'exécution de la méthode");
		
		return resultat;
	}
}
