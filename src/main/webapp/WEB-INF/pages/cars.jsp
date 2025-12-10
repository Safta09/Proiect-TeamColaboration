<%@page contentType="text/html; charset=UTF-8"  %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- You might need to import JSTL functions if you use fn:contains. For this example, we'll assume a boolean is set in the controller. --%>
<%-- If you cannot set a boolean, please confirm how you check for list containment in your setup. --%>

<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>

    <c:set var="canWriteCars" value="${userGroups.contains('WRITE_CARS')}" />

    <form method="POST" action="${pageContext.request.contextPath}/CarsServlet">

        <c:if test="${canWriteCars}">
            <a href="${pageContext.request.contextPath}/AddCarServlet" class="btn btn-primary btn-lg">Add Car</a>
            <button class="btn btn-danger" type="submit">Delete Cars</button>
        </c:if>

        <div class="container text-center">
            <c:forEach var="car" items="${cars}">
                <div class="row">
                    <div class="col">
                        <c:if test="${canWriteCars}">
                            <input type="checkbox" name="carIds" value="${car.id}" />
                        </c:if>
                    </div>
                    <div class="col">
                            ${car.licensePlate}
                    </div>
                    <div class="col">
                            ${car.parkingSpot}
                    </div>
                    <div class="col">
                            ${car.ownerName}
                    </div>

                    <div class="col">
                        <c:if test="${canWriteCars}">
                            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditCarServlet?id=${car.id}">Edit Car</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </form>
</t:pageTemplate>