package dev.paie.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import dev.paie.service.InitialiserDonneesServiceDev;

@Controller
public class StartupController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StartupController.class);

	@Autowired
	private InitialiserDonneesServiceDev initDonneesService;

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		// Log de niveau INFO
		LOGGER.info("Initialisation des données");
		initDonneesService.initialiser();
	}
}
