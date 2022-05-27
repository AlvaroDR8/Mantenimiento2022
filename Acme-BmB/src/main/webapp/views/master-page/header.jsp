<%--
 * header.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a href="#"><img src="images/logo.png" alt="Acme-BmB Co., Inc." /></a>
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/attributes/list.do"><spring:message code="master.page.administrator.attributes" /></a></li>
					<li><a href="administrator/valueAttributes/list.do"><spring:message code="master.page.administrator.valueAttributes" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('LESSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.request" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="lessor/request/list.do"><spring:message code="master.page.lessor.request" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.invoice" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="lessor/invoice/list.do"><spring:message code="master.page.lessor.myInvoices" /></a></li>				
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('TENANT')">
			<li><a class="fNiv"><spring:message	code="master.page.request" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="tenant/request/list.do"><spring:message code="master.page.tenant.myRequests" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.invoice" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="tenant/invoice/list.do"><spring:message code="master.page.tenant.myInvoices" /></a></li>					
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li><a class="fNiv"><spring:message	code="master.page.audit" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/audit/list.do"><spring:message code="master.page.audit.audits" /></a></li>				
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.property" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/property/list.do"><spring:message code="master.page.property.properties" /></a></li>
					<security:authorize access="hasRole('LESSOR')">
						<li><a href="lessor/property/list.do"><spring:message code="master.page.lessor.myProperties" /></a></li>	
					</security:authorize>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.comment" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/comment/list.do"><spring:message code="master.page.profile.comments" /></a></li>			
				</ul>
			</li>
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="profile/socialIdentity/list.do"><spring:message code="master.page.profile.socialIdentities" /></a></li>	
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

