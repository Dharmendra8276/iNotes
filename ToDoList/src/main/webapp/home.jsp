<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.todo.User"%>

<!-- header include -->
<c:import url="/include/header.jsp">
	<c:param name="title" value="Home"></c:param>
</c:import>

<%
	User user = (User)session.getAttribute("userDetails");

	if(user == null){
		response.sendRedirect("welcome.jsp");
	}
	


if (session.getAttribute("visitupdateNoteServlet") != null) {
	if (session.getAttribute("noteUpdate") != null) {
		session.removeAttribute("visitupdateNoteServlet");
		out.print("<div class='alert alert-success alert-dismissible fade show text-center' role='alert'>"
		+ "To-do Updated Successfully<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button> </div>");
	}
}

%>



<div class=" container card mt-5 py-3 pb-5">
	<div class="card-body text-center">
		<img alt="" src="image/pngegg.png" class="img-fluid mx-auto"
			style="max-width: 350px;">
		<h1>START TAKING YOUR NOTES</h1><br>
		<a href="addNote.jsp" class="btn btn-outline-primary">START HERE</a>

	</div>
</div>




<!-- footer include -->
<c:import url="/include/footer.jsp"></c:import>