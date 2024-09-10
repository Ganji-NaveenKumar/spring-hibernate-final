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
    <h3>The Doctors Available are</h3>
     <c:choose>
            <c:when test="${empty doctors}">
                <p>No doctors found.</p>
            </c:when>
       <c:otherwise>
      <table class="doctor-table">
              <thead>
                  <tr>
                      <th>ID</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Email</th>
                      <th>Specialist</th>
                      <th>Patients</th>
                      <th>Action</th>
                      <th>Remove</th>
                  </tr>
              </thead>
              <tbody>
                  <c:forEach var="doctor" items="${doctors}">
                      <c:url var="updateLink" value="/showDoctorDetail">
                           <c:param name="doctorId" value="${doctor.id}"/>
                      </c:url>
                      <c:url var="deleteLink" value="/deleteDoctor">
                          <c:param name="doctorId" value="${doctor.id}"/>
                      </c:url>

                      <c:url var="getPatient" value="/getDoctorPatients">
                            <c:param name="doctorId" value="${doctor.id}"/>
                        </c:url>

                      <tr>
                          <td>${doctor.id}</td>
                          <td>${doctor.firstName}</td>
                          <td>${doctor.lastName}</td>
                          <td>${doctor.email}</td>
                          <td>${doctor.specialist}</td>
                          <td><a href="${getPatient}">View Patients</a></td>
                          <td><a href="${updateLink}" >Update</a></td>
                          <td>
                              <a href="${deleteLink}"
                                 class="delete-icon"
                                 onclick="if(!(confirm('You want to delete the doctor with id - ${doctor.id}'))) return false;">
                                  <i class="fas fa-trash-alt"></i> <!-- Font Awesome delete icon -->
                              </a>
                          </td>
                      </tr>
                  </c:forEach>
              </tbody>
          </table>


 <c:forEach var="patient" items="${doctor.patientList}">
 <tr>${patient.firstName}</tr>
 </c:forEach>
        </c:otherwise>
        </c:choose>
        <input type="button"
                  onclick="window.location.href='showDoctorForm';return false;"
                  class="outlined-button"
                  value="Add Doctor" />
      </div>


  </div>

       </body>
       </html>