package com.desafiolatam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String registrar(@ModelAttribute("contact") Contact contact, Model model,
			RedirectAttributes redirectAttributes) {
		// validar
		if (contact.getNombre() != null && !contact.getNombre().equals("") &&
			contact.getApellidoPaterno() != null && !contact.getApellidoPaterno().equals("") &&
			contact.getApellidoMaterno() != null && !contact.getApellidoMaterno().equals("") &&
			contact.getDireccion() != null && !contact.getDireccion().equals("") &&
			contact.getTelefono() != null && !contact.getTelefono().equals("")
			) {
			// persistencia
			contactService.save(contact);

			// pasar mensaje
			redirectAttributes.addFlashAttribute("msgOk", "Contacto creado correctamente");
			return "redirect:/";

		} else {
			model.addAttribute("listContact", contactService.findAll());
			// retornar mensaje de error
			redirectAttributes.addFlashAttribute("msgError", "Faltan datos, por favor, reinténtalo");
			// Redirigir
			return "views/contactManager.jsp";
		}

		
	}

	// método eliminar con captura dinámina
	// http://localhost:9080/contactManager/eliminar/${id}
	@RequestMapping("/eliminar/{id}")
	public String eliminarContact(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		contactService.deleteById(id);
		redirectAttributes.addFlashAttribute("msgOk", "Contacto eliminado correctamente");
		return "redirect:/";
	}
	
}

/*
1. Crear proyecto con ​https://start.spring.io/ maven y Spring Boot 2.1.6, considerando
los siguientes datos del proyecto:
- groupId: cl.desafiolatam. --> groupIs: com.desafiolatam
- artifactId: contactmanager. --> artifactId: contactManager
- packaging: war. --> packaging: war
- Utilizar Java 8.  --> Java 8
 
2. Crear la estructura del proyecto, clase controller, vista y archivo web.xml de la
siguiente manera:
Crear Packages Controller - cl.desafiolatam.controller --> Lo hice en com.desafiolatam.controller
Crear clase controller - ContactController.java --> Realizado
Crear archivo web.xml, configurando el distpatcher de Spring --> No lo hice
Crear método getContactList y devolver la palabra Hola Mundo!! (Esto es,
solo para comprobar que el proyecto fue creado correctamente) --> No lo hice
Crear la Vista contactManager.jsp bajo la carpeta WEB-INF/views del
proyecto --> Realizado
Configurar la vista en el archivo application.properties --> Realizado
Probar en la url ​http://localhost:8080/contactManager/​ --> Realizado en la url ​http://localhost:9080/contactManager.

3. Crear la interfaz como se muestra en Requerimientos funcionales. Una vez creada la
interfaz, programar el método getContactList para que muestre una lista de datos
tipo Contacto.
a. El método debe ser de tipo GET.
b. Este método actuará como inicio de la aplicación, por lo que para acceder a él se
debe hacer mediante la siguiente URL:​ http://localhost:8080/contactManager/​. --> URL:​ http://localhost:9080/contactManager​
c. ​La lista, debe ser una Lista de Contactos, por lo que se debe desarrollar la clase
Contacto.java en base a los atributos del contacto. --> La clase se llama Contact.java
List `​ < ​ Contacto​ > ​ ` listaContacto. --> Paso una list<Contact> listContact
Podemos tener un solo contacto el cual puede ser reemplazado y eliminado. --> Realicé una persistencia a BD y
 el programa guarda y elimina los datos de los contactos


4. Programar los siguientes métodos restantes en el controlado:
addContact: Método encargado de agregar un contacto. Este método debe ser de
tipo POST y debe ser accedido mediante la siguiente URL:
http://localhost:8082/contactManager/addContact. --> Con el método POST en http://localhost:9080/contactManager
a. Utilizar @ModelAttribute. --> Utilizo @ModelAttribute
b. Recibir desde la Vista, el objeto contact. Utilizar ​ < ​ form:form​ />​ de Spring MVC. --> Hecho
c. ​El método, debe crear un id para cada Ítem de la lista. --> El objeto contact tiene id y fecha de creación 
deleteContact: Método encargado de eliminar un contacto. Este método debe ser de
tipo POST y debe ser accedido mediante la siguiente URL:
http://localhost:8082/contactManager/deleteContact/?id= . --> El método eliminarContact se accede por 
http://localhost:9080/contactManager/eliminar/${id}​

 */
