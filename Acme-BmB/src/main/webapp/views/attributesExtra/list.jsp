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
	name="attributes" requestURI="${attributeURI}" id="row">
	
	<!-- Action links -->
	
	<security:authorize access="hasRole('ADMIN')">
		<display:column>
			<a href="administrator/attributes/edit.do?attributeId=${row.id}">
				<spring:message code="attribute.edit" />
			</a>		
		</display:column>
	</security:authorize>
	
	<!-- Attributes -->
	
	<spring:message code="attribute.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" />

	
</display:table>

<!-- Action links -->
<security:authorize access="hasRole('ADMIN')">
	<div>
		<a href="administrator/attributes/create.do"> <spring:message
				code="attribute.create" />
		</a>
	</div>
</security:authorize>