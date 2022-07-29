<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 
<style>
/*
.back-img{
	background: url("image/back.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	width: 100%;
	height: 92vh;
	
}
*/

.welcome{
	background-color: #f0ebeb;
	text-align:center;
	margin-top: 100px;
	padding: 30;
}


</style>



<!-- header include -->
<c:import url="/include/header.jsp">
<c:param name="title" value="Welcome"></c:param>
</c:import>

<!-- <div class="container-fluid back-img"></div> -->

<div class="welcome container">
	<h2>Welcome To iNotes</h2><br>
	<a class="btn btn-light custom-btn" href="login.jsp">Login</a><br><br>
	<p>Don't have an account?  <a href="signup.jsp"> SignUp</a> !!</p>
</div>


<!-- footer include -->
<c:import url="/include/footer.jsp"></c:import>



