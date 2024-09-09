<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Payments</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/doctor-page.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
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
<div class="content">
    <h3>The Payments Available are</h3>
     <c:choose>
        <c:when test="${empty payments}">
            <p>No doctors found.</p>
        </c:when>
   <c:otherwise>
    <div class="doctor-div">
        <table class="doctor-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Amount</th>
                    <th>Payment Method</th>
                    <th>Date</th>
                    <th>Update</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="payment" items="${payments}">
                    <c:url var="updateLink" value="/showPaymentDetail">
                        <c:param name="paymentId" value="${payment.id}" />
                    </c:url>
                    <c:url var="deleteLink" value="/deletePayment">
                        <c:param name="paymentId" value="${payment.id}" />
                    </c:url>

                    <tr>
                        <td>${payment.id}</td>
                        <td>${payment.amount}</td>
                        <td>${payment.paymentMethod}</td>
                        <td>${payment.date}</td>
                        <td><a href="${updateLink}">Update</a></td>
                        <td>
                            <a href="${deleteLink}"
                               class="delete-icon"
                               onclick="return confirm('You want to delete the payment with id - ${payment.id}')">
                                <i class="fas fa-trash-alt"></i> <!-- Font Awesome delete icon -->
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </div>
     </c:otherwise>
    </c:choose>
     <input type="button"
       onclick="window.location.href='${pageContext.request.contextPath}/showPaymentForm'; return false;"
       class="outlined-button"
       value="Add Payment" />
</div>
</body>
</html>
