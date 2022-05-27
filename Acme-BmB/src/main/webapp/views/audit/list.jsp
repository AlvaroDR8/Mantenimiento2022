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
	name="audits" requestURI="${auditURI}" id="row">
	
	<!-- Action links -->
	
	<security:authorize access="hasRole('AUDITOR')">
		<display:column>
			<jstl:choose>
				<jstl:when test="${ auditsEditable.contains(row) }">
					<a href="auditor/audit/edit.do?auditId=${row.id}">
						<spring:message code="audit.edit" />
					</a>						
				</jstl:when>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
	<!-- Attributes -->
	
	<spring:message code="audit.doneMoment" var="doneMomentHeader" />
	<display:column property="doneMoment" title="${doneMomentHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

	<spring:message code="audit.AttachmentNumber" var="AttachmentNumberHeader" />
	<display:column property="attachmentNumber" title="${AttachmentNumberHeader}" />

	<spring:message code="audit.finalVersion" var="finalVersionHeader" />
	<display:column property="finalVersion" title="${finalVersionHeader}"  />
	
	<spring:message code="audit.initialText" var="initialTextHeader" />
	<display:column property="initialText" title="${initialTextHeader}"  />

	<spring:message code="audit.address" var="addressHeader" />
	<display:column property="property.address" title="${addressHeader}" sortable="false" />
	
	<spring:message code="audit.name" var="nameHeader" />
	<display:column property="auditor.name" title="${nameHeader}" sortable="false" />
	
</display:table>

<!-- Action links -->