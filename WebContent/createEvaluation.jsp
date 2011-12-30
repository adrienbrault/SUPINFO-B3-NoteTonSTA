<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<form>
	<fieldset>
		<div class="clearfix<c:if test="${not empty errors.id_booster}"> error</c:if>">
			<label for="id_booster_input">ID Booster</label>
			<div class="input">
				<input type="number" name="id_booster" id="id_booster_input" value="${param.id_booster}" />
				
				<c:if test="${not empty errors.id_booster}">
					<span class="help-inline">${errors.id_booster}</span>
				</c:if>
			</div>
		</div>
	</fieldset>
	
	<fieldset>
		<legend>About the speaker</legend>
		
		<div class="clearfix<c:if test="${not empty errors.speaker_mark}"> error</c:if>">
			<table class="bordered-table">
				<thead>
					<tr>
						<th></th>
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td>His knowledge of the subject</td>
						<t:markRow>
							<jsp:attribute name="name">speaker_knowledge_mark</jsp:attribute>
							<jsp:attribute name="value">${param.speaker_knowledge_mark}</jsp:attribute>
						</t:markRow>
					</tr>
					<tr>
						<td>His teaching abilities</td>
						<t:markRow>
							<jsp:attribute name="name">speaker_teaching_mark</jsp:attribute>
							<jsp:attribute name="value">${param.speaker_teaching_mark}</jsp:attribute>
						</t:markRow>
					</tr>
					<tr>
						<td>The quality of his answers</td>
						<t:markRow>
							<jsp:attribute name="name">speaker_answers_mark</jsp:attribute>
							<jsp:attribute name="value">${param.speaker_answers_mark}</jsp:attribute>
						</t:markRow>
					</tr>
				</tbody>
			</table>
			
			<c:if test="${not empty errors.speaker_mark}">
				<span class="help-inline error">${errors.speaker_mark}</span>
			</c:if>
		</div>
	</fieldset>
	
	<fieldset>
		<legend>About the slides</legend>
		
		<div class="clearfix<c:if test="${not empty errors.slides_mark}"> error</c:if>">
			<table class="bordered-table">
				<thead>
					<tr>
						<th></th>
						<th>1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td>The content richness</td>
						<t:markRow>
							<jsp:attribute name="name">slides_content_mark</jsp:attribute>
							<jsp:attribute name="value">${param.slides_content_mark}</jsp:attribute>
						</t:markRow>
					</tr>
					<tr>
						<td>The format / layout</td>
						<t:markRow>
							<jsp:attribute name="name">slides_format_mark</jsp:attribute>
							<jsp:attribute name="value">${param.slides_content_mark}</jsp:attribute>
						</t:markRow>
					</tr>
					<tr>
						<td>The examples</td>
						<t:markRow>
							<jsp:attribute name="name">slides_examples_mark</jsp:attribute>
							<jsp:attribute name="value">${param.slides_content_mark}</jsp:attribute>
						</t:markRow>
					</tr>
				</tbody>
			</table>
			
			<c:if test="${not empty errors.slides_mark}">
				<span class="help-inline error">${errors.slides_mark}</span>
			</c:if>
		</div>
	</fieldset>
	
	<fieldset>
		<div class="clearfix">
			<label for="email_input">Comments</label>
			<div class="input">
				<textarea rows="3" name="comments" class="xlarge"></textarea>
			</div>
		</div>
	</fieldset>
</form>