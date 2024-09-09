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
    <p>Contact Us</p>
</div>
</body>
</html>
