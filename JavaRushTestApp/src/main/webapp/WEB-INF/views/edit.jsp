<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JavaRushTestApp | Edit User Details</title>
</head>
<body>
<div class = "javarush-testapp">


   <h1><center>JavaRushTestApp | Edit User Details</center></h1>






  <form:form id="registerForm" modelAttribute="user" method="post"
   action="update">

   <form:input path="id" value="${userObject.id}" type="hidden" />
   <div class = "section"></div>
   <div class="inner-wrap">
   <form:label path="name">Name</form:label>
     <form:input path="name" value="${userObject.name}" />
     <div style="color: red; font-style: italic;"><form:errors
                        path="name" />


     </div>


    <form:label path="age">Age</form:label>
     <form:input path="age" value="${userObject.age}"/>
     <div style="color: red; font-style: italic;"><form:errors
                        path="age" />
     </div>



     <label> Admin<form:checkbox path="isAdmin" /></label>

   </div>




    <div class = "button-section">

     <input type="submit" value="Update" />
     </div>




  </form:form>

  <br>


<div class = "back">
  <a href = "list"><center>Back</center></a>
</div>




 </div>
</body>
</html>