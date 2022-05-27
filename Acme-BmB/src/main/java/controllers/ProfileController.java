/*
 * ProfileController.java
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

import domain.Actor;
import domain.Audit;
import domain.Auditor;
import domain.Comment;
import domain.Property;
import domain.Request;
import domain.SocialIdentity;
import domain.Tenant;
import security.LoginService;
import services.ActorService;
import services.AuditService;
import services.AuditorService;
import services.CommentService;
import services.LessorService;
import services.PropertyService;
import services.SocialIdentityService;
import services.TenantService;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private LessorService lessorService;
	@Autowired
	private TenantService tenantService;
	@Autowired
	private SocialIdentityService socialIdentityService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private AuditorService auditorService;
	@Autowired
	private AuditService auditService;

	// Listing Properties ---------------------------------------------------------------		

	@RequestMapping(value = "/property/list", method = RequestMethod.GET)
	public ModelAndView listProperties() {
		ModelAndView result;
		Collection<Request> requests;
		Collection<Audit> audits;
		Collection<Property> properties, propertiesEditable, propertiesRequestable, propertiesAuditable;
		int lessor;
		Tenant tenant;
		Auditor auditor;
		propertiesRequestable = new ArrayList<Property>();
		propertiesAuditable = new ArrayList<Property>();
		
		try {
			lessor = this.lessorService.findByUserAccount(LoginService.getPrincipal()).getId();
			propertiesEditable = this.propertyService.findByLessorId(lessor);
		} catch (Exception e) {
			propertiesEditable = new ArrayList<Property>();
		}
		
		try {
			tenant = this.tenantService.findByUserAccount(LoginService.getPrincipal());
			requests = tenant.getRequests();
			
			for(Request request : requests) {
				propertiesRequestable.add(request.getProperty());
			}
		} catch (Exception e) {
			propertiesRequestable = new ArrayList<Property>();
		}
		
		try {
			auditor = this.auditorService.findByUserAccount(LoginService.getPrincipal());
			audits = auditor.getAudits();
			
			for(Audit audit : audits) {
				propertiesAuditable.add(audit.getProperty());
			}
		} catch (Exception e) {
			propertiesAuditable = new ArrayList<Property>();
		}
		
		result = new ModelAndView("property/list");
		properties = this.propertyService.findAll();
		result.addObject("requesrURI", "profile/property/list.do");
		result.addObject("properties", properties);
		result.addObject("propertiesEditable", propertiesEditable);
		result.addObject("propertiesRequestable", propertiesRequestable);
		result.addObject("propertiesAuditable", propertiesAuditable);

		return result;
	}
	
	// Listing Social Identities ---------------------------------------------------------------		

	@RequestMapping(value = "/socialIdentity/list", method = RequestMethod.GET)
	public ModelAndView listSocialIdentities() {
		ModelAndView result;
		Collection<SocialIdentity> socialIdentities, mySocialIdentities;
		Actor actor = this.actorService.findByUserAccount(LoginService.getPrincipal());
		mySocialIdentities = actor.getSocialIdentities();
			
		result = new ModelAndView("socialIdentity/list");
		socialIdentities = this.socialIdentityService.findAll();
		result.addObject("requesrURI", "profile/socialIdentity/list.do");
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("mySocialIdentities", mySocialIdentities);

		return result;
	}
	
	// Listing Comments ---------------------------------------------------------------		

	@RequestMapping(value = "/comment/list", method = RequestMethod.GET)
	public ModelAndView listComments() {
		ModelAndView result;
		Collection<Comment> comments, commentsEditable;
		Actor actor = this.actorService.findByUserAccount(LoginService.getPrincipal());
		commentsEditable = actor.getComments();
				
		result = new ModelAndView("comment/list");
		comments = this.commentService.findAll();
		result.addObject("requestURI", "profile/comment/list.do");
		result.addObject("comments", comments);
		result.addObject("commentsEditable", commentsEditable);

		return result;
	}
	
	// Listing Audits ---------------------------------------------------------------		

	@RequestMapping(value = "/audit/list", method = RequestMethod.GET)
	public ModelAndView listAudits() {
		ModelAndView result;
		Collection<Audit> audits, auditsEditable;
		
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


	// Edit Social Identity ---------------------------------------------------------------		

	@RequestMapping(value = "/socialIdentity/edit", method = RequestMethod.GET)
	public ModelAndView editSocialIdentity(@RequestParam final int socialIdentityId) {
		ModelAndView result;
		SocialIdentity socialIdentity;

		socialIdentity = this.socialIdentityService.findOne(socialIdentityId);
		socialIdentity.setActor(this.actorService.findByUserAccount(LoginService.getPrincipal()));
		Assert.notNull(socialIdentity);

		result = new ModelAndView("socialIdentity/edit");
		result.addObject("socialIdentity", socialIdentity);

		return result;
	}
	
	// Edit Comment ---------------------------------------------------------------		

	@RequestMapping(value = "/comment/edit", method = RequestMethod.GET)
	public ModelAndView editComment(@RequestParam final int commentId) {
		ModelAndView result;
		Comment comment;

		comment = this.commentService.findOne(commentId);
		Assert.notNull(comment);

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);

		return result;
	}
	
	// Create Social Identity ---------------------------------------------------------------		

	@RequestMapping(value = "/socialIdentity/create", method = RequestMethod.GET)
	public ModelAndView createSocialIdentity() {
		ModelAndView result;
		SocialIdentity socialIdentity = this.socialIdentityService.create();
		Actor actor;
			
		actor = this.actorService.findByUserAccount(LoginService.getPrincipal());
		socialIdentity.setActor(actor);

		result = new ModelAndView("socialIdentity/create");
		result.addObject("socialIdentity", socialIdentity);

		return result;
	}
	
	// Create Comment ---------------------------------------------------------------		

	@RequestMapping(value = "/comment/create", method = RequestMethod.GET)
	public ModelAndView createComment() {
		ModelAndView result;
		Comment comment = this.commentService.create();
		Actor actor;
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
				
		actor = this.actorService.findByUserAccount(LoginService.getPrincipal());
		comment.setActor(actor);
		comment.setMomentPosted(momentD);

		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);

		return result;
	}
	
	// Save Social Identity ---------------------------------------------------------
	
	@RequestMapping(value = "/socialIdentity/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SocialIdentity socialIdentity, final BindingResult binding) {
		ModelAndView result;
		Collection<SocialIdentity> socialIdentities, mySocialIdentities;
			
		this.socialIdentityService.save(socialIdentity);
			
		Actor actor = this.actorService.findByUserAccount(LoginService.getPrincipal());
		mySocialIdentities = actor.getSocialIdentities();
			
		result = new ModelAndView("socialIdentity/list");
		socialIdentities = this.socialIdentityService.findAll();
		result.addObject("requestURI", "profile/socialIdentity/list.do");
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("mySocialIdentities", mySocialIdentities);
			
		return result;
	}
	
	// Save Comment ---------------------------------------------------------
	
	@RequestMapping(value = "/comment/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveComment(@Valid final Comment comment, final BindingResult binding) {
		ModelAndView result;
		Collection<Comment> comments, commentsEditable;
				
		this.commentService.save(comment);
				
		Actor actor = this.actorService.findByUserAccount(LoginService.getPrincipal());
		commentsEditable = actor.getComments();
				
		result = new ModelAndView("comment/list");
		comments = this.commentService.findAll();
		result.addObject("requestURI", "profile/comment/list.do");
		result.addObject("comments", comments);
		result.addObject("commentsEditable", commentsEditable);
				
		return result;
	}
	
	// Delete Social Identity ---------------------------------------------------------
	
	@RequestMapping(value = "/socialIdentity/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final SocialIdentity socialIdentity, final BindingResult binding) {
		ModelAndView result;
		Collection<SocialIdentity> socialIdentities, mySocialIdentities;
				
		this.socialIdentityService.delete(socialIdentity);
				
		Actor actor = this.actorService.findByUserAccount(LoginService.getPrincipal());
		mySocialIdentities = actor.getSocialIdentities();
				
		result = new ModelAndView("socialIdentity/list");
		socialIdentities = this.socialIdentityService.findAll();
		result.addObject("requestURI", "profile/socialIdentity/list.do");
		result.addObject("socialIdentities", socialIdentities);
		result.addObject("mySocialIdentities", mySocialIdentities);
				
		return result;
	}
	
	// Delete Social Identity ---------------------------------------------------------
	
	@RequestMapping(value = "/comment/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteComment(@Valid final Comment comment, final BindingResult binding) {
		ModelAndView result;
		Collection<Comment> comments, commentsEditable;
					
		this.commentService.delete(comment);
					
		Actor actor = this.actorService.findByUserAccount(LoginService.getPrincipal());
		commentsEditable = actor.getComments();
					
		result = new ModelAndView("comment/list");
		comments = this.commentService.findAll();
		result.addObject("requestURI", "profile/comment/list.do");
		result.addObject("comments", comments);
		result.addObject("commentsEditable", commentsEditable);
					
		return result;
	}

}
