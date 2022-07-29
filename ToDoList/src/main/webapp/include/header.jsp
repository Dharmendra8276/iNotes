<%@page import="com.todo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>${param.title}</title>

<!-- Bootstrap CDN -->
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">

<!-- icons -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,700,1,200" />


<!-- css file -->
<link rel="stylesheet" href="css/pageStyle.css">


</head>
<body>

	<!-- Nav-bar  -->

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark navbar-custom">
		<div class="container-fluid">
			<%
			
			if (session.getAttribute("userDetails") == null) {
				out.println("<a class='navbar-brand' href='welcome.jsp'>iNotes</a>");
			} else {
				out.println("<a class='navbar-brand' href='home.jsp'>iNotes</a>");
			}
			%>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ">
					<li class="nav-item">
						<%
						if (session.getAttribute("userDetails") == null) {
							out.println("<a class='nav-link custom-style' aria-current='page' href='welcome.jsp'>Home</a>");
						} else {
							out.println("<a class='nav-link custom-style' aria-current='page' href='home.jsp'>Home</a>");
						}
						%>
					</li>

					<%
					if (session.getAttribute("userDetails") == null) {
						out.print("<li class='nav-item'> <a class='nav-link custom-style' href='login.jsp'>Login</a> </li>");

						out.print("<li class='nav-item'> <a class='nav-link custom-style' href='signup.jsp'>SignUp</a> </li>");
					} else {
						out.print("<li class='nav-item'> <a class='nav-link custom-style' href='addNote.jsp'>Add Note</a> </li>");

						out.print("<li class='nav-item'> <a class='nav-link custom-style' href='/ToDoList/showNotesServlet'>Show Note</a> </li>");
					}
					%>


					<li class="nav-item"></li>
				</ul>


				<form class="form-inline my-2 my-lg-0">
					<%
					User user = (User) session.getAttribute("userDetails");

					if (user != null) {
					%>
					<a class="btn btn-light custom-btn" data-bs-toggle="modal"
						data-bs-target="#exampleModal" href=""><%=user.getName()%></a>; <a
						class="btn btn-light custom-btn" href="logout.jsp">Logout</a>;

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">User
										Profile</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<div class="container ">
										<center> <span class="material-symbols-outlined"> person </span> </center>

										<table class="table">
											<tbody>
												<tr>
													<th>User Id</th>
													<td><%=user.getId()%></td>
												</tr>

												<tr>
													<th>Full Name</th>
													<td><%=user.getName()%></td>
												</tr>

												<tr>
													<th>Email Id</th>
													<td><%=user.getEmail()%></td>
												</tr>
											</tbody>

										</table>

									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>

					<%
					}
					%>
				</form>
			</div>
		</div>
	</nav>

	<!-- Navbar end -->