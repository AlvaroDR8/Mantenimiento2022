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

<form:form action="lessor/request/edit.do" modelAttribute="request">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="checkIn" />
	<form:hidden path="checkOut" />
	<form:hidden path="smoker" />
	<form:hidden path="creditCard" />
	<form:hidden path="lessorFee" />
	<form:hidden path="tenant" />
	<form:hidden path="invoice" />
	<form:hidden path="property" />
	

	<form:label path="status">
		<spring:message code="request.status" />:
	</form:label>
	<form:select id="status" path="status">
		<form:option value="PENDIG" label="PENDIG" />
		<form:option value="ACCEPTED" label="ACCEPTED" />
		<form:option value="DENIED" label="DENIED" />
	</form:select>
	<form:errors cssClass="error" path="status" />
	<br />
	
	<%-- <form:errors cssClass="error" path="reviewer" /> --%>
	<br />

	<input type="submit" name="save"
		value="<spring:message code="request.save" />" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="request.cancel" />"
		onclick="javascript: relativeRedir('lessor/request/list.do');" />
	<br />

</form:form>