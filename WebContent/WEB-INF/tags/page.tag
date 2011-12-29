<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>NoteTonSTA - <jsp:invoke fragment="title"/></title>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/jquery-ui.custom.min.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-alerts.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-buttons.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-dropdown.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-twipsy.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-modal.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-popover.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-scrollspy.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/bootstrap-tabs.js"></script>
	<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.css" />
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/scripts.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css" />
</head>
<body>
	<div class="topbar">
		<div class="fill">
			<div class="container">
			  	<a class="brand" href="${pageContext.request.contextPath}">NoteTonSTA</a>
			  	<ul class="nav">
			  		<c:choose>
					  	<c:when test="${not empty logged_speaker}">
					  		<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
					  	</c:when>
					  	<c:otherwise>
					    	<li><a href="${pageContext.request.contextPath}/register">Register</a></li>
				    		<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
					  	</c:otherwise>
					</c:choose>
			  	</ul>
			  	
			  	<ul class="nav secondary-nav">
			  		<c:if test="${not empty logged_speaker}">
			  			<li><a href="#">Logged in as ${logged_speaker.email}</a></li>
			  		</c:if>
			  	</ul>
			</div>
		</div>
    </div>

    <div class="container">
    
	    <div class="content">
			<div class="page-header">
				<h1><jsp:invoke fragment="title"/></h1>
				
				<c:if test="${not empty alert_error}">
					<div class="alert-message error" data-alert="alert">
				        <a class="close" href="#">×</a>
				        <p>${alert_error}</p>
		      		</div>
				</c:if>
			</div>
			
			<jsp:doBody/>
	    </div>
        
    </div>
</body>
</html>
