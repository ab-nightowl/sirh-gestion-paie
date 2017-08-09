package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { ServicesConfig.class, DataSourceMySQLConfig.class, JpaConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	
	@Autowired
	private CotisationService cotisationService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder une nouvelle cotisation
		Cotisation cotisation = new Cotisation();
		cotisation.setCode("cot1");
		cotisation.setLibelle("ma cotisation");
		cotisation.setTauxSalarial(new BigDecimal("0.005"));
		cotisation.setTauxPatronal(new BigDecimal("0.003"));
		cotisationService.sauvegarder(cotisation);
		assertThat(cotisation.getCode()).isEqualTo("cot1");
		
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
		// via la méthode lister
		List<Cotisation> list = cotisationService.lister();
		Cotisation cotisation2 = list.get(list.size() - 1);
		assertThat(cotisation2.getCode()).isEqualTo("cot1");
		
		// TODO modifier une cotisation
		cotisation2.setLibelle("cotisation 2");
		cotisation2.setTauxSalarial(new BigDecimal("0.004"));
		cotisation2.setTauxPatronal(new BigDecimal("0.004"));
		cotisationService.mettreAJour(cotisation2);
		
		// TODO vérifier que les modifications sont bien prises en compte via la
		// méthode lister
		List<Cotisation> list2 = cotisationService.lister();
		Cotisation cotisation3 = list2.get(list2.size() - 1);
		assertThat(cotisation3.getLibelle()).isEqualTo("cotisation 2");
		assertThat(cotisation3.getTauxSalarial()).isEqualTo("0.004");
		assertThat(cotisation3.getTauxPatronal()).isEqualTo("0.004");
		
	}
}