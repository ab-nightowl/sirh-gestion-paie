package dev.paie.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilRenumerationRepository;
import dev.paie.repository.UtilisateurRepository;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired private ApplicationContext context;
	@Autowired private CotisationRepository cotisationRepo;
	@Autowired private EntrepriseRepository entrepriseRepo;
	@Autowired private GradeRepository gradeRepo;
	@Autowired private ProfilRenumerationRepository profilRepo;
	@Autowired private PeriodeRepository periodeRepo;
	@Autowired private UtilisateurRepository utilisateurRepo;
	
	@Autowired private PasswordEncoder passwordEncoder;
	
	@Override
	public void initialiser() {
		Utilisateur utilisateur1 = new Utilisateur();
		utilisateur1.setNomUtilisateur("admin");
		String mdp1 = "admin";
		String mdpHashe1 = this.passwordEncoder.encode(mdp1);
		utilisateur1.setMotDePasse(mdpHashe1);
		utilisateur1.setEstActif(true);
		utilisateur1.setRole(ROLES.ROLE_ADMINISTRATEUR);
		utilisateurRepo.save(utilisateur1);
		
		Utilisateur utilisateur2 = new Utilisateur();
		utilisateur2.setNomUtilisateur("user");
		String mdp2 = "secret";
		String mdpHashe2 = this.passwordEncoder.encode(mdp2);
		utilisateur2.setMotDePasse(mdpHashe2);
		utilisateur2.setEstActif(true);
		utilisateur2.setRole(ROLES.ROLE_UTILISATEUR);
		utilisateurRepo.save(utilisateur2);
		
		Map<String, Cotisation> beansOfCotisation = context.getBeansOfType(Cotisation.class);
		beansOfCotisation.values().stream().forEach(cot -> cotisationRepo.save(cot));
		
		Map<String, Entreprise> beansOfEntreprise = context.getBeansOfType(Entreprise.class);
		beansOfEntreprise.values().stream().forEach(ent -> entrepriseRepo.save(ent));
		
		Map<String, Grade> beansOfGrade = context.getBeansOfType(Grade.class);
		beansOfGrade.values().stream().forEach(g -> gradeRepo.save(g));
		
		Map<String, ProfilRemuneration> beansOfProfil = context.getBeansOfType(ProfilRemuneration.class);
		beansOfProfil.values().stream().forEach(p -> profilRepo.save(p));
		
		int anneeCourante = LocalDate.now().getYear();

		IntStream.range(1, 13).forEach(mois -> {
			Periode p = new Periode();
			p.setDateDebut(LocalDate.of(anneeCourante, mois, 1));
			p.setDateFin(p.getDateDebut().with(TemporalAdjusters.lastDayOfMonth()));
			periodeRepo.save(p);
		});
		
	}

}
