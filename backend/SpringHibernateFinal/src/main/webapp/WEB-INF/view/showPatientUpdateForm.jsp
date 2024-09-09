<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Surya Hospital</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/home-page.css">
</head>
<body>
<div class="sidebar">
    <div class="sidebar">
        <div class="nav-item">
            <a href="${pageContext.request.contextPath}/">Home</a>
        </div>
        <div class="nav-item">
            <a href="${pageContext.request.contextPath}/doctors">Doctors</a>
        </div>
        <div class="nav-item">
            <a href="${pageContext.request.contextPath}/patients">Patients</a>
        </div>
        <div class="nav-item">
            <a href="${pageContext.request.contextPath}/payments">Payments</a>
        </div>
        <div class="nav-item">
            <a href="${pageContext.request.contextPath}/contact">Contact</a>
        </div>
    </div>
</div>

<div class="content">
    <form:form method="post" action="saveSpecificPatient" modelAttribute="patient">
        <h3>Enter the details</h3>
        <form:hidden path="id" />
        <form:hidden path="doctor.id" /> <!-- Fixed Doctor ID -->

        <div>
            <label for="firstName">First Name:</label>
            <form:input path="firstName" id="firstName" />
            <form:errors path="firstName" cssClass="error" />
        </div>

        <div>
            <label for="lastName">Last Name:</label>
            <form:input path="lastName" id="lastName" />
              <form:errors path="lastName" cssClass="error" />
        </div>

        <div>
            <label for="email">Email:</label>
            <form:input path="email" id="email" />
            <form:errors path="email" cssClass="error" />
        </div>

        <div>
            <label for="details">Details:</label>
            <form:input path="details" id="details" />
             <form:errors path="details" cssClass="error" />
        </div>
        <div>
            <label for="contact">Contact:</label>
            <form:input path="contact" id="contact" />
             <form:errors path="contact" cssClass="error" />
        </div>
        <div>
            <label for="doc">Doctor:</label>
            <p>${doctor.firstName} ${doctor.lastName}</p>
            <input type="hidden" name="doctorId" value="${doctor.id}" />
        </div>
        <div id="button">
            <button type="submit">Save</button>
        </div>

        <p>
        <a href="${pageContext.request.contextPath}/patients">Back</a>
        </p>
    </form:form>
</div>
</body>
</html>
