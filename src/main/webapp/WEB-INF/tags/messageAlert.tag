<%@ tag description="Message Alert Block" pageEncoding="UTF-8" %>
<%@ attribute name="message"
              type="ru.t_systems.alyona.sbb.dto.MessageDTO"
              required="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${message.severity == 'INFORMATIONAL'}">
        <div class="alert alert-info" role="alert">
                ${message.text}
        </div>
    </c:when>
    <c:when test="${message.severity == 'WARNING'}">
        <div class="alert alert-warning" role="alert">
                ${message.text}
        </div>
    </c:when>
    <c:when test="${message.severity == 'ERROR'}">
        <div class="alert alert-danger" role="alert">
                ${message.text}
        </div>
    </c:when>
    <c:when test="${message.severity == 'TECHNICAL_ERROR'}">
        <div class="alert alert-danger" role="alert">
                ${message.text}
        </div>
    </c:when>
    <c:otherwise>
        <div class="alert alert-secondary" role="alert">
                ${message.text}
        </div>
    </c:otherwise>
</c:choose>
