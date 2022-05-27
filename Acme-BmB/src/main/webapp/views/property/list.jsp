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
	name="properties" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	
	
	<security:authorize access="hasRole('LESSOR')">
		<display:column>
			<jstl:choose>
				<jstl:when test="${ propertiesEditable.contains(row) }">
					<a href="lessor/property/edit.do?propertyId=${row.id}">
						<spring:message code="property.edit" />
					</a>					
				</jstl:when>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
	<!-- Attributes -->
	
	<spring:message code="property.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />

	<spring:message code="property.address" var="addressHeader" />
	<display:column property="address" title="${addressHeader}" sortable="true"/>

	<spring:message code="property.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="property.rent" var="rentHeader" />
	<display:column property="rent" title="${rentHeader}" sortable="false" />

	<security:authorize access="hasRole('TENANT')">
		<display:column>
			<jstl:choose>
				<jstl:when test="${ !propertiesRequestable.contains(row) }">
					<a href="tenant/request/create.do?propertyId=${row.id}">
						<spring:message code="property.request" />
					</a>					
				</jstl:when>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('AUDITOR')">
		<display:column>
			<jstl:choose>
				<jstl:when test="${ !propertiesAuditable.contains(row) }">
					<a href="auditor/audit/create.do?propertyId=${row.id}">
						<spring:message code="property.audit" />
					</a>					
				</jstl:when>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
</display:table>

<!-- Action links -->

<security:authorize access="hasRole('LESSOR')">
	<div>
		<a href="lessor/property/create.do"> <spring:message
				code="property.create" />
		</a>
	</div>
</security:authorize>