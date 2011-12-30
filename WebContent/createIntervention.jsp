<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
	<jsp:attribute name="title">
    	Create an intervention
    </jsp:attribute>

    <jsp:body>
    	<form method="POST">
    		<fieldset>
    			<div class="clearfix<c:if test="${not empty errors.subject}"> error</c:if>">
    				<label for="subject_input">Subject:</label>
    				<div class="input">
    					<input
    						class="xlarge<c:if test="${not empty errors.subject}"> error</c:if>"
    						id="subject_input"
    						type="text"
    						name="subject"
    						value="${param.subject}"
   						/>
    					
    					<c:if test="${not empty errors.subject}">
	    					<span class="help-inline">${errors.subject}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="clearfix<c:if test="${not empty errors.campus}"> error</c:if>">
    				<label for="campus_select">Campus:</label>
    				<div class="input" id="campus_select">
    					<select name="campus">
    						<c:forEach items="${campuses}" var="campus">
    							<option value="${campus.id}">${campus.name}</option>
    						</c:forEach>
    					</select>
    				
    					<c:if test="${not empty errors.campus}">
	    					<span class="help-inline">${errors.campus}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="clearfix<c:if test="${not empty errors.from}"> error</c:if>">
    				<label for="from_input">From:</label>
    				<div class="input">
    					<input
    						class="xlarge<c:if test="${not empty errors.from}"> error</c:if> datepicker"
    						type="text"
    						name="from"
    						id="from_input"
    						value="${param.from}"
    					/>
    				
    					<c:if test="${not empty errors.from}">
	    					<span class="help-inline">${errors.from}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="clearfix<c:if test="${not empty errors.to}"> error</c:if>">
    				<label for="to_input">To:</label>
    				<div class="input">
    					<input
    						class="xlarge<c:if test="${not empty errors.to}"> error</c:if> datepicker"
    						type="text"
    						name="to"
    						id="to_input"
    						value="${param.to}"
    					/>
    				
    					<c:if test="${not empty errors.to}">
	    					<span class="help-inline">${errors.to}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="clearfix<c:if test="${not empty errors.description}"> error</c:if>">
    				<label for="description_textarea">Description:</label>
    				<div class="input">
    					<textarea
    						id="description_textarea"
    						name="description"
    						rows="5"
    						class="xlarge<c:if test="${not empty errors.description}"> error</c:if>"
   						>${param.description}</textarea>
    				
    					<c:if test="${not empty errors.description}">
	    					<span class="help-inline">${errors.description}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="actions">
	            	<input type="submit" class="btn primary" value="Submit">
	          	</div>
    		</fieldset>
    	</form>
    </jsp:body>
</t:page>
