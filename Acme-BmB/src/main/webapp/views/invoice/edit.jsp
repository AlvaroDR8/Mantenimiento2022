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

<form:form action="lessor/invoice/edit.do" modelAttribute="invoice">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="momentIssued"/>
	<form:hidden path="creditCard" />
	<form:hidden path="request" />
	

	<form:label path="vatNumber">
		<spring:message code="invoice.vatNumber" />:
	</form:label>
	<form:input path="vatNumber" />
	<form:errors cssClass="error" path="vatNumber" />
	<br />
	
	<form:label path="details">
		<spring:message code="invoice.details" />:
	</form:label>
	<form:input path="details" />
	<form:errors cssClass="error" path="details" />
	<br />
	
	<form:label path="amount">
		<spring:message code="invoice.amount" />:
	</form:label>
	<form:input path="amount" />
	<form:errors cssClass="error" path="amount" />
	<br />
	
	<form:label path="totalAmount">
		<spring:message code="invoice.totalAmount" />:
	</form:label>
	<form:input path="totalAmount" />
	<form:errors cssClass="error" path="totalAmount" />
	<br />
	
	<%-- <form:errors cssClass="error" path="reviewer" /> --%>
	<br />

	<input type="submit" name="save"
		value="<spring:message code="invoice.save" />" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="invoice.cancel" />"
		onclick="javascript: relativeRedir('lessor/request/list.do');" />
	<br />

</form:form>