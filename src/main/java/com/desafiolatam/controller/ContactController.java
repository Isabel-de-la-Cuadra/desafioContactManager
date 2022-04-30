package com.desafiolatam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafiolatam.models.Contact;
import com.desafiolatam.services.ContactService;

@Controller
@RequestMapping("/contactManager")
public class ContactController {

	@Autowired
	ContactService contactService;

	@RequestMapping("") // http://localhost:9080/contactManager
	public String showContactManager(@ModelAttribute("contact") Contact contact, 
			Model model) {
				model.addAttribute("listContact", contactService.findAll());
		return "views/contactManager.jsp";
	}

	//capturar los datos del jsp
	@PostMapping("") // http://localhost:9080/contactManager
	public String registrar(@Valid @ModelAttribute("contact") Contact contact, BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {
		// validar
		if (!result.hasErrors()) {
			// persistencia
			contactService.save(contact);

			// pasar mensaje
			redirectAttributes.addFlashAttribute("msgOk", "Contacto creado correctamente");
			return "redirect:/contactManager";

		} else {
			model.addAttribute("listContact", contactService.findAll());
			// retornar mensaje de error
			redirectAttributes.addFlashAttribute("msgError", "Faltan datos, por favor, reinténtalo");
			// Redirigir
			return "views/contactManager.jsp";
		}

	}

	// método eliminar con captura dinámina
	// http://localhost:9080/contactManager/deleteContact/${id}
	@RequestMapping("/deleteContact/{id}")
	public String deleteContact(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		contactService.deleteById(id);
		redirectAttributes.addFlashAttribute("msgOk", "Contacto eliminado correctamente");
		return "redirect:/contactManager";
	}

}