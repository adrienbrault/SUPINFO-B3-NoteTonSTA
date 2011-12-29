<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<t:page>
	<jsp:attribute name="title">
    	Intervention: <c:out value="${intervention.subject}" />
    </jsp:attribute>

    <jsp:body>
    
    	<h3>Informations</h3>
    
    	<table class="bordered-table">
    		<thead>
    			<tr>
	    			<th>Campus</th>
	    			<th>From</th>
	    			<th>To</th>
    			</tr>
    		</thead>
    		<tbody>
    			<tr>
    				<td>${intervention.campus.name}</td>
    				<td><fmt:formatDate value="${intervention.dateBegin}" /></td>
    				<td><fmt:formatDate value="${intervention.dateEnd}" /></td>
    			</tr>
    		</tbody>
    	</table>
   	
   		<h3>Description</h3>
   		
    	<p>${intervention.description}</p>
    	
    	<h3>Marks</h3>
    	
    	<table class="bordered-table">
    		<thead>
    			<tr>
	    			<th>Count</th>
	    			<th>Speaker</th>
	    			<th>Slides</th>
	    			<th>Global event</th>
    			</tr>
    		</thead>
    		<tbody>
    			<tr>
    				<td>${marks.count}</td>
    				<td>
    					${marks.speakerAverage}
    					<c:if test="${not empty marks.speakerAverage}"></c:if>
   					</td>
    				<td>
    					${marks.slidesAverage}
    					<c:if test="${not empty marks.slidesAverage}"></c:if>
   					</td>
    				<td>
    					${marks.average}
    					<c:if test="${not empty marks.average}"></c:if>
   					</td>
    			</tr>
    		</tbody>
    	</table>
	    
    </jsp:body>
</t:page>
