<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<t:page>
	<jsp:attribute name="title">
    	Interventions from <c:out value="${campus.name}" />
    </jsp:attribute>

    <jsp:body>
    
    	<c:choose>
			<c:when test="${interventions.size() gt 0}">
			
				<table class="bordered-table">
				
					<thead>
						<tr>
							<th>Subject</th>
							<th>Begin</th>
							<th>End</th>
							<th>Status</th>
						</tr>
					</thead>
					
					<tbody>
						
						<c:forEach items="${interventions}" var="intervention">
						
							<tr>
								<td><c:out value="${intervention.subject}" /></td>
								<td><fmt:formatDate value="${intervention.dateBegin}" /></td>
								<td><fmt:formatDate value="${intervention.dateEnd}" /></td>
								<td><c:out value="${intervention.statusString}" /></td>
							</tr>
						
						</c:forEach>
						
					</tbody>
				
				</table>
				
			</c:when>
	
			<c:otherwise>
				<h3>There are no interventions from <c:out value="${campus.name}" />.</h3>
			</c:otherwise>
		</c:choose>
		
    </jsp:body>
</t:page>
