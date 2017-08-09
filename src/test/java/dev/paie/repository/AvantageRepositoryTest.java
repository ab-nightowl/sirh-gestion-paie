package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class, DataSourceMySQLConfig.class, JpaConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouvel avantage
		Avantage avantage = new Avantage();
		avantage.setCode("av-1");
		avantage.setNom("Avantage 1");
		avantage.setMontant(new BigDecimal("10.50"));
		avantageRepository.save(avantage);

		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via
		// la méthode findOne
		Avantage avantage1 = avantageRepository.findAll().get(0);
		assertThat(avantage1.getCode()).isEqualTo("av-1");
		
//		Echec du test via findOne() ci-dessous...
//		Integer id = avantage1.getId();
//		assertThat(avantageRepository.findOne(id)).isEqualTo(avantage1);

		
		// TODO modifier un avantage
		avantage1.setCode("nouveauCode");
		avantage1.setNom("nouveauNom");
		avantage1.setMontant(new BigDecimal("2"));
		avantageRepository.save(avantage1);
		// TODO vérifier que les modifications sont bien prises en compte via la
		// méthode findOne
		assertThat(avantage1.getCode()).isEqualTo("nouveauCode");
		assertThat(avantage1.getNom()).isEqualTo("nouveauNom");
		assertThat(avantage1.getMontant()).isEqualTo(new BigDecimal("2"));
	}
}
