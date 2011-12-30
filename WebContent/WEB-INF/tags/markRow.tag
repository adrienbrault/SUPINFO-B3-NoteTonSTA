<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag description="Marks form rom template" pageEncoding="UTF-8" %>
<%@ attribute name="name" %>
<%@ attribute name="value" %>

<c:forEach var="i" begin="1" end="5" step="1" varStatus ="status">
	<td>
		<input
			type="radio"
			name="${name}"
			value="${i}"
			<c:if test="${value eq i}">checked="checked"</c:if>
		/>
	</td>
</c:forEach>
