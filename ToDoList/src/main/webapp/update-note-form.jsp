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
%>



<div class="container mt-3">
	<div class="row">
		<div class="col-md-12">

			<h1 class="text-center">UPDATE NOTE</h1>

			<form action="modificationServlet" method="post">

				<input type="hidden" name="command" value="UPDATE" />

				<%
				User user = (User) session.getAttribute("userDetails");
				if (user != null) {
				%>
				<input type="hidden" value="<%=user.getId()%>" name="uid"> <input
					type="hidden" value="${THE_NOTE.id}" name="nid">
				<%
				}
				%>


				<div class="form-group mb-3 mt-5">
					<input type="text" class="form-control" id="title" name="title"
						value="${THE_NOTE.title}" required="required">
				</div>

				<div class="form-group mb-3">
					<textarea rows="9" cols="" class="form-control" id="description"
						name="description" required="required">${THE_NOTE.description} </textarea>
				</div>

				<div class="text-center d-grid gap-2 mt-5">
					<button type="submit" class="btn btn-primary">UPDATE NOTE</button>
				</div>
			</form>

		</div>
	</div>
</div>



<!-- footer include -->
<c:import url="/include/footer.jsp"></c:import>