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
	    			<th>Speaker</th>
	    			<th>From</th>
	    			<th>To</th>
    			</tr>
    		</thead>
    		<tbody>
    			<tr>
    				<td>${intervention.campus.name}</td>
    				<td>${intervention.speaker.firstName} ${intervention.speaker.lastName}</td>
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
    	
    	<c:if test="${not empty logged_speaker and logged_speaker.id eq intervention.speaker.id}">
    		<a class="btn primary evaluateIntervention" href="${pageContext.request.contextPath}/intervention/delete?id=${intervention.id}">Remove</a>
    		<a class="btn primary evaluateIntervention" href="${pageContext.request.contextPath}/intervention/new?id=${intervention.id}">Edit</a>
    	</c:if>
    	
    	<c:if test="${empty logged_speaker}">
    		<button class="btn primary evaluateIntervention">Evaluate</button>
    		
    		<div id="evaluate-intervention-modal" class="modal hide fade">
	            <div class="modal-header">
	              	<a href="#" class="close">×</a>
	              	<h3>Evaluate <strong>${intervention.subject}</strong></h3>
	            </div>
	            <div class="modal-body" style="max-height: 400px; overflow: auto;">
	              	
	            </div>
	            <div class="modal-footer">
	              	<button id="submitEvaluateIntervention" class="btn primary">Submit</button>
	            </div>
          	</div>
    		
    		<script type="text/javascript">
    		$(function() {
    			var evaluateInterventionButton = $('.evaluateIntervention')
    			var submitEvaluateIntervention = $('#submitEvaluateIntervention');
    			var modal = $('#evaluate-intervention-modal');
				var modalBody = modal.find('.modal-body');
				var submitUrl = '${pageContext.request.contextPath}/evaluate/intervention?id=${intervention.id}';
				
    			evaluateInterventionButton.bind('click', function(event) {
    				submitEvaluateIntervention.show();
    				
    				modal.modal({
    					'show': true,
    					'backdrop': true
    				});
    				
    				var loadingBody = 
    					'<div style="text-align: center; margin: 10px;">'
    						+ '<img src="${pageContext.request.contextPath}/assets/img/ajax-loader.gif" alt="Loading" />'
    					+ '</div>';
    				modalBody.html(loadingBody);
    				
    				submitEvaluateIntervention.button('loading');
    				
    				$.ajax({
    					url: submitUrl
    				}).done(function(response) {
    					// Check if response is OK.
   						modalBody.html(response);
   					}).fail(function() {
   						modal.modal('hide');
   					}).always(function() {
   						submitEvaluateIntervention.button('reset');
   					});
    			});
    			
    			submitEvaluateIntervention.bind('click', function(event) {
    				var form = modal.find('form');
    				
    				form.submit();
    			});
    			
    			modal.on('submit', 'form', function(event) {
    				event.preventDefault();
    				
    				var form = modal.find('form');
    				
    				$.ajax({
    					url: submitUrl,
    					type: 'POST',
    					data: form.serialize()
    				}).done(function (response) {
    					if (response == 'OK') {
    						modalBody.html('<h3>Your evaluation has been created.</h3>');
    						submitEvaluateIntervention.hide();
    					} else {
    						modalBody.html(response);
    					}
    				});
    			});
    		});
    		</script>
    	</c:if>
	    
    </jsp:body>
</t:page>
