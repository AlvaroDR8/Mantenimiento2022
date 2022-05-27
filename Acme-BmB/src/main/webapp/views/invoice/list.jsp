<%--
 * list.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="invoices" requestURI="${invoiceURI}" id="row">
	
	<!-- Action links -->
	
	
	
	<!-- Attributes -->
	
	<spring:message code="invoice.momentIssued" var="momentIssuedHeader" />
	<display:column property="momentIssued" title="${momentIssuedHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="invoice.vatNumber" var="vatNumberHeader" />
	<display:column property="vatNumber" title="${vatNumberHeader}" />

	<spring:message code="invoice.details" var="detailsHeader" />
	<display:column property="details" title="${detailsHeader}"  />
	
	<spring:message code="invoice.amount" var="amountHeader" />
	<display:column property="amount" title="${amountHeader}"  />

	<spring:message code="invoice.totalAmount" var="totalAmountHeader" />
	<display:column property="totalAmount" title="${totalAmountHeader}" sortable="false" />
	
	<spring:message code="invoice.address" var="addressHeader" />
	<display:column property="request.property.address" title="${addressHeader}" sortable="false" />
	
</display:table>

<!-- Action links -->