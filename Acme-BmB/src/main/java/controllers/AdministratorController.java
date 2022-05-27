/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Attributes;
import domain.ValueAttributes;
import services.AttributesService;
import services.ValueAttributesService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AttributesService attributesService;
	@Autowired
	private ValueAttributesService valueAttributesService;
	
	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Listing Attributes ---------------------------------------------------------------		

	@RequestMapping(value = "/attributes/list", method = RequestMethod.GET)
	public ModelAndView listAttributes() {
		ModelAndView result;
		Collection<Attributes> attributes;
					
		result = new ModelAndView("attribute/list");
		attributes = this.attributesService.findAll();
		result.addObject("attributesURI", "administrator/attributes/list.do");
		result.addObject("attributes", attributes);

		return result;
	}
	
	// Listing Value Attributes ---------------------------------------------------------------		

	@RequestMapping(value = "/valueAttributes/list", method = RequestMethod.GET)
	public ModelAndView listValueAttributes() {
		ModelAndView result;
		Collection<ValueAttributes> valueAttributes;
						
		result = new ModelAndView("valueAttribute/list");
		valueAttributes = this.valueAttributesService.findAll();
		result.addObject("attributesURI", "administrator/attributes/list.do");
		result.addObject("valueAttributes", valueAttributes);

		return result;
	}
	
	// Edit Attribute ---------------------------------------------------------------		

	@RequestMapping(value = "/attributes/edit", method = RequestMethod.GET)
	public ModelAndView editAttribute(@RequestParam final int attributeId) {
		ModelAndView result;
		Attributes attribute;

		attribute = this.attributesService.findOne(attributeId);
		Assert.notNull(attribute);
			
		result = new ModelAndView("attribute/edit");
		result.addObject("attribute", attribute);

		return result;
	}
	
	// Create Attribute ---------------------------------------------------------------		

	@RequestMapping(value = "/attributes/create", method = RequestMethod.GET)
	public ModelAndView createAttribute() {
		ModelAndView result;
		Attributes attribute = this.attributesService.create();
				
		result = new ModelAndView("attribute/edit");
		result.addObject("attribute", attribute);

		return result;
	}
	
	// Save Attribute ---------------------------------------------------------
	
	@RequestMapping(value = "/attributes/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Attributes attribute, final BindingResult binding) {
		ModelAndView result;
		Collection<Attributes> attributes;
				
		this.attributesService.save(attribute);
				
		result = new ModelAndView("attribute/list");
		attributes = this.attributesService.findAll();
		result.addObject("attributeURI", "administrator/attributes/list.do");
		result.addObject("attributes", attributes);
				
		return result;
	}
	
	// Save Attribute ---------------------------------------------------------
	
	@RequestMapping(value = "/attributes/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Attributes attribute, final BindingResult binding) {
		ModelAndView result;
		Collection<Attributes> attributes;
					
		this.attributesService.delete(attribute);
					
		result = new ModelAndView("attribute/list");
		attributes = this.attributesService.findAll();
		result.addObject("attributeURI", "administrator/attributes/list.do");
		result.addObject("attributes", attributes);
					
		return result;
	}

}
