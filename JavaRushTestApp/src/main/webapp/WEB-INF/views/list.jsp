<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="pagedListHolder" scope="request"
   type="org.springframework.beans.support.PagedListHolder"/>

<c:url value="list" var="pagedLink">

<c:param name="p" value="~"/>
</c:url>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JavaRushTestApp | Users</title>
</head>
<body>
<div class = "javarush-testapp">



 <h1><a href = "list"><center>JavaRush TestApp</center></a></h1>


   <form:form modelAttribute="user" method="post"
    action="register">
    <div class="section"><span>1</span>User Registration<br></div>
    <div class="inner-wrap">

    <form:label path="name">Name</form:label>
      <form:input path="name"/>
      <div style="color: red; font-style: italic;"><form:errors
             path="name" />

      </div>
    <form:label path="age">Age</form:label>
      <form:input path="age" />
      <div style="color: red; font-style: italic;"><form:errors
                   path="age" />
      </div>

      <br>

    <label> Admin<form:checkbox path="isAdmin" /></label>
    </div>

    <div class="button-section">

    <input type="submit" value="Register" />
    </div>

   </form:form>

   <br>

   <div class = "section">Search for name</div>
   <div class = "inner-wrap">

   <form:form modelAttribute="user" action="search" method="get"  >
      <form:input type="text" path="name" />
   </div>
   <div class="button-section">
      <input type="submit" value="Search" >
   </div>

   </form:form>

   <br>


  <div class="section"><span>2</span>Table of Users</div>
  <div class = "inner-wrap">

       <c:if test="${empty userList}">
       <label>No matches found or table is empty.</label>
       </c:if>
      <c:if test="${!empty userList}">
       <table>
       <tr>



         <th>Id</th>
         <th>Name</th>
         <th>Age</th>
         <th>Admin</th>
         <th>Date created/modified</th>
         <th>Edit</th>
         <th>Delete</th>

       </tr>

        <c:forEach items="${pagedListHolder.pageList}" var="user">
         <tr>

          <td><c:out value="${user.id}" />
          </td>
          <td><c:out value="${user.name}" />
          </td>
          <td><c:out value="${user.age}" />
          </td>
          <td><c:if test = "${user.isAdmin}">
          <c:out value="yes" />
          </c:if>
          </td>
          <td><c:out value="${user.createdDate}" />
          </td>

          <td><a href="edit?id=${user.id}">Edit</a></td>
          <td><a href="delete?id=${user.id}">Delete</a></td>
         </tr>
        </c:forEach>
       </table>
      </c:if>
      </div>
      <center><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></center>




 </div>

</body>
</html>

