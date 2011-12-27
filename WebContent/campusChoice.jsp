<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:page>
    <jsp:body>
        <select name="campus">
            <c:forEach items="${campuses}" var="campus">
                <option value="<c:out value="${campus.id}" />">
                    <c:out value="${campus.name}" />
                </option>
            </c:forEach>
        </select>
    </jsp:body>
</t:page>
