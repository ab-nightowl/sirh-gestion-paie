package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		Grade grade = bulletin.getRemunerationEmploye().getGrade();
		
		BigDecimal salaireBaseBigD = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		String salaireBaseStr = paieUtils.formaterBigDecimal(salaireBaseBigD);
		BigDecimal salaireBaseArrondi = new BigDecimal(salaireBaseStr);
		
		BigDecimal salaireBrut = salaireBaseArrondi.add(bulletin.getPrimeExceptionnelle());
		String salaireBrutStr = paieUtils.formaterBigDecimal(salaireBrut);
		BigDecimal salaireBrutArrondi = new BigDecimal(salaireBrutStr);
		
		List<Cotisation> cotisationsNonImposables = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		Optional<BigDecimal> totalRetenueSalariale = cotisationsNonImposables.stream()
				.filter(cot -> cot.getTauxSalarial() != null)
				.map(cot -> cot.getTauxSalarial().multiply(salaireBrutArrondi))
				.reduce((big1, big2) -> big1.add(big2));
		
		String totalRetenueSalarialeStr = this.paieUtils.formaterBigDecimal(totalRetenueSalariale.get());
		BigDecimal totalRetenueSalarialeArr = new BigDecimal(totalRetenueSalarialeStr);
		
		Optional<BigDecimal> totalCotisationsPatronales = cotisationsNonImposables.stream()
				.filter(cot -> cot.getTauxPatronal() != null)
				.map(cot -> cot.getTauxPatronal().multiply(salaireBrutArrondi))
				.reduce((big1, big2) -> big1.add(big2));
		
		String totalCotisationsPatronalesStr = this.paieUtils.formaterBigDecimal(totalCotisationsPatronales.get());
		
		BigDecimal netImposable = salaireBrutArrondi.subtract(totalRetenueSalarialeArr);
		String netImposableStr = paieUtils.formaterBigDecimal(netImposable);
		BigDecimal netImposableArr = new BigDecimal(netImposableStr);
		
		List<Cotisation> cotisationsImposables = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsImposables();
		Optional<BigDecimal> totalRetenueImposable = cotisationsImposables.stream()
				.filter(cot -> cot.getTauxSalarial() != null)
				.map(cot -> cot.getTauxSalarial().multiply(salaireBrutArrondi))
				.reduce((big1, big2) -> big1.add(big2));
		
		
		BigDecimal netAPayer = netImposableArr.subtract(totalRetenueImposable.get());
		String netAPayerStr = paieUtils.formaterBigDecimal(netAPayer);
		
		ResultatCalculRemuneration resultatCalculRemuneration = new ResultatCalculRemuneration();
		resultatCalculRemuneration.setSalaireDeBase(salaireBaseStr);
		resultatCalculRemuneration.setSalaireBrut(salaireBrutStr);
		resultatCalculRemuneration.setTotalRetenueSalarial(totalRetenueSalarialeStr);
		resultatCalculRemuneration.setTotalCotisationsPatronales(totalCotisationsPatronalesStr);
		resultatCalculRemuneration.setNetImposable(netImposableStr);
		resultatCalculRemuneration.setNetAPayer(netAPayerStr);
		
		return resultatCalculRemuneration;
	}

}
