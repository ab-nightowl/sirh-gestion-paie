package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRenumerationRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired
	private EntrepriseRepository entrepriseRepo;
	@Autowired
	private GradeRepository gradeRepo;
	@Autowired
	private ProfilRenumerationRepository profilRepo;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView afficherPageCreer() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		
		List<Entreprise> listEnt = entrepriseRepo.findAll();
		mv.addObject("listEnt", listEnt);
		
		List<ProfilRemuneration> listProfils = profilRepo.findAll();
		mv.addObject("listProfils", listProfils);
		
		List<Grade> listGrades = gradeRepo.findAll();
		mv.addObject("listGrades", listGrades);
		
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		
		return mv;
	}
}
