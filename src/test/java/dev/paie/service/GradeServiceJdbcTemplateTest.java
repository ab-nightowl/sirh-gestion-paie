package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { GradeServiceJdbcTemplate.class, DataSourceMySQLConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {

	@Autowired
	private GradeService gradeService;
	
	@Before
	public void setUp() {
		gradeService.effacerDonnees();
	}
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		Grade grade = new Grade();
		grade.setCode("my-grad");
		grade.setNbHeuresBase(new BigDecimal("151.67"));
		grade.setTauxBase(new BigDecimal("11.0984"));
		
		// TODO sauvegarder un nouveau grade
		gradeService.sauvegarder(grade);
		assertThat(grade.getCode()).isEqualTo("my-grad");
		
		// TODO vérifier qu'il est possible de récupérer le nouveau grade via la
		// méthode lister
		List<Grade> list = gradeService.lister();
		Grade grade2 = list.get(0);
		assertThat(list.contains(grade2)).isTrue();
		
		// TODO modifier un grade
		grade2.setTauxBase(new BigDecimal("11.10"));
		gradeService.mettreAJour(grade2);
		
		// TODO vérifier que les modifications sont bien prises en compte via la
		// méthode lister
		Grade grade3 = gradeService.lister().get(0);
		assertThat(grade3.getTauxBase()).isEqualTo("11.1");
		
	}
}
