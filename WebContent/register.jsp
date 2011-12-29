<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
	<jsp:attribute name="title">
    	Register
    </jsp:attribute>

    <jsp:body>
    	<form method="POST">
    		<fieldset>
    			<div class="clearfix<c:if test="${not empty errors.first_name}"> error</c:if>">
    				<label for="first_name_input">First name:</label>
    				<div class="input">
    					<input
    						class="xlarge<c:if test="${not empty errors.first_name}"> error</c:if>"
    						id="first_name_input"
    						type="text"
    						name="first_name"
    						value="${param.first_name}"
   						/>
    					
    					<c:if test="${not empty errors.first_name}">
	    					<span class="help-inline">${errors.first_name}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="clearfix<c:if test="${not empty errors.last_name}"> error</c:if>">
    				<label for="last_name_input">Last name:</label>
    				<div class="input">
    					<input
    						class="xlarge<c:if test="${not empty errors.last_name}"> error</c:if>"
    						type="text"
    						name="last_name"
    						id="last_name_input"
    						value="${param.last_name}"
    					/>
    				
    					<c:if test="${not empty errors.last_name}">
	    					<span class="help-inline">${errors.last_name}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="clearfix<c:if test="${not empty errors.email}"> error</c:if>">
    				<label for="email_input">Email:</label>
    				<div class="input">
    					<input
    						class="xlarge<c:if test="${not empty errors.email}"> error</c:if>"
    						type="text"
    						name="email"
    						id="email_input"
    						value="${param.email}"
    					/>
    				
    					<c:if test="${not empty errors.email}">
	    					<span class="help-inline">${errors.email}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="clearfix<c:if test="${not empty errors.password}"> error</c:if>">
    				<label for="password_input">Password:</label>
    				<div class="input">
    					<input
    						class="xlarge<c:if test="${not empty errors.password}"> error</c:if>"
    						type="password"
    						name="password"
    						id="password_input"
    						value="${param.password}"
    					/>
    				
    					<c:if test="${not empty errors.password}">
	    					<span class="help-inline">${errors.password}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="clearfix<c:if test="${not empty errors.password_confirmation}"> error</c:if>">
    				<label for="password_confirmation_input">Password confirmation:</label>
    				<div class="input">
    					<input
    						class="xlarge<c:if test="${not empty errors.password_confirmation}"> error</c:if>"
    						type="password"
    						name="password_confirmation"
    						id="password_confirmation_input"
    						value="${param.password_confirmation}"
    					/>
    				
    					<c:if test="${not empty errors.password_confirmation}">
	    					<span class="help-inline">${errors.password_confirmation}</span>
	    				</c:if>
    				</div>
    			</div>
    			
    			<div class="actions">
	            	<input type="submit" class="btn primary" value="Register">
	          	</div>
    		</fieldset>
    	</form>
    </jsp:body>
</t:page>
