package dev.paie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import dev.paie.config.ServicesConfig;
import dev.paie.repository.GradeRepository;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

@ContextConfiguration(classes = { ServicesConfig.class, DataSourceMySQLConfig.class, JpaConfig.class })
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	private GradeRepository gradeRepo;
	
	@Override
	public void initialiser() {
		// TODO Auto-generated method stub
		
	}

}
