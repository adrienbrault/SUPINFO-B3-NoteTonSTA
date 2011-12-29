<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
	<jsp:attribute name="title">
      Select your campus
    </jsp:attribute>

    <jsp:body>
    	<p>This website propose you to evaluate interventions of SUPINFO speakers.</p>
		<p>You can also see statistics by speaker or campus.</p>
   
   		<h3>Select a campus:</h3>
   	
    	<select name="campus" id="campus_select">
        	<option>-- Campus --</option>
        
            <c:forEach items="${campuses}" var="campus">
                <option value="<c:out value="${campus.id}" />">
                    <c:out value="${campus.name}" />
                </option>
            </c:forEach>
        </select>
        
        <br />
        <br />
        
        <p>
        	If you are speaker and already have an account, please
        	<a href="${pageContext.request.contextPath}/login">login</a>
        	to manager your interventions.
       	</p>
		<p>
			If you don't have an account, please
			<a href="${pageContext.request.contextPath}/register">register</a>
			.
		</p>
		
		<script type="text/javascript">
		$(function() {
			$("#campus_select").change(function() {
				window.location = "${pageContext.request.contextPath}/interventions?campusId=" + $(this).val();
			});
		});
		</script>
    </jsp:body>
</t:page>
