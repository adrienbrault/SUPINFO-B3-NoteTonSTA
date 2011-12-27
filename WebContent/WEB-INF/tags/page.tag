<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>

<html>
<head>
	<title>NoteTonSTA - <jsp:invoke fragment="title"/></title>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/vendor/jquery-ui.custom.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/vendor/bootstrap.min.css" />
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css" />
</head>
<body>
	<div class="topbar">
		<div class="fill">
			<div class="container">
			  	<a class="brand" href="${pageContext.request.contextPath}">NoteTonSTA</a>
			  	<ul class="nav">
			    	<li><a href="${pageContext.request.contextPath}/register">Register</a></li>
			    	<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
			  	</ul>
			</div>
		</div>
    </div>

    <div class="container">
    
	    <div class="content">
			<div class="page-header">
				<h1><jsp:invoke fragment="title"/></h1>
			</div>
			
			<div class="row">
				<div class="span14">
					<jsp:doBody/>
				</div>
			</div>
	    </div>
        
    </div>
</body>
</html>
