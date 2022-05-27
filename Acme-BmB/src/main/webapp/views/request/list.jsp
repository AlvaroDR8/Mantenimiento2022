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
	name="requests" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	
	
	<security:authorize access="hasRole('LESSOR')">
		<display:column>
			<a href="lessor/request/edit.do?requestId=${row.id}">
				<spring:message code="request.edit" />
			</a>
		</display:column>
	</security:authorize>
	
	
	<!-- Attributes -->
	
	<spring:message code="request.checkIn" var="checkInHeader" />
	<display:column property="checkIn" title="${checkInHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="request.checkOut" var="checkOutHeader" />
	<display:column property="checkOut" title="${checkOutHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="request.smoker" var="smokerHeader" />
	<display:column property="smoker" title="${smokerHeader}" sortable="true"/>

	<spring:message code="request.status" var="statusHeader" />
	<display:column property="status" title="${statusHeader}" sortable="true" />
	
	<spring:message code="request.lessorFee" var="lessorFeeHeader" />
	<display:column property="lessorFee" title="${lessorFeeHeader}" sortable="false" />

	<spring:message code="request.propertyAddress" var="propertyAddressHeader" />
	<display:column property="property.address" title="${propertyAddressHeader}" sortable="false" />

	<security:authorize access="hasRole('LESSOR')">
		<spring:message code="request.tenantName" var="tenantNameHeader" />
		<display:column property="tenant.name" title="${tenantNameHeader}" sortable="false" />
	</security:authorize>
	
	<security:authorize access="hasRole('TENANT')">
		<spring:message code="request.lessorName" var="lessorNameHeader" />
		<display:column property="property.lessor.name" title="${lessorNameHeader}" sortable="false" />
	</security:authorize>
	
	<security:authorize access="hasRole('LESSOR')">
		<display:column>
			<jstl:choose>
				<jstl:when test="${ row.status == 'ACCEPTED' && !requestsInvoiced.contains(row) }">
					<a href="lessor/invoice/edit.do?requestId=${row.id}">
						<spring:message code="request.invoice" />
					</a>					
				</jstl:when>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
</display:table>

<!-- Action links -->