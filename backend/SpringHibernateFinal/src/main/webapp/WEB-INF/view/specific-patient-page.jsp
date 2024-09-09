<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <title>Naveen</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/doctor-page.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
    <h3>The Patients Available are</h3>
    <c:choose>
        <c:when test="${empty patients}">
            <h4>No Payments found.</h4>
        </c:when>
   <c:otherwise>
   <div class=:doctor-div>
      <table class="doctor-table">
              <thead>
                  <tr>
                      <th>ID</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Email</th>
                      <th>Details</th>
                      <th>Contact</th>
                      <th>Payments</th>
                      <th>Update</th>
                      <th>Remove</th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach var="patient" items="${patients}">
                      <c:url var="updateLink" value="/showPatientDetail">
                           <c:param name="patientId" value="${patient.id}"/>
                      </c:url>
                      <c:url var="deleteLink" value="/deletePatient">
                          <c:param name="patientId" value="${patient.id}"/>
                      </c:url>
                      <c:url var="getPayments" value="/getPatientPayment">
                            <c:param name="patientId" value="${patient.id}"/>
                      </c:url>

                      <tr>
                          <td>${patient.id}</td>
                          <td>${patient.firstName}</td>
                          <td>${patient.lastName}</td>
                          <td>${patient.email}</td>
                          <td>${patient.details}</td>
                          <td>${patient.contact}</td>
                          <td><a href="${getPayments}">view payments</a></td>
                          <td><a href="${updateLink}" >Update</a></td>
                          <td>
                              <a href="${deleteLink}"
                                 class="delete-icon"
                                 onclick="if(!(confirm('You want to delete the patient with id - ${patient.id}'))) return false;">
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
            onclick="window.location.href='showPatientUpdateForm?doctorId=${doctor.id}';return false;"
            class="outlined-button"
            value="Add Patient" />
  </div>

       </body>
       </html>