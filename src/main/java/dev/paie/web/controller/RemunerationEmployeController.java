package dev.paie.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRenumerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired
	private EntrepriseRepository entrepriseRepo;
	@Autowired
	private GradeRepository gradeRepo;
	@Autowired
	private ProfilRenumerationRepository profilRepo;
	@Autowired
	private RemunerationEmployeRepository employeRepo;
	
	
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
	public ModelAndView creerEmploye(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String matricule = req.getParameter("matricule");
		String entrepriseId = req.getParameter("entreprise");
		String profilRemunerationId = req.getParameter("profil");
		String gradeId = req.getParameter("grade");
		
		Entreprise entreprise = entrepriseRepo.findById(Integer.parseInt(entrepriseId));
		ProfilRemuneration profilRemuneration = profilRepo.findById(Integer.parseInt(profilRemunerationId));
		Grade grade = gradeRepo.findById(Integer.parseInt(gradeId));

		resp.setContentType("text/html");
		
		List<String> paramsNull = Stream.of("matricule", "entreprise", "profil", "grade")
				.filter(p -> req.getParameter(p) == null).collect(Collectors.toList());
		
		if (paramsNull.isEmpty()) {
			resp.setStatus(201);
			resp.getWriter().write("Création d’un employé avec les informations suivantes : " 
					+ "matricule=" + matricule 
					+ ", entreprise=" + entreprise
					+ ", profil=" + profilRemuneration
					+ ", grade=" + grade
					);
		} else {
			resp.setStatus(400);
			resp.getWriter().write(
					"Les paramètres suivants sont incorrects : " + String.join(", ", paramsNull));
		}
		
		employeRepo.save(new RemunerationEmploye(matricule, entreprise, profilRemuneration, grade));
		
		return afficherPageCreer();
	}
}
