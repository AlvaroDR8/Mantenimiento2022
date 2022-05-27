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
	name="socialIdentities" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->
	
	
	<security:authorize access="isAuthenticated()">
		<display:column>
			<jstl:choose>
				<jstl:when test="${ mySocialIdentities.contains(row) }">
					<a href="profile/socialIdentity/edit.do?socialIdentityId=${row.id}">
						<spring:message code="socialIdentity.edit" />
					</a>						
				</jstl:when>
			</jstl:choose>
		</display:column>
	</security:authorize>
	
	
	<!-- Attributes -->
	
	<spring:message code="socialIdentity.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" sortable="true" />

	<spring:message code="socialIdentity.socialNetwork" var="socialNetworkHeader" />
	<display:column property="socialNetwork" title="${socialNetworkHeader}" sortable="true"/>

	<spring:message code="socialIdentity.profileURL" var="profileURLHeader" />
	<display:column property="profileURL" title="${profileURLHeader}" sortable="false" />
	
	<spring:message code="socialIdentity.name" var="nameHeader" />
	<display:column property="actor.name" title="${nameHeader}" sortable="false" />

	<spring:message code="socialIdentity.surname" var="surnameHeader" />
	<display:column property="actor.surname" title="${surnameHeader}" sortable="false" />

</display:table>

<!-- Action links -->

<security:authorize access="isAuthenticated()">
	<div>
		<a href="profile/socialIdentity/create.do"> <spring:message
				code="socialIdentity.create" />
		</a>
	</div>
</security:authorize>