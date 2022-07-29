<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.todo.User"%>

<!-- header include -->
<c:import url="/include/header.jsp">
	<c:param name="title" value="Add Note"></c:param>
</c:import>


<%
if (session.getAttribute("userDetails") == null) {
	response.sendRedirect("welcome.jsp");
}

if (session.getAttribute("visitAddNoteServlet") != null) {
	if (session.getAttribute("noteAdd") != null) {
		session.removeAttribute("visitAddNoteServlet");
		out.println("<div class='alert alert-success alert-dismissible fade show text-center' role='alert'>"
		+ "To-do Added Successfully<button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button> </div>");
	} else {
		out.println("<div class='alert alert-warning alert-dismissible fade show text-center' role='alert'>"
		+ "TO-do Not Added <button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button> </div>");
	}
}
%>



<div class="container mt-3">
	<div class="row">
		<div class="col-md-12">

			<h1 class="text-center">ADD YOUR NOTE</h1>

			<form action="addNotesServlet" method="post">

				<%
				User user = (User) session.getAttribute("userDetails");
				if (user != null) {
				%>
				<input type="hidden" value="<%=user.getId()%>" name="uid">
				<%
				}
				%>


				<div class="form-group mb-3 mt-5">
					<input type="text" class="form-control" id="title" name="title"
						placeholder="Enter Title" required="required">
				</div>

				<div class="form-group mb-3">
					<textarea rows="9" cols="" class="form-control" id="description"
						name="description" placeholder="Description" required="required"></textarea>
				</div>

				<div class="text-center d-grid gap-2 mt-5">
					<button type="submit" class="btn btn-primary">ADD NOTE</button>
				</div>
			</form>

		</div>
	</div>
</div>



<!-- footer include -->
<c:import url="/include/footer.jsp"></c:import>