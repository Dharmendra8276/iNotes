<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style>
   .container{
     margin-top: 90px;
   }

   .mb{
     margin-bottom: 25px;
   }
   
   .mt{
   	 margin-top:25px;
   }
   .btn{
     width:100%;
   }
</style>

<!-- header include -->
<c:import url="/include/header.jsp">
<c:param name="title" value="SignUp"></c:param>
</c:import>

<%
	if(session.getAttribute("visitSignupServlet") != null){
		if(session.getAttribute("signup-success") == null){
			out.print("<div class='alert alert-danger' role='alert'> Something went wrong!!</div>");
		}
	}

%>


<div class="container">
   <div class="row">
   		<div class="col-md-4 offset-md-4">
   			<div class="card">
   				<div class="card-header">
   				   <h2 class="text-center mb mt">SIGNUP</h2>
				   <form action="signupServlet" method="post">
				   	 <div class="mb">
				       <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Full Name" aria-describedby="emailHelp" required="required">
				     </div>
				     <div class="mb">
				       <input type="email" class="form-control" id="email" name="email" placeholder="Email Address" aria-describedby="emailHelp" required="required">
				     </div>
				     <div class="mb">
				       <!-- <label for="password" class="form-label">Password</label> -->
				       <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
				     </div>
				     <button type="submit" class="btn btn-primary mt-3">SignUp</button>
				
				     <p class="mt-5 text-center " >Already have an account? <a href="login.jsp">Login</a></p>
				   </form>
   				</div>
   			</div>   		
   		</div>   
   </div>
</div>


<!-- footer include -->
<c:import url="/include/footer.jsp"></c:import>