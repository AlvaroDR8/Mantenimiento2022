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

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.CreditCard;
import domain.Invoice;
import domain.Property;
import domain.Request;
import domain.Tenant;
import security.LoginService;
import services.InvoiceService;
import services.PropertyService;
import services.RequestService;
import services.TenantService;

@Controller
@RequestMapping("/tenant")
public class TenantController extends AbstractController {


	@Autowired
	private PropertyService		propertyService;
	
	@Autowired
	private TenantService		tenantService;

	@Autowired
	private RequestService		requestService;

	@Autowired
	private InvoiceService		invoiceService;
	
	// Constructors -----------------------------------------------------------

	public TenantController() {
		super();
	}

	// Request Listing ---------------------------------------------------
	
		@RequestMapping(value = "/request/list", method = RequestMethod.GET)
		public ModelAndView listRequest() {
			ModelAndView result;
			Collection<Request> requests;
			int tenant;
			
			tenant = this.tenantService.findByUserAccount(LoginService.getPrincipal()).getId();

			result = new ModelAndView("request/list");
			requests = this.requestService.findByTenantId(tenant);
			result.addObject("requestURI", "tenant/request/list.do");
			result.addObject("requests", requests);

			return result;
		}
	
	// Invoice Listing ---------------------------------------------------
		
		@RequestMapping(value = "/invoice/list", method = RequestMethod.GET)
		public ModelAndView listInvoice() {
			ModelAndView result;
			Collection<Invoice> invoices;
			int tenant;
			
			tenant = this.tenantService.findByUserAccount(LoginService.getPrincipal()).getId();


			result = new ModelAndView("invoice/list");
			invoices = this.invoiceService.findByTenantId(tenant);
			result.addObject("invoiceURI", "tenant/invoice/list.do");
			result.addObject("invoices", invoices);
			
			return result;
		}		
	
	// Create Request ---------------------------------------------------------------		

	@RequestMapping(value = "/request/create", method = RequestMethod.GET)
	public ModelAndView createRequest(@RequestParam final int propertyId) {
		ModelAndView result;
		Request request = this.requestService.create();
		Property property;
		Tenant tenant;
		CreditCard creditCard;
		
		tenant = this.tenantService.findByUserAccount(LoginService.getPrincipal());
		property = this.propertyService.findOne(propertyId);
		creditCard = tenant.getCreditCard();
		
		request.setCreditCard(creditCard);
		request.setTenant(tenant);
		request.setProperty(property);
		request.setStatus("PENDIG");

		result = new ModelAndView("request/create");
		result.addObject("request", request);

		return result;
	}
	
	// Save Request ---------------------------------------------------------
	
		@RequestMapping(value = "/request/create", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final Request request, final BindingResult binding) {
			ModelAndView result;
			Collection<Request> requests;
			Collection<Property> properties, propertiesRequestable;
			Tenant tenant;
			propertiesRequestable = new ArrayList<Property>();
			
			this.requestService.save(request);
			
			try {
				tenant = this.tenantService.findByUserAccount(LoginService.getPrincipal());
				requests = tenant.getRequests();
				
				for(Request requestAux : requests) {
					propertiesRequestable.add(requestAux.getProperty());
				}
			} catch (Exception e) {
				propertiesRequestable = new ArrayList<Property>();
			}
			
			result = new ModelAndView("property/list");
			properties = this.propertyService.findAll();
			result.addObject("requesrURI", "profile/property/list.do");
			result.addObject("properties", properties);
			result.addObject("propertiesRequestable", propertiesRequestable);
			
			return result;
		}

}
