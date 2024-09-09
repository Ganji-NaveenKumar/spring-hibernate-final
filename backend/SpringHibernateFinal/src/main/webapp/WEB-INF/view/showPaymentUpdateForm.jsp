<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Surya Hospital - Update Payment</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/home-page.css">
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
    <form:form method="post" action="savePayment" modelAttribute="payment">
        <h3>Update Payment Details</h3>

        <form:hidden path="id" />
        <form:hidden path="patient.id" value="${patient.id}"/>
        <div>
            <label for="amount">Amount:</label>
            <form:input path="amount" id="amount" />
             <form:errors path="amount" cssClass="error" />
        </div>
       <div>
                   <label for="paymentMethod">Payment Method:</label>
                   <form:select path="paymentMethod" id="paymentMethod">
                       <form:option value="-1" label="-- Select Payment Method --" />
                       <form:option value="Credit Card">Credit Card</form:option>
                       <form:option value="Debit Card">Debit Card</form:option>
                       <form:option value="Cash">Cash</form:option>
                       <form:option value="UPI">UPI</form:option>
                   </form:select>
                   <form:errors path="paymentMethod" cssClass="error" />
               </div>

        <div>
                   <label for="patient">Patient:</label>
                   <p>${patient.id}) ${patient.firstName} ${patient.lastName}</p>
               </div>

        <div id="button">
            <button type="submit">Save</button>
        </div>

        <p>
            <a href="${pageContext.request.contextPath}/payments">Back</a>
        </p>
    </form:form>
</div>
</body>
</html>
