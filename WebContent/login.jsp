<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
	<jsp:attribute name="title">
    	Login
    </jsp:attribute>

    <jsp:body>
    	<form method="POST">
    		<fieldset>
    			<div class="clearfix">
    				<label for="email_input">Email:</label>
    				<div class="input">
    					<input
    						class="xlarge"
    						id="email_input"
    						type="text"
    						name="email"
    						value="${param.email}"
   						/>
    				</div>
    			</div>
    			
    			<div class="clearfix">
    				<label for="password_input">Password:</label>
    				<div class="input">
    					<input
    						class="xlarge"
    						id="password_input"
    						type="password"
    						name="password"
    						value="${param.password}"
   						/>
    				</div>
    			</div>
    			
    			<div class="actions">
	            	<input type="submit" class="btn primary" value="Login">
	          	</div>
    		</fieldset>
    	</form>
    </jsp:body>
</t:page>
