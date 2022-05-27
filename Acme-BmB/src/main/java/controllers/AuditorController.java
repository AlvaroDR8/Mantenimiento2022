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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Audit;
import domain.Auditor;
import domain.Property;
import security.LoginService;
import services.AuditService;
import services.AuditorService;
import services.PropertyService;

@Controller
@RequestMapping("/auditor")
public class AuditorController extends AbstractController {

	@Autowired
	private AuditService		auditService;
	@Autowired
	private AuditorService		auditorService;
	@Autowired
	private PropertyService		propertyService;
	
	// Constructors -----------------------------------------------------------

	public AuditorController() {
		super();
	}

	// Edit Audit ---------------------------------------------------------------		

	@RequestMapping(value = "/audit/edit", method = RequestMethod.GET)
	public ModelAndView editAudit(@RequestParam final int auditId) {
		ModelAndView result;
		Audit audit;

		audit = this.auditService.findOne(auditId);
		Assert.notNull(audit);
		
		result = new ModelAndView("audit/edit");
		result.addObject("audit", audit);

		return result;
	}
	
	// Create Audit ---------------------------------------------------------------		

	@RequestMapping(value = "/audit/create", method = RequestMethod.GET)
	public ModelAndView createAudit(@RequestParam final int propertyId) {
		ModelAndView result;
		Audit audit = this.auditService.create();
		Property property;
		Auditor auditor;
		SimpleDateFormat formatter;
		String moment;
		Date momentD;
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		try {
			momentD = formatter.parse(moment);
		} catch(Exception e) {
			momentD = new Date();
		}

		property = this.propertyService.findOne(propertyId);
		auditor = this.auditorService.findByUserAccount(LoginService.getPrincipal());
		audit.setAuditor(auditor);
		audit.setProperty(property);
		audit.setDoneMoment(momentD);
			
		result = new ModelAndView("audit/create");
		result.addObject("audit", audit);

		return result;
	}
	
	// Save Audit ---------------------------------------------------------
	
	@RequestMapping(value = "/audit/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Audit audit, final BindingResult binding) {
		ModelAndView result;
		Collection<Audit> audits;
		Collection<Property> properties, propertiesAuditable;
		Auditor auditor;
		propertiesAuditable = new ArrayList<Property>();
				
		this.auditService.save(audit);
				
		try {
			auditor = this.auditorService.findByUserAccount(LoginService.getPrincipal());
			audits = auditor.getAudits();
			
			for(Audit auditAux : audits) {
				propertiesAuditable.add(auditAux.getProperty());
			}
		} catch (Exception e) {
			propertiesAuditable = new ArrayList<Property>();
		}
		
		result = new ModelAndView("property/list");
		properties = this.propertyService.findAll();
		result.addObject("propertyURI", "profile/property/list.do");
		result.addObject("properties", properties);
		result.addObject("propertiesAuditable", propertiesAuditable);
				
		return result;
	}
	
	// Delete Audit ---------------------------------------------------------
	
		@RequestMapping(value = "/audit/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(@Valid final Audit audit, final BindingResult binding) {
			ModelAndView result;
			Collection<Audit> audits, auditsEditable;
					
			this.auditService.delete(audit);
			
			try {
				Auditor auditor = this.auditorService.findByUserAccount(LoginService.getPrincipal());
				auditsEditable = auditor.getAudits();
			} catch(Exception e) {
				auditsEditable = new ArrayList<Audit>();
			}
					
			result = new ModelAndView("audit/list");
			audits = this.auditService.findAll();
			result.addObject("auditURI", "profile/audit/list.do");
			result.addObject("audits", audits);
			result.addObject("auditsEditable", auditsEditable);
					
			return result;
		}

}
