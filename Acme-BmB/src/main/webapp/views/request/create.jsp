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

<form:form action="tenant/request/create.do" modelAttribute="request">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="creditCard" />
	<form:hidden path="tenant" />
	<form:hidden path="invoice" />
	<form:hidden path="property" />
	<form:hidden path="status" />
	
	<form:label path="checkIn">
		<spring:message code="request.checkIn" />:
	</form:label>
	<form:input path="checkIn" placeholder="dd/MM/yyyy hh:mm"/>
	<form:errors cssClass="error" path="checkIn" />
	<br />
	
	<form:label path="checkOut">
		<spring:message code="request.checkOut" />:
	</form:label>
	<form:input path="checkOut" placeholder="dd/MM/yyyy hh:mm"/>
	<form:errors cssClass="error" path="checkOut" />
	<br />
	
	<form:label path="smoker">
		<spring:message code="request.smoker" />:
	</form:label>
	<form:select id="smoker" path="smoker">
		<form:option value="true">
			<spring:message code="request.smoker.yes" />
		</form:option>
		<form:option value="false">
			<spring:message code="request.smoker.no" />
		</form:option>
	</form:select>
	<form:errors cssClass="error" path="smoker" />
	<br />
	
	<form:label path="lessorFee">
		<spring:message code="request.lessorFee" />:
	</form:label>
	<form:input path="lessorFee"/>
	<form:errors cssClass="error" path="lessorFee" />
	<br />
	
	<%-- <form:errors cssClass="error" path="reviewer" /> --%>
	<br />

	<input type="submit" name="save"
		value="<spring:message code="request.save" />" />&nbsp;
	<input type="button" name="cancel"
		value="<spring:message code="request.cancel" />"
		onclick="javascript: relativeRedir('profile/property/list.do');" />
	<br />

</form:form>