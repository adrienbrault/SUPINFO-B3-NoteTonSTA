<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<t:page>
	<jsp:attribute name="title">
    	Interventions from ${campus.name}"
    </jsp:attribute>

    <jsp:body>
    
    	<c:choose>
			<c:when test="${interventions.size() gt 0}">
			
				<table class="bordered-table zebra-striped">
				
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
								<td>
									<a href="${pageContext.request.contextPath}/intervention?id=${intervention.id}">
										${intervention.subject}
									</a>
								</td>
								<td><fmt:formatDate value="${intervention.dateBegin}" /></td>
								<td><fmt:formatDate value="${intervention.dateEnd}" /></td>
								<td>${intervention.statusString}</td>
							</tr>
						
						</c:forEach>
						
					</tbody>
				
				</table>
				
			</c:when>
	
			<c:otherwise>
				<h3>There are no interventions from ${campus.name}.</h3>
			</c:otherwise>
		</c:choose>
		
    </jsp:body>
</t:page>
