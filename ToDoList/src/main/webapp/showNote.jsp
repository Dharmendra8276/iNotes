<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page
	import="com.todo.User, java.util.List, com.todo.ProjectDbUtil, com.todo.Post"%>


<%
User user = (User) session.getAttribute("userDetails");
if (user == null) {
	response.sendRedirect("welcome.jsp");
}
%>


<!-- header include -->
<c:import url="/include/header.jsp">
	<c:param name="title" value="Show Notes"></c:param>
</c:import>


<div class="container">
	<h2 class="text-center">All Notes</h2>

	<div class="row">
		<div class="col-md-12">

			<c:forEach var="note" items="${USER_NOTES}">

				<div class="card mt-3">
					<div class="card-body p-4">

						<h5 class="card-title">${note.title}</h5>
						<p>${note.description}</p>

						<p>
							<b class="text-success">Published By: <%=user.getName()%></b> <br>
							<b class="text-primary"></b>
						</p>

						<p>
							<b class="text-success">Published Date: ${note.noteDate}</b> <br>
							<b class="text-success"></b>
						</p>

						<div class="container text-center mt-2">

							<c:url var="updateLink" value="modificationServlet">
								<c:param name="command" value="LOAD" />
								<c:param name="noteId" value="${note.id}" />
							</c:url>

							<!--  set up a link to delete a student -->
							<c:url var="deleteLink" value="modificationServlet">
								<c:param name="command" value="DELETE" />
								<c:param name="noteId" value="${note.id}" />
							</c:url>


							<a href="${updateLink}" class="btn btn-primary">Edit</a> <a
								href="${deleteLink}" class="btn btn-danger">Delete</a>

						</div>

					</div>
				</div>

			</c:forEach>
		</div>
	</div>


</div>



<!-- footer include -->
<c:import url="/include/footer.jsp"></c:import>