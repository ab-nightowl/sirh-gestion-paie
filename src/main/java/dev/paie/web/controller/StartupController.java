package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import dev.paie.service.InitialiserDonneesServiceDev;

@Controller
public class StartupController {

	@Autowired
	private InitialiserDonneesServiceDev initDonneesService;
	
	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		initDonneesService.initialiser();
	}
}
