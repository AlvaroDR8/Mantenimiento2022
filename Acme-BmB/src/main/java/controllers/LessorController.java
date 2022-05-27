/*
 * CustomerController.java
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

import domain.Invoice;
import domain.Lessor;
import domain.Property;
import domain.Request;
import security.LoginService;
import services.InvoiceService;
import services.LessorService;
import services.PropertyService;
import services.RequestService;

@Controller
@RequestMapping("/lessor")
public class LessorController extends AbstractController {
	

	@Autowired
	private PropertyService		propertyService;
	
	@Autowired
	private LessorService		lessorService;

	@Autowired
	private RequestService		requestService;

	@Autowired
	private InvoiceService		invoiceService;
	
	// Constructors -----------------------------------------------------------

	public LessorController() {
		super();
	}

	// Property Listing ---------------------------------------------------
	
	@RequestMapping(value = "/property/list", method = RequestMethod.GET)
	public ModelAndView listProperties() {
		ModelAndView result;
		Collection<Property> properties;
		int lessor;
		
		try {
			lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();
			properties = this.propertyService.findByLessorId(lessor);
		} catch (Exception e) {
			properties = new ArrayList<Property>();
		} 
		
		result = new ModelAndView("property/list");
		result.addObject("requesrURI", "lessor/property/list.do");
		result.addObject("properties", properties);
		result.addObject("propertiesEditable", properties);

		return result;
	}
	
	// Request Listing ---------------------------------------------------
	
	@RequestMapping(value = "/request/list", method = RequestMethod.GET)
	public ModelAndView listRequest() {
		ModelAndView result;
		Collection<Request> requests, requestsInvoiced;
		Collection<Invoice> invoices;
		int lessor;
		requestsInvoiced = new ArrayList<Request>();
		
		lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();
		requests = this.requestService.findByLessorId(lessor);
		invoices = this.invoiceService.findByLessorId(lessor);
		
		for(Invoice invoice: invoices) {
			requestsInvoiced.add(invoice.getRequest());
		}
				
		result = new ModelAndView("request/list");
		result.addObject("requestURI", "lessor/request/list.do");
		result.addObject("requests", requests);
		result.addObject("requestsInvoiced", requestsInvoiced);

		return result;
	}
	
	// Invoice Listing ---------------------------------------------------
	
	@RequestMapping(value = "/invoice/list", method = RequestMethod.GET)
	public ModelAndView listInvoice() {
		ModelAndView result;
		Collection<Invoice> invoices;
		int lessor;
				
		lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();


		result = new ModelAndView("invoice/list");
		invoices = this.invoiceService.findByLessorId(lessor);
		result.addObject("invoiceURI", "lessor/invoice/list.do");
		result.addObject("invoices", invoices);
				
		return result;
	}

	// Edit Property ---------------------------------------------------------------		

	@RequestMapping(value = "/property/edit", method = RequestMethod.GET)
	public ModelAndView editProperty(@RequestParam final int propertyId) {
		ModelAndView result;
		Property property;

		property = this.propertyService.findOne(propertyId);
		Assert.notNull(property);
		result = this.createEditModelAndView(property);

		return result;
	}
	
	// Edit Invoice ---------------------------------------------------------------		

	@RequestMapping(value = "/invoice/edit", method = RequestMethod.GET)
	public ModelAndView editInvoice(@RequestParam final int requestId) {
		ModelAndView result;
		Invoice invoice;
		Request request;
		Lessor lessor;
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
		lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal());
		request = this.requestService.findOne(requestId);
		Assert.notNull(request);
		invoice = this.invoiceService.create();
		invoice.setRequest(request);
		invoice.setCreditCard(lessor.getCreditCard());
		invoice.setMomentIssued(momentD);
		
		result = new ModelAndView("invoice/edit");
		result.addObject("invoice", invoice);

		return result;
	}
	
	// Edit Request ---------------------------------------------------------------		

		@RequestMapping(value = "/request/edit", method = RequestMethod.GET)
		public ModelAndView editRequest(@RequestParam final int requestId) {
			ModelAndView result;
			Request request;

			request = this.requestService.findOne(requestId);
			Assert.notNull(request);

			result = new ModelAndView("request/edit");
			result.addObject("request", request);

			return result;
		}
	
	// Create Property ---------------------------------------------------------
	
	@RequestMapping(value = "/property/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Property property;
		String lessor;
		
		lessor = "" + this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();
		

		property = this.propertyService.create();
		result = this.createCreationModelAndView(property, lessor, null);

		return result;
	}
	
	// Save Property ---------------------------------------------------------
	
	@RequestMapping(value = "/property/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Property property, final BindingResult binding) {
		ModelAndView result;
		Collection<Property> properties, propertiesEditable;
		int lessor;
		
		this.propertyService.save(property);
		
		lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();
		propertiesEditable = this.propertyService.findByLessorId(lessor);
		
		result = new ModelAndView("property/list");
		properties = this.propertyService.findAll();
		result.addObject("requesrURI", "profile/property/list.do");
		result.addObject("properties", properties);
		result.addObject("propertiesEditable", propertiesEditable);
		
		return result;
	}
	
	// Save Request ---------------------------------------------------------
	
		@RequestMapping(value = "/request/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView requestSave(@Valid final Request request, final BindingResult binding) {
			ModelAndView result;
			Collection<Request> requests, requestsInvoiced;
			Collection<Invoice> invoices;
			int lessor;
			requestsInvoiced = new ArrayList<Request>();
			
			this.requestService.save(request);

			lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();
			requests = this.requestService.findByLessorId(lessor);
			invoices = this.invoiceService.findByLessorId(lessor);
			
			for(Invoice invoice: invoices) {
				requestsInvoiced.add(invoice.getRequest());
			}
			result = new ModelAndView("request/list");
			requests = this.requestService.findByLessorId(lessor);
			result.addObject("requesrURI", "lessor/request/list.do");
			result.addObject("requests", requests);
			result.addObject("requestsInvoiced", requestsInvoiced);
			
			return result;
		}
	
	// Save Invoice ---------------------------------------------------------
		
		@RequestMapping(value = "/invoice/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView invoiceSave(@Valid final Invoice invoice, final BindingResult binding) {
			ModelAndView result;
			Collection<Request> requests, requestsInvoiced;
			Collection<Invoice> invoices;
			int lessor;
			requestsInvoiced = new ArrayList<Request>();
				
			this.invoiceService.save(invoice);

			lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();
			requests = this.requestService.findByLessorId(lessor);
			invoices = this.invoiceService.findByLessorId(lessor);
			
			for(Invoice invoiceAux: invoices) {
				requestsInvoiced.add(invoiceAux.getRequest());
			}
			
			result = new ModelAndView("request/list");
			requests = this.requestService.findByLessorId(lessor);
			result.addObject("requesrURI", "lessor/request/list.do");
			result.addObject("requests", requests);
			result.addObject("requestsInvoiced", requestsInvoiced);
				
			return result;
		}
	
	// Delete Property ----------------------------------------------------------------
	
	@RequestMapping(value = "/property/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Property property, final BindingResult binding) {
		ModelAndView result;
		Collection<Property> properties, propertiesEditable;
		int lessor;

		try {
			this.propertyService.delete(property);
			
			lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();
			propertiesEditable = this.propertyService.findByLessorId(lessor);
			
			result = new ModelAndView("property/list");
			properties = this.propertyService.findAll();
			result.addObject("requesrURI", "profile/property/list.do");
			result.addObject("properties", properties);
			result.addObject("propertiesEditable", propertiesEditable);
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(property, "announcement.commit.error");
		}

		return result;
	}
	
	// ----------------------------------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(final Property property) {
		ModelAndView result;

		result = this.createEditModelAndView(property, null);

		return result;
	}
	
	protected ModelAndView createEditModelAndView(final Property property, final String message) {
		ModelAndView result;

		result = new ModelAndView("property/edit");
		result.addObject("property", property);

		return result;
	}
	
	protected ModelAndView createCreationModelAndView(final Property property,final String lessor, final String message) {
		ModelAndView result;

		result = new ModelAndView("property/create");
		result.addObject("property", property);
		result.addObject("lessor", lessor);

		return result;
	}
}
