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

<form:form action="auditor/audit/edit.do" modelAttribute="audit">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="doneMoment" />

	<form:label path="attachmentNumber">
		<spring:message code="audit.AttachmentNumber" />:
	</form:label>
	<form:input path="attachmentNumber" />
	<form:errors cssClass="error" path="attachmentNumber" />
	<br />
	
	<form:label path="finalVersion">
		<spring:message code="audit.finalVersion" />:
	</form:label>
	<form:select id="finalVersion" path="finalVersion">
		<form:option value="true">
			<spring:message code="audit.finalVersion.yes" />
		</form:option>
		<form:option value="false">
			<spring:message code="audit.finalVersion.no" />
		</form:option>
	</form:select>
	<form:errors cssClass="error" path="finalVersion" />
	<br />

	<form:label path="initialText">
		<spring:message code="audit.initialText" />:
	</form:label>
	<form:input path="initialText" />
	<form:errors cssClass="error" path="initialText" />
	<br />
	
	<form:hidden path="auditor"/>
	<form:hidden path="property"/>
	<br />
	
	<%-- <form:errors cssClass="error" path="reviewer" /> --%>
	<br />

	<input type="submit" name="save"
		value="<spring:message code="audit.save" />" />&nbsp; 
	<jstl:if test="${audit.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="audit.delete" />"
			onclick="return confirm('<spring:message code="audit.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="audit.cancel" />"
		onclick="javascript: relativeRedir('profile/audit/list.do');" />
	<br />

</form:form>