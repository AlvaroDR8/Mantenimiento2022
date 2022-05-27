<%--
 * edit.jsp
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

<form:form action="lessor/property/edit.do" modelAttribute="property">

	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="name">
		<spring:message code="property.name" />:
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	<br />

	<form:label path="address">
		<spring:message code="property.address" />:
	</form:label>
	<form:input path="address" />
	<form:errors cssClass="error" path="address" />
	<br />

	<form:label path="description">
		<spring:message code="property.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="rent">
		<spring:message code="property.rent" />:
	</form:label>
	<form:input path="rent" />
	<form:errors cssClass="error" path="rent" />
	<br />
	
	<form:hidden path="lessor" value="${ lessor }"/>
	<br />
	
	<%-- <form:errors cssClass="error" path="reviewer" /> --%>
	<br />

	<input type="submit" name="save"
		value="<spring:message code="property.save" />" />&nbsp; 
	<jstl:if test="${property.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="property.delete" />"
			onclick="return confirm('<spring:message code="property.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="property.cancel" />"
		onclick="javascript: relativeRedir('profile/property/list.do');" />
	<br />

</form:form>